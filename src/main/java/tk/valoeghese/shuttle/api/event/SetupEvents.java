package tk.valoeghese.shuttle.api.event;

import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.impl.command.BrigadierCommandBuilder;

/**
 * Collection of event interfaces which pertain to different phases of mod setup.
 */
public final class SetupEvents {
	private SetupEvents() {
		// NO-OP
	}

	/**
	 * Event subscriber for setting up and registering commands.
	 */
	public static interface ShuttleCommandSetup extends ShuttleEvent {
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
			BrigadierCommandBuilder.buildAndRegister(command);
		}
	}
}
