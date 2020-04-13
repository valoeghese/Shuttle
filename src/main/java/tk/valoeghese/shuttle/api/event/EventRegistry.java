package tk.valoeghese.shuttle.api.event;

import java.util.ArrayList;
import java.util.List;

import tk.valoeghese.shuttle.api.ShuttleEventSubscriber;
import tk.valoeghese.shuttle.impl.event.ShuttleEventTracker;
import tk.valoeghese.shuttle.impl.event.ShuttleEvents;

/**
 * API class where event subscribers are registered. Automatically done by {@link ShuttleEventSubscriber}.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class EventRegistry {
	private EventRegistry() {
		// NO-OP
	}

	private static List<ShuttleEventTracker> events = new ArrayList<>();

	public static <T extends ShuttleEvent> void register(ShuttleEvent in) {
		final Class clazz = in.getClass();

		events.forEach(tracker -> {
			if (tracker.getEventClass().isAssignableFrom(clazz)) {
				tracker.subscribe(in);
			}
		});
	}

	static {
		// setup
		events.add(ShuttleEvents.SETUP_COMMAND);
		// tick loop
		events.add(ShuttleEvents.TICK);
		events.add(ShuttleEvents.TIMER);
	}
}
