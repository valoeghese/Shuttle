package tk.valoeghese.shuttle.api.world;

import net.minecraft.util.math.BlockPos;
import tk.valoeghese.shuttle.api.world.block.Block;

/**
 * Represents a part of the world that contains the chunks, blocks, etc for a specific dimension.<br/>
 * For the world as a whole, see {@link GlobalWorld}.
 * @apiNote the name World was chosen for this class over Dimension, DimensionalWorld, Level, among other names because th
 * name "World" is already standard in the modding community for representing this.
 */
public interface World {
	/**
	 * @return the block at the specified position.
	 */
	Block getBlock(int x, int y, int z);
	/**
	 * @return the block at the specified position.
	 */
	Block getBlock(BlockPos pos);
	/**
	 * Sets the block at the specified position.
	 * @param block the block to set.
	 * @return whether the block was successfully set.
	 */
	boolean setBlock(int x, int y, int z, Block block);
	/**
	 * Sets the block at the specified position.
	 * @param block the block to set.
	 * @return whether the block was successfully set.
	 */
	boolean setBlock(BlockPos pos, Block block);
}
