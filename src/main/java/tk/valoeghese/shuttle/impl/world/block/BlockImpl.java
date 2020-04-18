package tk.valoeghese.shuttle.impl.world.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;

public class BlockImpl extends AbstractBlockImpl {
	private BlockImpl(Block block, String registryName) {
		super(() -> {
			Map<String, String> result = new HashMap<>();
			// get default state
			BlockState defaultState = block.getDefaultState();
			// iterate over block properties
			for (Property<?> property : defaultState.getProperties()) {
				// add property to map
				result.put(property.getName(), defaultState.get(property).toString());
			}
			return result;
		});

		this.parent = block;
		this.registryName = registryName;

		this.states.put(this.properties, this);
	}

	private final Block parent;
	private final String registryName;
	final Map<Map<String, String>, AbstractBlockImpl> states = new HashMap<>();

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
		Map<String, String> resultProperties = new HashMap<>(this.properties);
		resultProperties.put(property.getName(), property.getValue());
		return this.states.computeIfAbsent(resultProperties, l -> new BlockStateImpl(this, resultProperties));
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
