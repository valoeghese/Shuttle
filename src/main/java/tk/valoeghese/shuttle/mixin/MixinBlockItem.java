package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.world.WorldInteractionEvents.PlayerBlockInteractionContext;
import tk.valoeghese.shuttle.impl.PlayerImpl;
import tk.valoeghese.shuttle.impl.world.BlockImpl;

@Mixin(BlockItem.class)
public class MixinBlockItem {
	@Inject(at = @At("HEAD"), method = "canPlace", cancellable = true)
	private void canPlace(ItemPlacementContext placementContext, BlockState state, CallbackInfoReturnable<Boolean> info) {
		PlayerEntity entity = placementContext.getPlayer();

		if (entity instanceof ServerPlayerEntity) {
			// create context
			PlayerBlockInteractionContext context = new PlayerBlockInteractionContext(
					new PlayerImpl((ServerPlayerEntity) entity),
					new BlockImpl(state.getBlock()),
					placementContext.getBlockPos());
			// post event
			ShuttleEvents.PLAYER_BLOCK_PLACE.postEvent(context);
			// result
			EventResult result = context.getResult();

			if (result == EventResult.FAIL) {
				info.setReturnValue(false);
			} else if (result == EventResult.SUCCESS) {
				info.setReturnValue(true);
			}
		}
	}
}
