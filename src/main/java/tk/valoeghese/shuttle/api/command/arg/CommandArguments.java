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
	/**
	 * Gets the string command argument for the given name, if it exists.
	 * @return a string argument type: i.e. {@link CommandArgType#WORD}, {@link CommandArgType#STRING}, or {@link CommandArgType#GREEDY_STRING}.
	 */
	String getString(String name);
	/**
	 * Gets the float command argument for the given name, if it exists.
	 */
	float getFloat(String name);
	/**
	 * Gets the double command argument for the given name, if it exists.
	 */
	double getDouble(String name);
}
