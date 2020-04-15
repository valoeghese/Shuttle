package tk.valoeghese.shuttle.api.player;

import tk.valoeghese.shuttle.api.item.Item;
import tk.valoeghese.shuttle.api.item.ItemStack;
import tk.valoeghese.shuttle.impl.item.ItemImpl;

/**
 * Interface representing and giving access to a player's inventory.
 */
public interface Inventory {
	/**
	 * @param index the index of the inventory.<br/>
	 * Indexes 0-8 are the hotbar slots, and the remaining indexes 9-35 are in the rest of the inventory.
	 * @return the stack at the given index.
	 */
	ItemStack getStack(int index);
	/**
	 * Gets the first index of a stack of the specified item in the inventory.
	 * @param item the item to search for.
	 * @return the first found index of the specified item, if found, or -1 if the item is not found.
	 */
	default int getIndexOf(Item item) {
		if (item == null || item == ItemImpl.AIR) {
			return this.getFirstEmptySlot();
		} else {
			return this.getIndexOf(item, -1);
		}
	}
	/**
	 * Gets the first index of a stack after the specified start index (i.e. exclusive) which is of the specified item.
	 * @param item the item to search for.
	 * @param searchStartIndex the index after which to search for items. The search <b>excludes</b> this stack index.
	 * @return the first found index of the specified item, if found, or -1 if the item is not found.
	 */
	int getIndexOf(Item item, int searchStartIndex);
	/**
	 * Gives an array of all the indices of a specified item.
	 * @param item the item to search for.
	 * @return an array of the found indices of the specified item.
	 */
	int[] getIndicesOf(Item item);
	/**
	 * @return the first index of an an empty slot in the inventory, if found, or -1 if the inventory has no empty slots.
	 */
	int getFirstEmptySlot();
	/**
	 * @return the number if items of the specified type in the inventory.
	 */
	int getCountOf(Item item);
	/**
	 * @return the slot the player of this inventory currently has selected - i.e. what slot they are using currently.
	 */
	int getSelectedSlot();
}
