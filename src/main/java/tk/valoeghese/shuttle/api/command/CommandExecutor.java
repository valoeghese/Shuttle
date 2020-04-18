package tk.valoeghese.shuttle.api.command;

import tk.valoeghese.shuttle.api.chat.ChatMessageBuilder;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.ChunkPos;
import tk.valoeghese.shuttle.api.util.Vec3d;

/**
 * Represents the executor of a command.
 */
public interface CommandExecutor {
	/**
	 * Sends the specified chat message to this player / command executor.
	 */
	void sendMessage(String message);
	/**
	 * Sends the specified chat message to this player / command executor.
	 */
	default void sendMessage(ChatMessageBuilder message) {
		this.sendMessage(message.toString());
	}
	/**
	 * @return the name. If this is a player, returns the player's username.
	 */
	String getName();
	/**
	 * @return if an entity/player, the chunk coordinates of the entity/player. Otherwise returns 0, 0.
	 */
	ChunkPos getChunkCoordinates();
	/**
	 * @return if an entity/player, the block coordinates of the entity/player. Otherwise returns 0, 0, 0.
	 */
	BlockPos getBlockCoordinates();
	/**
	 * @return if an entity/player, the coordinates of the entity/player. Otherwise returns 0, 0, 0.
	 */
	Vec3d getCoordinates();
	/**
	 * @return the command permission level of the executor.
	 */
	PermissionLevel getPermissionLevel();
}
