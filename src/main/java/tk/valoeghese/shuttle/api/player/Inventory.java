package tk.valoeghese.shuttle.api.player;

import net.minecraft.item.ItemStack;

public interface Inventory {
	/**
	 * @param index the index of the inventory.
	 * @return the stack at the given index.
	 */
	ItemStack getStack(int index);
}
