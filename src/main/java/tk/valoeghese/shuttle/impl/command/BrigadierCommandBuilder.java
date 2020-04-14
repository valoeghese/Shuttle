package tk.valoeghese.shuttle.impl.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.command.CommandExecutor;
import tk.valoeghese.shuttle.api.command.CommandRuntimeInfo;
import tk.valoeghese.shuttle.api.command.arg.CommandArguments;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.impl.player.PlayerImpl;

public class BrigadierCommandBuilder {
	public static void buildAndRegister(final Command command) {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal(command.getName())
					.requires(command.getCommandSourcePredicate());

			// stack sub commands and arguments
			stackCommands(command.getSubCommands(), command, builder);

			dispatcher.register(builder);
		});
	}

	private static void stackCommands(Command[] subCommands, Command parent, ArgumentBuilder<ServerCommandSource, ?> builder) {
		// add sub commands
		for (Command command : subCommands) {
			ArgumentBuilder<ServerCommandSource, ?> next = CommandManager.literal(command.getName());
			stackCommands(command.getSubCommands(), command, next);
			builder.then(next);
		}

		// add normal arguments
		CommandParameter[] args = parent.getCommandArgs();
		int max = args.length;

		if (max == 0) {
			builder.executes(context -> {
				CommandArguments arguments = new CommandArgumentSupplier(context);
				ServerCommandSource source = context.getSource();
				// command executor
				CommandExecutor executor = (source.getEntity() instanceof Player) ? new PlayerImpl(source.getPlayer()) : new GenericCommandExecutor(source);
				return parent.execute(arguments, new CommandRuntimeInfo(executor, source.getMinecraftServer())) ? 1 : 0;
			});
		} else {
			stack(builder, args, 0, max - 1, context -> {
				CommandArguments arguments = new CommandArgumentSupplier(context);
				ServerCommandSource source = context.getSource();
				// command executor
				CommandExecutor executor = (source.getEntity() instanceof Player) ? new PlayerImpl(source.getPlayer()) : new GenericCommandExecutor(source);
				return parent.execute(arguments, new CommandRuntimeInfo(executor, source.getMinecraftServer())) ? 1 : 0;
			});
		}
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
