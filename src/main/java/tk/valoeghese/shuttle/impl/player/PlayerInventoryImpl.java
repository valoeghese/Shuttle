package tk.valoeghese.shuttle.impl.player;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import tk.valoeghese.shuttle.api.item.Item;
import tk.valoeghese.shuttle.api.item.ItemStack;
import tk.valoeghese.shuttle.api.player.Inventory;
import tk.valoeghese.shuttle.impl.item.ItemStackImpl;

public class PlayerInventoryImpl implements Inventory {
	public PlayerInventoryImpl(PlayerInventory parent) {
		this.parent = parent;
	}

	private final PlayerInventory parent;

	@Override
	public ItemStack getStack(int index) {
		return ItemStackImpl.of(parent.getInvStack(index));
	}

	@Override
	public int getIndexOf(Item item, int searchStartIndex) {
		net.minecraft.item.Item raw = item == null ? Items.AIR : item.getRawItem();
		return this.getIndexOf(raw, searchStartIndex);
	}

	private int getIndexOf(net.minecraft.item.Item item, int searchStartIndex) {
		if (searchStartIndex < 35) {
			// search for item stack
			for (int i = searchStartIndex + 1; i < 35; ++i) {
				net.minecraft.item.ItemStack stack = this.parent.getInvStack(i);

				if (stack.getItem() == item) {
					// if the item is identical, return the item.
					return i;
				}
			}
		}

		// return -1 if not found
		return -1;
	}

	@Override
	public int[] getIndicesOf(Item item) {
		net.minecraft.item.Item raw = item == null ? Items.AIR : item.getRawItem();
		IntList result = new IntArrayList();
		int i = -1;

		while (true) {
			i = this.getIndexOf(raw, i);

			if (i == -1) {
				return result.toIntArray();
			} else {
				result.add(i);
			}
		}
	}

	@Override
	public int getFirstEmptySlot() {
		return this.parent.getEmptySlot();
	}

	@Override
	public int getCountOf(Item item) {
		return this.parent.countInInv(item.getRawItem());
	}

	@Override
	public int getSelectedSlot() {
		return this.parent.selectedSlot;
	}
}
