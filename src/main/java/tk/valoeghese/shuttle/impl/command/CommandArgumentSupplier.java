package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import tk.valoeghese.shuttle.api.command.arg.CommandArguments;

public class CommandArgumentSupplier implements CommandArguments {
	public CommandArgumentSupplier(CommandContext<?> src) {
		this.parent = src;
	}

	private final CommandContext<?> parent;

	@Override
	public int getInt(String name) {
		return IntegerArgumentType.getInteger(this.parent, name);
	}

	@Override
	public boolean getBoolean(String name) {
		return BoolArgumentType.getBool(this.parent, name);
	}
}
