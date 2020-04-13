package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.command.CommandArguments;

public class BrigadierCommandBuilder {
	public static void buildAndRegister(final Command command) {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> lab = CommandManager.literal(command.getName())
					.requires(command.getCommandSourcePredicate());

			CommandParameter[] args = command.getCommandArgs();
			int max = args.length;

			stack(lab, args, 0, max - 1, src -> {
				CommandArguments arguments = new CommandArgumentSupplier(src);
				return command.execute(arguments) ? 1 : 0;
			});

			dispatcher.register(lab);
		});
	}

	private static void stack(ArgumentBuilder<ServerCommandSource, ?> parent, CommandParameter[] args, int toStack, int target, com.mojang.brigadier.Command<ServerCommandSource> addExec) {
		ArgumentBuilder<ServerCommandSource, ?> next = CommandManager.argument(args[toStack].getName(), args[toStack].getType());

		if (toStack < target) {
			stack(next, args, toStack + 1, target, addExec);
		} else if (toStack == target) {
			next.executes(addExec);
		}

		parent.then(next);
	}
}
