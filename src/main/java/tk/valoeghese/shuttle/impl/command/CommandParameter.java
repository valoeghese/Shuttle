package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.command.arguments.BlockStateArgumentType;
import tk.valoeghese.shuttle.api.command.arg.CommandArgType;

public class CommandParameter {
	public CommandParameter(String name, CommandArgType type) {
		this.name = name;
		this.type = brigadierify(type);
	}

	private final String name;
	private final ArgumentType<?> type;

	public String getName() {
		return this.name;
	}

	public ArgumentType<?> getType() {
		return this.type;
	}

	private static ArgumentType<?> brigadierify(CommandArgType shuttleType) {
		switch (shuttleType) {
		case INTEGER:
			return IntegerArgumentType.integer();
		case BOOLEAN:
			return BoolArgumentType.bool();
		case BLOCK:
			return BlockStateArgumentType.blockState();
		default:
			return null;
		}
	}
}
