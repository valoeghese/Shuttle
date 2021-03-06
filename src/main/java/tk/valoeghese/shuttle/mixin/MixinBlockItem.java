package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.network.packet.s2c.play.ContainerSlotUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockPlacementContext;
import tk.valoeghese.shuttle.impl.Wrappers;
import tk.valoeghese.shuttle.impl.player.PlayerImpl;
import tk.valoeghese.shuttle.impl.world.interact.WorldImpl;

@Mixin(BlockItem.class)
public class MixinBlockItem {
	@Inject(at = @At("RETURN"), method = "getPlacementState", cancellable = true)
	private void onGetPlacementState(ItemPlacementContext placementContext, CallbackInfoReturnable<BlockState> info) {
		PlayerEntity entity = placementContext.getPlayer();

		if (entity instanceof ServerPlayerEntity) {
			BlockState state = info.getReturnValue();
			// create context
			PlayerBlockPlacementContext context = new PlayerBlockPlacementContext(
					new PlayerImpl((ServerPlayerEntity) entity),
					new WorldImpl((ServerWorld) placementContext.getWorld()),
					state == null ? null : Wrappers.wrap(state),
					Wrappers.wrap(placementContext.getBlockPos()));

			// post event
			ShuttleEvents.PLAYER_BLOCK_PLACE.postEvent(context);
			// result
			EventResult result = context.getResult();

			if (result == EventResult.FAIL) {
				info.setReturnValue(null);
				int selectedSlot = entity.inventory.selectedSlot;
				((ServerPlayerEntity) entity).networkHandler.sendPacket(new ContainerSlotUpdateS2CPacket(-2, selectedSlot, entity.inventory.getInvStack(selectedSlot)));
			} else if (result == EventResult.SUCCESS || (state != null && context.blockModified())) {
				// also check for non-null results in case block is changed.
				info.setReturnValue(context.blockModified() ? Wrappers.unwrap(context.getBlock()) : state);
			}
		}
	}
}
