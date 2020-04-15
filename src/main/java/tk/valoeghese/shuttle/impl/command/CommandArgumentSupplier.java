package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.arguments.BlockStateArgumentType;
import net.minecraft.command.arguments.ItemStackArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.api.command.arg.CommandArguments;
import tk.valoeghese.shuttle.api.item.Item;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.impl.item.ItemImpl;
import tk.valoeghese.shuttle.impl.world.BlockImpl;

public class CommandArgumentSupplier implements CommandArguments {
	public CommandArgumentSupplier(CommandContext<ServerCommandSource> src) {
		this.parent = src;
	}

	private final CommandContext<ServerCommandSource> parent;

	@Override
	public int getInt(String name) {
		return IntegerArgumentType.getInteger(this.parent, name);
	}

	@Override
	public boolean getBoolean(String name) {
		return BoolArgumentType.getBool(this.parent, name);
	}

	@Override
	public Block getBlock(String name) {
		return BlockImpl.of(BlockStateArgumentType.getBlockState(this.parent, name).getBlockState().getBlock());
	}

	@Override
	public Item getItem(String name) {
		return ItemImpl.of(ItemStackArgumentType.getItemStackArgument(this.parent, name).getItem());
	}
}
