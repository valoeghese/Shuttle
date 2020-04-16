package tk.valoeghese.shuttle.api.world.block;

import tk.valoeghese.shuttle.Unstable;
import tk.valoeghese.shuttle.impl.world.BlockImpl;

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
	 * @return the vanilla registry id of the block.
	 */
	String getRegistryName();

	/**
	 * @return a block for the given registry id. For example, minecraft:dirt gives an instnace representing dirt.
	 */
	static Block get(String registryName) {
		return BlockImpl.of(registryName);
	}
}
