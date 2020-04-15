package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockInteractionContext;
import tk.valoeghese.shuttle.impl.Targets;
import tk.valoeghese.shuttle.impl.player.PlayerImpl;
import tk.valoeghese.shuttle.impl.world.BlockImpl;
import tk.valoeghese.shuttle.impl.world.WorldImpl;

@Mixin(ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager {
	@Redirect(at = @At(value = "INVOKE", target = Targets.CANT_MINE), method = "tryBreakBlock(Lnet/minecraft/util/math/BlockPos;)Z")
	private boolean onBlockBreak(ServerPlayerEntity invokedOn, World world, BlockPos pos, GameMode gamemode) {
		// create context
		PlayerBlockInteractionContext context = new PlayerBlockInteractionContext(
				new PlayerImpl(invokedOn),
				new WorldImpl((ServerWorld) world),
				BlockImpl.of(world.getBlockState(pos).getBlock()),
				pos);

		// post event
		ShuttleEvents.PLAYER_BLOCK_BREAK.postEvent(context);

		// result
		EventResult result = context.getResult();

		if (result == EventResult.FAIL) {
			return true;
		} else if (result == EventResult.SUCCESS) {
			return false;
		}

		return invokedOn.canMine(world, pos, gamemode);
	}
}
