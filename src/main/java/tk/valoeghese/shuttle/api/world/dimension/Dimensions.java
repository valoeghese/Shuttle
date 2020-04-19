package tk.valoeghese.shuttle.api.world.dimension;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import tk.valoeghese.shuttle.Unstable;

/**
 * Enum representing the vanilla dimensions.
 */
public enum Dimensions implements Dimension {
	OVERWORLD(DimensionType.OVERWORLD),
	NETHER(DimensionType.THE_NETHER),
	THE_END(DimensionType.THE_END);

	private Dimensions(DimensionType parent) {
		this.parent = parent;
		this.registryName = Registry.DIMENSION_TYPE.getId(this.parent).toString();
	}

	private final DimensionType parent;
	private final String registryName;

	@Override
	@Unstable
	public DimensionType getRawDimension() {
		return this.parent;
	}

	@Override
	public int getId() {
		return Registry.DIMENSION_TYPE.getRawId(this.parent);
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}
}
