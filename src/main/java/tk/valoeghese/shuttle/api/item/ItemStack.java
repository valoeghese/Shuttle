package tk.valoeghese.shuttle.api.item;

public interface ItemStack {
	Item getItem();
	int getCount();
	void setCount(int count);
	void decrement(int amount);
	void increment(int amount);
	boolean isEmpty();
}
