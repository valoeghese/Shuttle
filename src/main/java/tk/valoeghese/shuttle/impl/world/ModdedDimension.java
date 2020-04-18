package tk.valoeghese.shuttle.impl.world;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;

public final class ModdedDimension implements Dimension {
	ModdedDimension(String registryName, DimensionType parent) {
		this.registryName =  registryName;
		this.parent = parent;
	}

	private final String registryName;
	private final DimensionType parent;

	@Override
	public int getId() {
		return Registry.DIMENSION_TYPE.getRawId(this.parent);
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}
}
