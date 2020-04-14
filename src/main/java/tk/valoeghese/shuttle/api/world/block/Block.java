package tk.valoeghese.shuttle.api.world.block;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.valoeghese.shuttle.impl.world.BlockImpl;

public interface Block {
	net.minecraft.block.Block getRawBlock();

	static Block get(String registryName) {
		return new BlockImpl(Registry.BLOCK.get(new Identifier(registryName)));
	}
}
