package tk.valoeghese.shuttle.impl.world;

import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.chunk.Chunk;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.gen.GeneratingChunk;

public class GeneratingChunkImpl implements GeneratingChunk {
	public GeneratingChunkImpl(ChunkRegion region, Chunk chunk) {
		this.chunk = chunk;
		this.seed = region.getSeed();
		this.seaLevel = region.getSeaLevel();
	}

	private final Chunk chunk;
	private final long seed;
	private final int seaLevel;

	@Override
	public Block getBlock(int x, int y, int z) {
		POS.set(x, y, z);
		return BlockImpl.of(this.chunk.getBlockState(POS).getBlock());
	}

	@Override
	public void setBlock(int x, int y, int z, Block block) {
		POS.set(x, y, z);
		this.chunk.setBlockState(POS, block.getRawBlock().getDefaultState(), false);
	}

	@Override
	public long getSeed() {
		return this.seed;
	}

	@Override
	public int getSeaLevel() {
		return this.seaLevel;
	}

	private static final Mutable POS = new Mutable();
}
