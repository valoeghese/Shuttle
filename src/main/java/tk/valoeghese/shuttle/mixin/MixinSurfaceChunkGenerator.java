package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ChunkShapeContext;
import tk.valoeghese.shuttle.impl.world.block.BlockImpl;
import tk.valoeghese.shuttle.impl.world.interact.GeneratingChunkImpl;

@Mixin(SurfaceChunkGenerator.class)
public abstract class MixinSurfaceChunkGenerator<T extends ChunkGeneratorConfig> extends ChunkGenerator<T> {
	public MixinSurfaceChunkGenerator(IWorld world, BiomeSource biomeSource, T config) {
		super(world, biomeSource, config);
	}

	@Inject(at = @At("RETURN"), method = "populateNoise")
	private void onChunkShape(IWorld region, Chunk chunk, CallbackInfo info) {
		// create random
		ChunkRandom rand = new ChunkRandom();
		// set random seed
		rand.setSeed(chunk.getPos().x, chunk.getPos().z);
		// create context for chunk shape
		ChunkShapeContext context = new ChunkShapeContext(
				new GeneratingChunkImpl(region, chunk),
				BlockImpl.of(this.getConfig().getDefaultBlock().getBlock()),
				BlockImpl.of(this.getConfig().getDefaultFluid().getBlock()),
				rand
				);
		// post event
		ShuttleEvents.CHUNK_SHAPE.postEvent(context);
	}
}
