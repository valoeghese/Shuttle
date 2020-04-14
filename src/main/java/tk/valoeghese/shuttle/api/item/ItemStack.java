package tk.valoeghese.shuttle.api.item;

import tk.valoeghese.shuttle.impl.item.ItemStackImpl;

/**
 * Represents a stack of items: an instance of an item with a count.
 */
public interface ItemStack {
	/**
	 * @return the raw, vanilla item stack instance.
	 */
	net.minecraft.item.ItemStack getRawItemStack();
	/**
	 * @return the item this stack is of.
	 */
	Item getItem();
	/**
	 * @return get the number of items in this item stack.
	 */
	int getCount();
	/**
	 * Sets the number of items in this stack.
	 */
	void setCount(int count);
	/**
	 * Decreases the number of items in this stack by the given amount.
	 */
	void decrement(int amount);
	/**
	 * Increases the number of items in this stack by the given amount.
	 */
	void increment(int amount);
	/**
	 * @return whether this item stack is empty.
	 */
	boolean isEmpty();

	static ItemStack of(Item item, int count) {
		return ItemStackImpl.of(item, count);
	}
}
