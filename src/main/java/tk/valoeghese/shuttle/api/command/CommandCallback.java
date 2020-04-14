package tk.valoeghese.shuttle.api.command;

/**
 * A callback giving the code to run when the command is executed.
 */
@FunctionalInterface
public interface CommandCallback {
	boolean execute(CommandArguments arguments, CommandRuntimeInfo context);

	static CommandCallback NONE = (args, context) -> false;
}
