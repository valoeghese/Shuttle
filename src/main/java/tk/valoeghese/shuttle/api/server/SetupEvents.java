package tk.valoeghese.shuttle.api.server;

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
		 * Called at command setup.
		 */
		void setupCommands(CommandSetupContext context);
	}

	/**
	 * Context providing an interface to register commands.
	 */
	public static class CommandSetupContext implements Context<ShuttleCommandSetup> {
		public void registerCommand(Command command) {
			if (command.isSubCommand()) {
				throw new RuntimeException("Cannot directly register a sub command! Sub commands are registered when the main command is registered.");
			}

			BrigadierCommandBuilder.buildAndRegister(command);
		}
	}
}
