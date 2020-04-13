package tk.valoeghese.shuttle.impl;

import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTickContext;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTickEvent;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTimerEvent;

public final class ShuttleEvents {
	public static final ShuttleEventTracker<ShuttleTickEvent, ShuttleTickContext> TICK = ShuttleEventTracker.of(
			ShuttleTickEvent.class,
			ShuttleTickContext.class,
			(context, events) -> events.forEach(event -> event.onTick(context)));

	public static final ShuttleEventTracker<ShuttleTimerEvent, ShuttleTickContext> TIMER = ShuttleEventTracker.of(
			ShuttleTimerEvent.class,
			ShuttleTickContext.class,
			(context, events) -> events.forEach(event -> {
				if (context.getElapsedTicks() % event.getTimerTicks() == 0) {
					event.onTimerCountdown(context);
				}
			}));
}
