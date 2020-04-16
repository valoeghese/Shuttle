package tk.valoeghese.shuttle.impl.world;

import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.world.World;
import tk.valoeghese.shuttle.api.world.block.Block;

public abstract class AbstractWorldImpl<T extends IWorld> implements World {
	public AbstractWorldImpl(T parent) {
		this.parent = parent;
	}

	protected final T parent;

	@Override
	public Block getBlock(int x, int y, int z) {
		POS.set(x, y, z);
		return BlockImpl.of(this.parent.getBlockState(POS).getBlock());
	}

	@Override
	public Block getBlock(BlockPos pos) {
		return this.getBlock(pos.x, pos.y, pos.z);
	}

	@Override
	public boolean setBlock(int x, int y, int z, Block block) {
		POS.set(x, y, z);
		return this.parent.setBlockState(POS, block.getRawBlock().getDefaultState(), 3);
	}

	@Override
	public boolean setBlock(BlockPos pos, Block block) {
		return this.setBlock(pos.x, pos.y, pos.z, block);
	}

	protected static final Mutable POS = new Mutable();
}
