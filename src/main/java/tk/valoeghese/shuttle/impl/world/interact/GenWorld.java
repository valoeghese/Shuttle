package tk.valoeghese.shuttle.impl.world.interact;

import net.minecraft.world.ChunkRegion;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;
import tk.valoeghese.shuttle.impl.world.DimensionUtils;

public class GenWorld extends AbstractWorldImpl<ChunkRegion> {
	public GenWorld(ChunkRegion parent) {
		super(parent);
	}

	@Override
	public Dimension getDimension() {
		return DimensionUtils.dimensionOf(this.parent.getDimension().getType());
	}
}
