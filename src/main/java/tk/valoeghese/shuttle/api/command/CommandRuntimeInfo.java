package tk.valoeghese.shuttle.api.command;

import net.minecraft.server.MinecraftServer;
import tk.valoeghese.shuttle.api.ServerInfo;

/**
 * Class providing information about the server and command executor at command runtime.
 */
public class CommandRuntimeInfo extends ServerInfo {
	public CommandRuntimeInfo(CommandExecutor executor, MinecraftServer server) {
		super(server);

		this.executor = executor;
	}

	private final CommandExecutor executor;

	/**
	 * @return the executor of the command.
	 */
	public CommandExecutor getExecutor() {
		return this.executor;
	}
}
