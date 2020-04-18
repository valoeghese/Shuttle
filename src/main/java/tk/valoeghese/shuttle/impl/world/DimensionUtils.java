package tk.valoeghese.shuttle.impl.world;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.dimension.DimensionType;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;
import tk.valoeghese.shuttle.api.world.dimension.Dimensions;

public class DimensionUtils {
	public static Dimension dimensionOf(DimensionType vanillaType) {
		return DIMENSIONS.get(vanillaType);
	}

	public static void addModdedDimension(String registryName, DimensionType parent) {
		DIMENSIONS.put(parent, new ModdedDimension(registryName, parent));
	}

	private static final Map<DimensionType, Dimension> DIMENSIONS = new HashMap<>();

	static {
		DIMENSIONS.put(DimensionType.OVERWORLD, Dimensions.OVERWORLD);
		DIMENSIONS.put(DimensionType.THE_NETHER, Dimensions.NETHER);
		DIMENSIONS.put(DimensionType.THE_END, Dimensions.THE_END);
	}
}
