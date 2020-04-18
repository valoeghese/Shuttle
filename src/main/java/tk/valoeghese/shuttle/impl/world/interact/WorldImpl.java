package tk.valoeghese.shuttle.impl.world.interact;

import net.minecraft.server.world.ServerWorld;
import tk.valoeghese.shuttle.api.world.dimension.Dimension;
import tk.valoeghese.shuttle.impl.world.DimensionUtils;

public class WorldImpl extends AbstractWorldImpl<ServerWorld> {
	public WorldImpl(ServerWorld parent) {
		super(parent);

		this.dimension = DimensionUtils.dimensionOf(parent.getDimension().getType());
	}

	private final Dimension dimension;

	@Override
	public Dimension getDimension() {
		return this.dimension;
	}
}
