package tk.valoeghese.shuttle.impl.world;

import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;
import tk.valoeghese.shuttle.api.util.Vec2i;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;
import tk.valoeghese.shuttle.api.world.gen.GeneratingChunk;

public class GeneratingChunkImpl implements GeneratingChunk {
	public GeneratingChunkImpl(IWorld region, Chunk chunk) {
		this.chunk = chunk;
		this.dimension = DimensionUtils.dimensionOf(region.getDimension().getType());
		this.seed = region.getSeed();
		this.seaLevel = region.getSeaLevel();
		this.chunkPos = new Vec2i(chunk.getPos().x, chunk.getPos().z);
	}

	private final Chunk chunk;
	private final Dimension dimension;
	private final long seed;
	private final int seaLevel;
	private final Vec2i chunkPos;

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

	@Override
	public Dimension getDimension() {
		return this.dimension;
	}

	@Override
	public int getChunkX() {
		return this.chunkPos.x;
	}

	@Override
	public int getChunkZ() {
		return this.chunkPos.y;
	}

	@Override
	public Vec2i getChunkPos() {
		return this.chunkPos;
	}

	private static final Mutable POS = new Mutable();
}
