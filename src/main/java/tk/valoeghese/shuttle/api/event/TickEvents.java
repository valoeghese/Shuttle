package tk.valoeghese.shuttle.api.event;

import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.impl.PlayerImpl;

/**
 * Collection of event interfaces which pertain to the server tick loop.
 */
public final class TickEvents {
	private TickEvents() {
		// NO-OP
	}

	/**
	 * Event Subscriber which is called every tick.
	 */
	public static interface ShuttleTickEvent extends ShuttleEvent {
		/**
		 * Called every server tick.
		 */
		void onTick(TickContext context);
	}

	/**
	 * Event Subscriber which is called every n number of ticks, where n is the number provided {@link ShuttleTimerEvent#getTimerTicks() here}.
	 */
	public static interface ShuttleTimerEvent extends ShuttleEvent {
		/**
		 * Called every {@link ShuttleTimerEvent#getTimerTicks n} number of ticks.
		 */
		void onTimerCountdown(TickContext context);
		/**
		 * Provides the number of ticks, n, where {@link ShuttleTimerEvent#onTimerCountdown(TickContext) the event method} is called every n ticks.
		 */
		int getTimerTicks();
	}

	/**
	 * Context providing information about the {@link MinecraftServer server} at the current tick.
	 */
	public static class TickContext implements Context<ShuttleTickEvents> {
		public TickContext(MinecraftServer server) {
			List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
			final int playerCount = players.size();
			this.players = new Player[playerCount];

			for (int i = 0; i < playerCount; ++i) {
				this.players[i] = new PlayerImpl(players.get(i));
			}

			this.elapsedTicks = server.getTicks();
		}

		private final Player[] players;
		private final int elapsedTicks;

		/**
		 * @return an array of players on the server.
		 */
		public Player[] getPlayers() {
			return this.players;
		}

		/**
		 * @return the number of elapsed ticks since server start.
		 */
		public int getElapsedTicks() {
			return this.elapsedTicks;
		}
	}

	private static interface ShuttleTickEvents extends ShuttleTickEvent, ShuttleTimerEvent {
	}
}
