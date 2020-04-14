package tk.valoeghese.shuttle.impl.world;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockImpl implements tk.valoeghese.shuttle.api.world.block.Block {
	private BlockImpl(Block block) {
		this.parent = block;
	}

	private final Block parent;

	@Override
	public Block getRawBlock() {
		return this.parent;
	}

	@Override
	public String getRegistryName() {
		return Registry.BLOCK.getId(this.parent).toString();
	}

	public static BlockImpl of(Block block) {
		return BLOCKS.computeIfAbsent(block, b -> {
			BlockImpl result = new BlockImpl(b);
			REGISTRY.put(Registry.BLOCK.getId(b).toString(), result);
			return result;
		});
	}

	public static BlockImpl of(String registryName) {
		return REGISTRY.computeIfAbsent(registryName, n -> {
			Block block = Registry.BLOCK.get(new Identifier(n));
			BlockImpl result = new BlockImpl(block);
			REGISTRY.put(registryName, result);
			return result;
		});
	}

	public static final Map<Block, BlockImpl> BLOCKS = new HashMap<>();
	public static final Map<String, BlockImpl> REGISTRY = new HashMap<>();
}
