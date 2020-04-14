package tk.valoeghese.shuttle.api.player;

public interface Inventory {
	/**
	 * @return the stack at the given index.
	 */
	ItemStack getStack(int index);
}
