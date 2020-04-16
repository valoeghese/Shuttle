package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ChunkShapeContext;
import tk.valoeghese.shuttle.impl.world.BlockImpl;
import tk.valoeghese.shuttle.impl.world.GeneratingChunkImpl;

@Mixin(SurfaceChunkGenerator.class)
public abstract class MixinSurfaceChunkGenerator<T extends ChunkGeneratorConfig> extends SurfaceChunkGenerator<T> {
	public MixinSurfaceChunkGenerator(IWorld world, BiomeSource biomeSource, int verticalNoiseResolution, int horizontalNoiseResolution, int worldHeight, T config, boolean useSimplexNoise) {
		super(world, biomeSource, verticalNoiseResolution, horizontalNoiseResolution, worldHeight, config, useSimplexNoise);
	}

	@Inject(at = @At("RETURN"), method = "populateNoise")
	private void onChunkShape(IWorld region, Chunk chunk, CallbackInfo info) {
		ChunkShapeContext context = new ChunkShapeContext(
				new GeneratingChunkImpl(region, chunk),
				BlockImpl.of(this.getConfig().getDefaultBlock().getBlock()),
				BlockImpl.of(this.getConfig().getDefaultFluid().getBlock())
				);
		ShuttleEvents.CHUNK_SHAPE.postEvent(context);
	}
}
