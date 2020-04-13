package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

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

			RequiredArgumentBuilder<ServerCommandSource, ?> builder = null;

			for (CommandParameter arg : command.getCommandArgs()) {
				if (builder == null) {
					lab.then(builder = CommandManager.argument(arg.getName(), arg.getType()));
				} else {
					final RequiredArgumentBuilder<ServerCommandSource, ?> oldBuilder = builder;
					oldBuilder.then(builder = CommandManager.argument(arg.getName(), arg.getType()));
				}
			}

			(builder == null ? lab : builder).executes(src -> {
				CommandArguments arguments = new CommandArgumentSupplier(src);
				return command.execute(arguments) ? 1 : 0;
			});

			dispatcher.register(lab);
		});
	}
}
