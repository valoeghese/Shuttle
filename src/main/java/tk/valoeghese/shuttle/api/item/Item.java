package tk.valoeghese.shuttle.api.item;

import tk.valoeghese.shuttle.Unstable;
import tk.valoeghese.shuttle.impl.item.ItemImpl;

/**
 * Represents a minecraft item.
 */
public interface Item {
	/**
	 * @return the raw, vanilla item.
	 */
	@Unstable
	net.minecraft.item.Item getRawItem();
	/**
	 * @return the vanilla registry id of the item.
	 */
	String getRegistryName();
	/**
	 * @return the maximum number of items a stack of this item can hold.
	 */
	int getMaxStackSize();

	/**
	 * @return an item for the given registry id. For example, minecraft:stick gives an instnace representing the stick item.
	 */
	static Item get(String registryName) {
		return ItemImpl.of(registryName);
	}
}
