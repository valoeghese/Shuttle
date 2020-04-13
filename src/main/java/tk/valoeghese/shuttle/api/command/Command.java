package tk.valoeghese.shuttle.api.command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.impl.command.CommandParameter;

/**
 * Interface representing a server command.
 */
public class Command {
	public Command(String name) {
		this.name = name;
	}

	private final String name;
	private final List<CommandParameter> commandArgs = new ArrayList<>();
	private int permissionLevel = 0;
	private boolean onlyPlayerCanUse = false;
	private CommandCallback callback = CommandCallback.NONE;

	/**
	 * Sets the permission level required to use this command.
	 */
	public void setPermissionLevel(PermissionLevel permissionLevel) {
		this.permissionLevel = permissionLevel.intValue();
	}

	/**
	 * Sets the command so that the sender must be a player to run the command.
	 */
	public void setSenderMustBePlayer() {
		this.onlyPlayerCanUse = true;
	}

	/**
	 * Adds an argument to the command.
	 * @param name the name of the argument.
	 * @param type the type of the argument.
	 */
	public void addCommandArg(String name, CommandArgType type) {
		this.commandArgs.add(new CommandParameter(name, type));
	}

	/**
	 * Sets the specified callback as the code the command will execute.
	 */
	public void setCallback(CommandCallback callback) {
		this.callback = callback;
	}

	/**
	 * @return the command name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the command source predicate.
	 */
	public Predicate<ServerCommandSource> getCommandSourcePredicate() {
		return cms -> cms.hasPermissionLevel(this.permissionLevel) && (this.onlyPlayerCanUse ? cms.getEntity() instanceof PlayerEntity : true); 
	}

	/**
	 * @return the arguments of the command.
	 */
	public CommandParameter[] getCommandArgs() {
		return this.commandArgs.toArray(new CommandParameter[this.commandArgs.size()]);
	}

	/**
	 * Executes the command.
	 * @param arguments the supplier of command arguments.
	 */
	public boolean execute(CommandArguments arguments) {
		return this.callback.execute(arguments);
	}
}
