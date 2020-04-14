package tk.valoeghese.shuttle.api.command.arg;

/**
 * Supplier of arguments in a command.
 */
public interface CommandArguments {
	int getInt(String name);
	boolean getBoolean(String name);
}