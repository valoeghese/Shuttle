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
import tk.valoeghese.shuttle.impl.Wrappers;
import tk.valoeghese.shuttle.impl.player.PlayerImpl;
import tk.valoeghese.shuttle.impl.world.block.BlockImpl;
import tk.valoeghese.shuttle.impl.world.interact.WorldImpl;

@Mixin(ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager {
	@Redirect(at = @At(value = "INVOKE", target = Targets.CANT_MINE), method = "tryBreakBlock(Lnet/minecraft/util/math/BlockPos;)Z")
	private boolean onBlockBreak(ServerPlayerEntity invokedOn, World world, BlockPos pos, GameMode gamemode) {
		// create context
		PlayerBlockInteractionContext context = new PlayerBlockInteractionContext(
				new PlayerImpl(invokedOn),
				new WorldImpl((ServerWorld) world),
				Wrappers.wrap(world.getBlockState(pos)),
				Wrappers.wrap(pos));

		// post event
		ShuttleEvents.PLAYER_BLOCK_BREAK.postEvent(context);

		// result
		EventResult result = context.getResult();

		// yes it's reversed, it returns whether it *can't* mine
		if (result == EventResult.FAIL) {
			return true;
		} else if (result == EventResult.SUCCESS) {
			return false;
		}

		return invokedOn.canMine(world, pos, gamemode);
	}
}
