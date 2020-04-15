package tk.valoeghese.shuttle.impl.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import tk.valoeghese.shuttle.api.item.Item;

public class ItemStackImpl implements tk.valoeghese.shuttle.api.item.ItemStack {
	public ItemStackImpl(ItemStack parent, Item item) {
		this.parent = parent;
		this.item = item;
	}

	private final ItemStack parent;
	private final Item item;

	@Override
	public ItemStack getRawItemStack() {
		return this.parent;
	}

	@Override
	public Item getItem() {
		return this.item;
	}

	@Override
	public int getCount() {
		return this.parent.getCount();
	}

	@Override
	public void setCount(int count) {
		this.parent.setCount(count);
	}

	@Override
	public void decrement(int amount) {
		this.parent.decrement(amount);
	}

	@Override
	public void increment(int amount) {
		this.parent.increment(amount);
	}

	@Override
	public boolean isEmpty() {
		return this.parent.isEmpty();
	}

	public static ItemStackImpl of(Item item, int count) {
		return item == null ? new ItemStackImpl(ItemStack.EMPTY, ItemImpl.of(Items.AIR)) : new ItemStackImpl(new ItemStack(item.getRawItem(), count), item);
	}

	public static ItemStackImpl of(ItemStack stack) {
		return stack.isEmpty() ? EMPTY : new ItemStackImpl(stack, ItemImpl.of(stack.getItem()));
	}

	public static ItemStackImpl EMPTY = of(null, 0);
}
