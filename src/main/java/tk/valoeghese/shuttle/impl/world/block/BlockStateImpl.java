package tk.valoeghese.shuttle.impl.world.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;

public class BlockStateImpl extends AbstractBlockImpl {
	BlockStateImpl(BlockImpl parent, Map<String, String> properties) {
		super(() -> properties);
		this.parent = parent;
	}

	private final BlockImpl parent;

	@Override
	public net.minecraft.block.Block getRawBlock() {
		return this.parent.getRawBlock();
	}

	@Override
	public String getRegistryName() {
		return this.parent.getRegistryName();
	}

	@Override
	public Block getDefaultState() {
		return this.parent;
	}

	@Override
	public AbstractBlockImpl withProperty(BlockProperty property) {
		Map<String, String> resultProperties = new HashMap<>(this.properties);
		resultProperties.put(property.getName(), property.getValue());
		return this.parent.states.computeIfAbsent(resultProperties, l -> new BlockStateImpl(this.parent, resultProperties));
	}

	public static Block blockOf(BlockState state) {
		if (state.getBlock().getDefaultState() == state) {
			return BlockImpl.of(state.getBlock());
		} else {
			Map<String, String> propertyMap = new HashMap<>();
			// iterate over block properties
			for (Property<?> property : state.getProperties()) {
				// add property to map
				propertyMap.put(property.getName(), state.get(property).toString());
			}

			BlockImpl block = BlockImpl.of(state.getBlock());
			return block.states
					.computeIfAbsent(propertyMap, pm -> new BlockStateImpl(block, pm));
		}
	}
}