package tk.valoeghese.shuttle.impl;

import net.minecraft.block.BlockState;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.Vec3d;
import tk.valoeghese.shuttle.api.world.block.Block;

public final class Wrappers {
	public static BlockPos wrap(net.minecraft.util.math.BlockPos pos) {
		return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
	}

	public static Vec3d wrap(net.minecraft.util.math.Vec3d vector) {
		return new Vec3d(vector.getX(), vector.getY(), vector.getZ());
	}

	public static BlockState unwrap(Block block) {
		
	}
}
