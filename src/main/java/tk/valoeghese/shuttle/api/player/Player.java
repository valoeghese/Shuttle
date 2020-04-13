package tk.valoeghese.shuttle.api.player;

import java.util.UUID;

/**
 * Interface representing a player on the server, which provides utilities to interact with the said player.
 */
public interface Player {
	/**
	 * @return the username of the player.
	 */
	String getUsername();
	/**
	 * @return the unique identifier of the player.
	 */
	UUID getUUID();
	/**
	 * Sends the specified chat message to the player.
	 */
	void sendMessage(String message);
	/**
	 * Sends the specified chat message to the player.
	 */
	void sendMessage(ChatMessage message);
}
