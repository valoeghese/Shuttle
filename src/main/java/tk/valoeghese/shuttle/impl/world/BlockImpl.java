package tk.valoeghese.shuttle.impl.world;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

public class BlockImpl implements tk.valoeghese.shuttle.api.world.block.Block {
	public BlockImpl(Block block) {
		this.parent = block;
	}

	private final Block parent;

	@Override
	public Block getRawBlock() {
		return this.parent;
	}

	public String getRegistryName() {
		return Registry.BLOCK.getId(this.parent).toString();
	}
}
