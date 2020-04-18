package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.ChunkRegion;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ReplaceBlocksContext;
import tk.valoeghese.shuttle.impl.Targets;
import tk.valoeghese.shuttle.impl.world.BlockImpl;
import tk.valoeghese.shuttle.impl.world.GeneratingChunkImpl;

@Mixin(ChunkStatus.class)
public class MixinChunkStatus {
	@Redirect(at = @At(value = "INVOKE", target = Targets.BUILD_SURFACE), method = "method_16567")
	private static void onBuildSurface(ChunkGenerator<?> generator, ChunkRegion region, Chunk chunk) {
		// create random
		ChunkRandom rand = new ChunkRandom();
		// set random seed
		rand.setSeed(chunk.getPos().x, chunk.getPos().z);
		// create context for chunk shape
		ReplaceBlocksContext context = new ReplaceBlocksContext(
				new GeneratingChunkImpl(region, chunk),
				BlockImpl.of(generator.getConfig().getDefaultBlock().getBlock()),
				BlockImpl.of(generator.getConfig().getDefaultFluid().getBlock()),
				rand
				);
		// post event
		ShuttleEvents.REPLACE_BLOCKS.postEvent(context);
		// if event is not to overwrite vanilla, run vanilla
		if (!context.getResult()) {
			generator.buildSurface(region, chunk);
		}
	}
}
