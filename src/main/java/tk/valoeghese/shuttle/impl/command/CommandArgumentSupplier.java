package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.arguments.BlockStateArgumentType;
import net.minecraft.command.arguments.ItemStackArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.api.command.arg.CommandArguments;
import tk.valoeghese.shuttle.api.item.Item;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.impl.Wrappers;
import tk.valoeghese.shuttle.impl.item.ItemImpl;

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
		return Wrappers.wrap(BlockStateArgumentType.getBlockState(this.parent, name).getBlockState());
	}

	@Override
	public Item getItem(String name) {
		return ItemImpl.of(ItemStackArgumentType.getItemStackArgument(this.parent, name).getItem());
	}

	@Override
	public String getString(String name) {
		return StringArgumentType.getString(this.parent, name);
	}

	@Override
	public float getFloat(String name) {
		return FloatArgumentType.getFloat(this.parent, name);
	}

	@Override
	public double getDouble(String name) {
		return DoubleArgumentType.getDouble(this.parent, name);
	}
}
