package tk.valoeghese.shuttle.impl.world.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;

public class BlockImpl extends AbstractBlockImpl {
	private BlockImpl(Block block, String registryName) {
		super(new ArrayList<>());
		this.parent = block;
		this.registryName = registryName;
		this.states.put(this.properties, this);
	}

	private final Block parent;
	private final String registryName;
	final Map<List<BlockProperty>, AbstractBlockImpl> states = new HashMap<>();

	@Override
	public Block getRawBlock() {
		return this.parent;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}

	@Override
	public tk.valoeghese.shuttle.api.world.block.Block getDefaultState() {
		return this;
	}

	@Override
	public AbstractBlockImpl withProperty(BlockProperty property) {
		List<BlockProperty> resultList = new ArrayList<>();
		resultList.add(property);
		return this.states.computeIfAbsent(resultList, l -> new BlockStateImpl(this, resultList));
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
