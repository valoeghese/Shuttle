package tk.valoeghese.shuttle.impl;

import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.Vec3d;

public final class Wrappers {
	public static BlockPos wrap(net.minecraft.util.math.BlockPos pos) {
		return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
	}

	public static Vec3d wrap(net.minecraft.util.math.Vec3d vector) {
		return new Vec3d(vector.getX(), vector.getY(), vector.getZ());
	}
}
