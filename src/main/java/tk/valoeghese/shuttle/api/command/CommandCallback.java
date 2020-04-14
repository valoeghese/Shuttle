package tk.valoeghese.shuttle.api.command;

import tk.valoeghese.shuttle.api.event.TickEvents.TickContext;

/**
 * A callback giving the code to run when the command is executed.
 */
@FunctionalInterface
public interface CommandCallback {
	boolean execute(CommandArguments arguments, TickContext context);

	static CommandCallback NONE = (args, context) -> false;
}
