package tk.valoeghese.shuttle.impl.world.block;

import java.util.List;
import java.util.Map;

import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;

public abstract class AbstractBlockImpl implements Block {
	protected AbstractBlockImpl(Map<String, String> properties) {
		this.properties = properties;
	}

	protected final Map<String, String> properties;

	@Override
	public BlockProperty[] getStateModifiers() {
		return this.properties.toArray(new BlockProperty[this.properties.size()]);
	}
}
