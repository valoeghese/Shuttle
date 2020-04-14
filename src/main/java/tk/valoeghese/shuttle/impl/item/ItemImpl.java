package tk.valoeghese.shuttle.impl.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemImpl implements tk.valoeghese.shuttle.api.item.Item {
	private ItemImpl(Item item) {
		this.parent = item;
	}

	private final Item parent;

	@Override
	public Item getRawItem() {
		return this.parent;
	}

	@Override
	public String getRegistryName() {
		return Registry.ITEM.getId(this.parent).toString();
	}

	public static ItemImpl of(Item block) {
		return ITEMS.computeIfAbsent(block, b -> {
			ItemImpl result = new ItemImpl(b);
			REGISTRY.put(Registry.ITEM.getId(b).toString(), result);
			return result;
		});
	}

	public static ItemImpl of(String registryName) {
		return REGISTRY.computeIfAbsent(registryName, n -> {
			Item item = Registry.ITEM.get(new Identifier(n));
			ItemImpl result = new ItemImpl(item);
			REGISTRY.put(registryName, result);
			return result;
		});
	}

	public static final Map<Item, ItemImpl> ITEMS = new HashMap<>();
	public static final Map<String, ItemImpl> REGISTRY = new HashMap<>();
}
