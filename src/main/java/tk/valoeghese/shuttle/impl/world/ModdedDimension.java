package tk.valoeghese.shuttle.impl.world;

import net.minecraft.world.dimension.DimensionType;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;

public class ModdedDimension implements Dimension {
	private ModdedDimension(int id, String registryName, DimensionType parent) {
		this.id = id
				this.registryName =  registryName;
	}

	private final int id;
	private final String registryName;

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRegistryName() {
		// TODO Auto-generated method stub
		return null;
	}

}
