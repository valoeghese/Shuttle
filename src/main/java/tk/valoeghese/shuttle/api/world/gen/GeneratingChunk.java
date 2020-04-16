package tk.valoeghese.shuttle.api.world.gen;

import tk.valoeghese.shuttle.api.world.block.Block;

/**
 * Represents a chunk which is still generating.
 */
public interface GeneratingChunk {
	/**
	 * Retrieves the block currently at that position in the chunk.
	 * @param x the x coordinate inside the chunk. Should be from 0 to 15.
	 * @param y the y coordinate.
	 * @param z the z coordinate inside the chunk. Should be from 0 to 15.
	 * @return the block at the specified position inside the chunk.
	 */
	Block getBlock(int x, int y, int z);
	/**
	 * Sets the block at that position in the chunk to the specified block.
	 * @param x the x coordinate inside the chunk. Should be from 0 to 15.
	 * @param y the y coordinate.
	 * @param z the z coordinate inside the chunk. Should be from 0 to 15.
	 */
	void setBlock(int x, int y, int z, Block block);
	/**
	 * @return the world seed.
	 */
	long getSeed();
	/**
	 * @return the sea level of the chunk.
	 */
	int getSeaLevel();
}
