package tk.valoeghese.shuttle.api.player;

import java.util.UUID;

import tk.valoeghese.shuttle.api.command.CommandExecutor;

/**
 * Interface representing a player on the server, which provides utilities to interact with the said player.
 */
public interface Player extends CommandExecutor {
	/**
	 * @return the unique identifier of the player.
	 */
	UUID getUUID();
}
