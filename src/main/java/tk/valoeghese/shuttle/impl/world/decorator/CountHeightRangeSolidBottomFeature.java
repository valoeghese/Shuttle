package tk.valoeghese.shuttle.impl.world.decorator;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
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

public class CountHeightRangeSolidBottomFeature extends Feature<DefaultFeatureConfig> {
	public CountHeightRangeSolidBottomFeature(int minY, Generator generator) {
		super(DefaultFeatureConfig::deserialize);
		this.generator = generator;
		this.minY = minY;
	}

	private final int minY;
	private final Generator generator;

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		int attempts = 25;
		int y = pos.getY();

		BlockPos.Mutable mutPos = new BlockPos.Mutable(pos);

		while (mutPos.getY() > this.minY && attempts > 0) {
			BlockState down = world.getBlockState(mutPos.down());
			if (!down.isAir() && !(down.getBlock() instanceof FluidBlock) && world.getBlockState(mutPos).isAir()) {
				ChunkRegion region = (ChunkRegion) world;
				return this.generator.generate(new GenWorld(region), random, region.getCenterChunkX() << 4, region.getCenterChunkZ() << 4, Wrappers.wrap(mutPos));
			}

			mutPos.setY(--y);
			attempts--;
		}

		return false;
	}

}
