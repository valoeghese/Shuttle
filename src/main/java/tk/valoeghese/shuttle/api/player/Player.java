package tk.valoeghese.shuttle.api.player;

import java.util.UUID;

import tk.valoeghese.shuttle.api.command.CommandExecutor;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.Vec3d;

/**
 * Interface representing a player on the server, which provides utilities to interact with the said player.
 */
public interface Player extends CommandExecutor {
	/**
	 * @return the unique identifier of the player.
	 */
	UUID getUUID();
	/**
	 * @return whether the player is in creative mode.
	 */
	boolean isInCreativeMode();
	/**
	 * @return whether the player is in spectator mode.
	 */
	boolean isInSpectatorMode();
	/**
	 * @return the player's inventory.
	 */
	Inventory getInventory();
	/**
	 * Teleports the player to the specified position.
	 */
	void teleportTo(Vec3d position);
	/**
	 * Teleports the player to the specified block position.
	 */
	void teleportTo(BlockPos position);
}
