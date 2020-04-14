package tk.valoeghese.shuttle.impl.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemImpl implements tk.valoeghese.shuttle.api.item.Item {
	private ItemImpl(Item item, String registryName) {
		this.parent = item;
		this.registryName = registryName;
	}

	private final Item parent;
	private final String registryName;

	@Override
	public Item getRawItem() {
		return this.parent;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}

	@Override
	public int getMaxStackSize() {
		return this.parent.getMaxCount();
	}

	public static ItemImpl of(Item block) {
		return ITEMS.computeIfAbsent(block, b -> {
			String registryName = Registry.ITEM.getId(b).toString();
			ItemImpl result = new ItemImpl(b, registryName);
			REGISTRY.put(registryName, result);
			return result;
		});
	}

	public static ItemImpl of(String registryName) {
		return REGISTRY.computeIfAbsent(registryName, n -> {
			Item item = Registry.ITEM.get(new Identifier(n));
			ItemImpl result = new ItemImpl(item, n);
			ITEMS.put(item, result);
			return result;
		});
	}

	public static final Map<Item, ItemImpl> ITEMS = new HashMap<>();
	public static final Map<String, ItemImpl> REGISTRY = new HashMap<>();
}
