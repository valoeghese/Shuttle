package tk.valoeghese.shuttle.impl.world.interact;

import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;
import tk.valoeghese.shuttle.api.util.ChunkPos;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;
import tk.valoeghese.shuttle.api.world.gen.GeneratingChunk;
import tk.valoeghese.shuttle.impl.Wrappers;
import tk.valoeghese.shuttle.impl.world.DimensionUtils;

public class GeneratingChunkImpl implements GeneratingChunk {
	public GeneratingChunkImpl(IWorld region, Chunk chunk) {
		this.chunk = chunk;
		this.dimension = DimensionUtils.dimensionOf(region.getDimension().getType());
		this.seed = region.getSeed();
		this.seaLevel = region.getSeaLevel();
		this.chunkPos = new ChunkPos(chunk.getPos().x, chunk.getPos().z);
	}

	private final Chunk chunk;
	private final Dimension dimension;
	private final long seed;
	private final int seaLevel;
	private final ChunkPos chunkPos;
	private boolean modified = false;

	@Override
	public Block getBlock(int x, int y, int z) {
		POS.set(x, y, z);
		return Wrappers.wrap(this.chunk.getBlockState(POS));
	}

	@Override
	public void setBlock(int x, int y, int z, Block block) {
		this.modified = true;
		POS.set(x, y, z);
		this.chunk.setBlockState(POS, Wrappers.unwrap(block), false);
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
		return this.chunkPos.z;
	}

	@Override
	public ChunkPos getChunkPos() {
		return this.chunkPos;
	}

	public boolean isModified() {
		return this.modified;
	}

	private static final Mutable POS = new Mutable();
}
