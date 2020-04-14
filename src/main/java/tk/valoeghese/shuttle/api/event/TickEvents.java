package tk.valoeghese.shuttle.api.event;

import net.minecraft.server.MinecraftServer;
import tk.valoeghese.shuttle.api.ServerInfo;

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
	public static interface ShuttleTickEvent extends ShuttleEventListener {
		/**
		 * Called every server tick.
		 */
		void onTick(TickContext context);
	}

	/**
	 * Event Subscriber which is called every n number of ticks, where n is the number provided {@link ShuttleTimerEvent#getTimerTicks() here}.
	 */
	public static interface ShuttleTimerEvent extends ShuttleEventListener {
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
	public static class TickContext extends ServerInfo implements Context<ShuttleTickEvents> {
		public TickContext(MinecraftServer server) {
			super(server);
		}
	}

	private static interface ShuttleTickEvents extends ShuttleTickEvent, ShuttleTimerEvent {
	}
}
