package tk.valoeghese.shuttle.api.world;

import java.util.Random;

import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;

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
	/**
	 * Sets a chest with the given loot table at the specified position. Custom loot tables can be added in your mod datapack (which goes in src/main/resources).
	 * @param lootTableId the string id (registry name) of the loot table to use. For example, the end city loot is "minecraft:chests/end_city_treasure".
	 * @param random the random generator for determining the loot
	 * @return whether the block was successfully set and the loot table was successfully applied.
	 */
	boolean setLootChest(BlockPos pos, Random random, String lootTableId);
	/**
	 * Sets a chest with the given loot table at the specified position. Custom loot tables can be added in your mod datapack (which goes in src/main/resources).
	 * @param lootTableId the string id (registry name) of the loot table to use. For example, the end city loot is "minecraft:chests/end_city_treasure".
	 * @param random the random generator for determining the loot
	 * @return whether the block was successfully set and the loot table was successfully applied.
	 */
	boolean setLootChest(int x, int y, int z, Random random, String lootTableId);
	/**
	 * @return the dimension of this world.
	 */
	Dimension getDimension();
	/**
	 * @return this world's random instance. It is recommended not to use this for world generation.
	 */
	Random getRandom();
}
