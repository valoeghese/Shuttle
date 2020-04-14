package tk.valoeghese.shuttle.impl.world;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockImpl implements tk.valoeghese.shuttle.api.world.block.Block {
	private BlockImpl(Block block, String registryName) {
		this.parent = block;
		this.registryName = registryName;
	}

	private final Block parent;
	private final String registryName;

	@Override
	public Block getRawBlock() {
		return this.parent;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}

	public static BlockImpl of(Block block) {
		return BLOCKS.computeIfAbsent(block, b -> {
			String registryName = Registry.BLOCK.getId(b).toString();
			BlockImpl result = new BlockImpl(b, registryName);
			REGISTRY.put(registryName, result);
			return result;
		});
	}

	public static BlockImpl of(String registryName) {
		return REGISTRY.computeIfAbsent(registryName, n -> {
			Block block = Registry.BLOCK.get(new Identifier(n));
			BlockImpl result = new BlockImpl(block, registryName);
			BLOCKS.put(block, result);
			return result;
		});
	}

	public static final Map<Block, BlockImpl> BLOCKS = new HashMap<>();
	public static final Map<String, BlockImpl> REGISTRY = new HashMap<>();
}
