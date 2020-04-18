package tk.valoeghese.shuttle.api.world.block;

import tk.valoeghese.shuttle.Unstable;
import tk.valoeghese.shuttle.impl.world.block.BlockImpl;

/**
 * Represents a minecraft block.
 */
public interface Block {
	/**
	 * @return the raw, vanilla block.
	 */
	@Unstable
	net.minecraft.block.Block getRawBlock();
	/**
	 * @return the default state of the block, without any modifying properties.
	 */
	Block getDefaultState();
	/**
	 * @return an array of {@link BlockProperty} modifiers on the block.
	 */
	BlockProperty[] getStateModifiers();
	/**
	 * @param property the property to add to the block.
	 * @return the block with the specified property added.
	 * @apiNote not all properties will have an effect on the block: only the properties implemented for the block by minecraft.
	 */
	Block withProperty(BlockProperty property);
	/**
	 * @return the vanilla registry id of the block.
	 */
	String getRegistryName();
	/**
	 * @param other the block to compare with
	 * @return whether the blocks are of the same type, regardless of state
	 */
	boolean isSameTypeAs(Block other);

	/**
	 * @return a block for the given registry id. For example, minecraft:dirt gives an instance representing dirt.
	 */
	static Block get(String registryName) {
		return BlockImpl.of(registryName);
	}
}
