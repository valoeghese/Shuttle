package tk.valoeghese.shuttle.impl.world.block;

import java.util.HashMap;
import java.util.Map;

import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;

public class BlockStateImpl extends AbstractBlockImpl {
	public BlockStateImpl(BlockImpl parent, Map<String, String> properties) {
		super(properties);
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
}
