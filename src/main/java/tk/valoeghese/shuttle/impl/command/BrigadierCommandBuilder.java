package tk.valoeghese.shuttle.impl.command;

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

			for (CommandParameter arg : command.getCommandArgs()) {
				lab.then(CommandManager.argument(arg.getName(), arg.getType()));
			}

			lab.executes(src -> {
				CommandArguments arguments = new CommandArgumentSupplier(src);
				return command.execute(arguments) ? 0 : 1;
			});
		});
	}
}
