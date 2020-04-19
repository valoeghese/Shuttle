package tk.valoeghese.shuttle.impl.world.interact;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.world.World;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.impl.Wrappers;

public abstract class AbstractWorldImpl<T extends IWorld> implements World {
	public AbstractWorldImpl(T parent) {
		this.parent = parent;
		this.random = parent.getRandom();
	}

	protected final T parent;
	private final Random random;

	@Override
	public Block getBlock(int x, int y, int z) {
		POS.set(x, y, z);
		return Wrappers.wrap(this.parent.getBlockState(POS));
	}

	@Override
	public Block getBlock(BlockPos pos) {
		return this.getBlock(pos.x, pos.y, pos.z);
	}

	@Override
	public boolean setBlock(int x, int y, int z, Block block) {
		POS.set(x, y, z);
		return this.parent.setBlockState(POS, Wrappers.unwrap(block), 3);
	}

	@Override
	public boolean setBlock(BlockPos pos, Block block) {
		return this.setBlock(pos.x, pos.y, pos.z, block);
	}

	@Override
	public boolean setLootChest(BlockPos pos, Random random, String lootTableId) {
		return this.setLootChest(pos.x, pos.y, pos.z, random, lootTableId);
	}

	@Override
	public boolean setLootChest(int x, int y, int z, Random random, String lootTableId) {
		POS.set(x, y, z);

		Direction direction = null;

		switch (random.nextInt(4)) {
		case 0:
			direction = Direction.NORTH;
			break;
		case 1:
			direction = Direction.EAST;
			break;
		case 2:
			direction = Direction.SOUTH;
			break;
		case 3:
			direction = Direction.WEST;
			break;
		}

		if (!this.parent.setBlockState(POS, Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, direction), 3)) {
			return false;
		}

		BlockEntity entity = this.parent.getBlockEntity(POS);

		if (entity instanceof ChestBlockEntity) {
			((ChestBlockEntity)entity).setLootTable(new Identifier(lootTableId), random.nextLong());
			return true;
		} else {
			// Should never happen.
			return false;
		}
	}

	@Override
	public Random getRandom() {
		return this.random;
	}

	protected static final Mutable POS = new Mutable();
}
