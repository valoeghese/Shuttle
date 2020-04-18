package tk.valoeghese.shuttle.impl.world.decorator;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import tk.valoeghese.shuttle.api.world.gen.Generator;
import tk.valoeghese.shuttle.impl.Wrappers;
import tk.valoeghese.shuttle.impl.world.interact.GenWorld;

public class GeneratorFeature extends Feature<DefaultFeatureConfig> {
	public GeneratorFeature(Generator generator) {
		super(DefaultFeatureConfig::deserialize);
		this.generator = generator;
	}

	private final Generator generator;

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		ChunkRegion region = (ChunkRegion) world;
		return this.generator.generate(new GenWorld(region), random, region.getCenterChunkX() << 4, region.getCenterChunkZ() << 4, Wrappers.wrap(pos));
	}
}
