package tk.valoeghese.shuttle.impl.world;

import net.minecraft.world.dimension.DimensionType;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;

public final class ModdedDimension implements Dimension {
	private ModdedDimension(int id, String registryName, DimensionType parent) {
		this.id = id;
		this.registryName =  registryName;
	}

	private final int id;
	private final String registryName;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}

	public static Dimension createNew(int id, String registryName, DimensionType parent) {
		return new ModdedDimension(id, registryName, parent);
	}
}
