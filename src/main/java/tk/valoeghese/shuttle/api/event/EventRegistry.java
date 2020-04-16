package tk.valoeghese.shuttle.api.event;

import java.util.ArrayList;
import java.util.List;

import tk.valoeghese.shuttle.api.ShuttlePlugin;

/**
 * API class where event subscribers are registered. Automatically done by {@link ShuttlePlugin}.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class EventRegistry {
	private EventRegistry() {
		// NO-OP
	}

	private static List<ShuttleEventTracker> events = new ArrayList<>();
	private static List<ShuttleEventListener> listeners = new ArrayList<>();

	/**
	 * Registers the specified shuttle event listener.
	 */
	public static <T extends ShuttleEventListener> void registerListener(ShuttleEventListener in) {
		final Class clazz = in.getClass();

		events.forEach(tracker -> {
			if (tracker.getEventClass().isAssignableFrom(clazz)) {
				tracker.subscribe(in);
			}
		});

		listeners.add(in);
	}

	/**
	 * Adds the event tracker to the event registry.
	 */
	public static void registerEventTracker(ShuttleEventTracker tracker) {
		events.add(tracker);

		// register listeners in retrograde
		listeners.forEach(listener -> {
			if (tracker.getEventClass().isAssignableFrom(listener.getClass())) {
				tracker.subscribe(listener);
			}
		});
	}

	static {
		// setup
		registerEventTracker(ShuttleEvents.SETUP_COMMAND);
		// tick loop
		registerEventTracker(ShuttleEvents.TICK);
		registerEventTracker(ShuttleEvents.TIMER);
		// data
		registerEventTracker(ShuttleEvents.WORLD_DATA);
		// world interaction
		registerEventTracker(ShuttleEvents.PLAYER_BLOCK_BREAK);
		registerEventTracker(ShuttleEvents.PLAYER_BLOCK_PLACE);
	}
}
