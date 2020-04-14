package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.world.WorldInteractionEvents.PlayerBlockInteractionContext;
import tk.valoeghese.shuttle.impl.PlayerImpl;
import tk.valoeghese.shuttle.impl.world.BlockImpl;

@Mixin(World.class)
public class MixinWorld {
	@Inject(at = @At("HEAD"), method = "breakBlock", cancellable = true)
	private void breakBlock(BlockPos pos, boolean drop, Entity breakingEntity, CallbackInfoReturnable<Boolean> info) {
		if (breakingEntity instanceof ServerPlayerEntity) {
			// create context
			PlayerBlockInteractionContext context = new PlayerBlockInteractionContext(
					new PlayerImpl((ServerPlayerEntity) breakingEntity),
					new BlockImpl(((World) (Object) this).getBlockState(pos).getBlock()),
					pos);
			// post event
			ShuttleEvents.PLAYER_BLOCK_BREAK.postEvent(context);
			// result
			if (context.getResult() == EventResult.FAIL) {
				info.setReturnValue(false);
			}
			// success is default for block break, so no check and action for success needed.
		}
	}
}
