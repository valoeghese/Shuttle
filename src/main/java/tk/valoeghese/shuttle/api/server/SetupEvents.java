package tk.valoeghese.shuttle.api.server;

import java.util.function.Consumer;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.event.Context;
import tk.valoeghese.shuttle.api.event.ShuttleEventListener;
import tk.valoeghese.shuttle.impl.command.BrigadierCommandBuilder;

/**
 * Collection of event interfaces which pertain to different phases of mod setup.
 */
public final class SetupEvents {
	private SetupEvents() {
		// NO-OP
	}

	/**
	 * Event Listener for setting up and registering commands.
	 */
	public static interface ShuttleCommandSetup extends ShuttleEventListener {
		/**
		 * Called at command setup, on the launch of the game.
		 */
		void setupCommands(CommandSetupContext context);
	}

	/**
	 * Context providing an interface to register commands.
	 */
	public static class CommandSetupContext implements Context<ShuttleCommandSetup> {
		/**
		 * Registers a command built with shuttle's command api.
		 * 
		 * @param command the shuttle command instance
		 */
		public void registerCommand(Command command) {
			if (command.isSubCommand()) {
				throw new RuntimeException("Cannot directly register a sub command! Sub commands are registered when the main command is registered.");
			}

			BrigadierCommandBuilder.buildAndRegister(command);
		}

		/**
		 * Registers a command built directly with the brigadier library to the game.
		 * 
		 * @param onlyOnDedicated if true, the command is only registered on a dedicated (i.e. not singleplayer integrated) server.
		 * @param commandSetup consumer which sets up the command from the supplied command dispatcher.
		 */
		public void registerBrigadierCommand(boolean onlyOnDedicated, Consumer<CommandDispatcher<ServerCommandSource>> commandSetup) {
			CommandRegistry.INSTANCE.register(false, commandSetup);
		}
	}
}
