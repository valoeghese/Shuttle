package tk.valoeghese.shuttle.impl.world.block;

import java.util.ArrayList;
import java.util.List;

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
		List<BlockProperty> resultList = new ArrayList<>(this.properties);
		resultList.add(property);
		return this.states.computeIfAbsent(resultList, l -> new BlockStateImpl(this, resultList));
	}
}
