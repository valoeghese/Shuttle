package tk.valoeghese.shuttle.impl.world.block;

import java.util.Map;
import java.util.function.Supplier;

import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;

public abstract class AbstractBlockImpl implements Block {
	protected AbstractBlockImpl(Supplier<Map<String, String>> properties) {
		this.properties = properties.get();
		this.propertyArray = this.properties.entrySet()
				.stream()
				.map(entry -> new BlockProperty(entry.getKey(), entry.getValue()))
				.toArray(BlockProperty[]::new);
	}

	protected final Map<String, String> properties;
	protected final BlockProperty[] propertyArray;

	@Override
	public BlockProperty[] getStateModifiers() {
		return this.propertyArray;
	}

	@Override
	public boolean isSameTypeAs(Block other) {
		return this.getRawBlock() == other.getRawBlock();
	}
}
