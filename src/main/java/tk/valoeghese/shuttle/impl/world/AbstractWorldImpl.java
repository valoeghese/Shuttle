package tk.valoeghese.shuttle.impl.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
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
		return this.getBlock(POS);
	}

	@Override
	public Block getBlock(BlockPos pos) {
		return BlockImpl.of(this.parent.getBlockState(pos).getBlock());
	}

	@Override
	public boolean setBlock(int x, int y, int z, Block block) {
		POS.set(x, y, z);
		return this.setBlock(POS, block);
	}

	@Override
	public boolean setBlock(BlockPos pos, Block block) {
		return this.parent.setBlockState(pos, block.getRawBlock().getDefaultState(), 3);
	}

	protected static final BlockPos.Mutable POS = new BlockPos.Mutable();
}
