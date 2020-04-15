package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockInteractionContext;
import tk.valoeghese.shuttle.impl.player.PlayerImpl;
import tk.valoeghese.shuttle.impl.world.BlockImpl;
import tk.valoeghese.shuttle.impl.world.WorldImpl;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
	// why is this called canMine, it's reversed
	@Inject(at = @At("HEAD"), method = "canMine", cancellable = true)
	private void cantMine(World world, BlockPos pos, GameMode gameMode, CallbackInfoReturnable<Boolean> info) {
		if ((Object) this instanceof ServerPlayerEntity) {
			// create context
			PlayerBlockInteractionContext context = new PlayerBlockInteractionContext(
					new PlayerImpl((ServerPlayerEntity) (Object) this),
					new WorldImpl((ServerWorld) world),
					BlockImpl.of(world.getBlockState(pos).getBlock()),
					pos);
			// post event
			ShuttleEvents.PLAYER_BLOCK_BREAK.postEvent(context);
			// result
			EventResult result = context.getResult();

			if (result == EventResult.FAIL) {
				info.setReturnValue(true);
			} else if (result == EventResult.SUCCESS) {
				info.setReturnValue(false);
			}
		}
	}
}
