package tk.valoeghese.shuttle.api.world;

import tk.valoeghese.shuttle.api.event.Context;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEvent;
import tk.valoeghese.shuttle.api.player.Player;

/**
 * Events which allow for modification of world interaction.
 */
public class WorldInteractionEvents {
	private WorldInteractionEvents() {
		// NO-OP
	}

	/**
	 * Event Subscriber which is called when a player attempts to break a block.
	 */
	public static interface ShuttlePlayerBlockBreakEvent extends ShuttleEvent {
		/**
		 * Called on player block break;
		 * @return {@link EventResult#FAIL} to cancel the block break,
		 * {@link EventResult#PASS} to pass the result on to further event processing,
		 * or {@link EventResult#SUCCESS} to cancel further processing and allow the block break.
		 */
		EventResult onPlayerBlockBreak(PlayerBlockInteractionContext context);
	}

	/**
	 * Context providing information about player interaction with blocks in the world.
	 */
	public static class PlayerBlockInteractionContext implements Context<ShuttlePlayerBlockEvents> {
		public PlayerBlockInteractionContext(Player player) {
			this.player = player;
		}

		private final Player player;

		/**
		 * @return the player interacting with the world.
		 */
		public Player getPlayer() {
			return this.player;
		}
	}

	private static interface ShuttlePlayerBlockEvents extends ShuttlePlayerBlockBreakEvent {
	}
}
