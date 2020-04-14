package tk.valoeghese.shuttle.impl.world;

import net.minecraft.block.Block;

public class BlockImpl implements tk.valoeghese.shuttle.api.world.block.Block {
	public BlockImpl(Block block) {
		this.parent = block;
	}

	private final Block parent;

	@Override
	public Block getRawBlock() {
		return this.parent;
	}
}
