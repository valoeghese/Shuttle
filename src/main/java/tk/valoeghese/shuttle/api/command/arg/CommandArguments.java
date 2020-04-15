package tk.valoeghese.shuttle.api.command.arg;

import tk.valoeghese.shuttle.api.item.Item;
import tk.valoeghese.shuttle.api.world.block.Block;

/**
 * Supplier of arguments in a command.
 */
public interface CommandArguments {
	/**
	 * Gets the integer command argument for the given name, if it exists.
	 */
	int getInt(String name);
	/**
	 * Gets the boolean command argument for the given name, if it exists.
	 */
	boolean getBoolean(String name);
	/**
	 * Gets the block command argument for the given name, if it exists.
	 */
	Block getBlock(String name);
	/**
	 * Gets the item command argument for the given name, if it exists.
	 */
	Item getItem(String name);
}
