package tk.valoeghese.shuttle.impl.event;

import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.event.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.event.SetupEvents.ShuttleCommandSetup;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTickEvent;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTimerEvent;
import tk.valoeghese.shuttle.api.event.TickEvents.TickContext;
import tk.valoeghese.shuttle.impl.data.WorldDataContext;

public final class ShuttleInternalEvents {
	public static final ShuttleEventTracker<ShuttleTickEvent, TickContext> TICK = ShuttleEventTracker.of(
			ShuttleTickEvent.class,
			TickContext.class,
			(context, events) -> events.forEach(event -> event.onTick(context)));

	public static final ShuttleEventTracker<ShuttleTimerEvent, TickContext> TIMER = ShuttleEventTracker.of(
			ShuttleTimerEvent.class,
			TickContext.class,
			(context, events) -> events.forEach(event -> {
				if (context.getElapsedTicks() % event.getTimerTicks() == 0) {
					event.onTimerCountdown(context);
				}
			}));

	public static final ShuttleEventTracker<ShuttleCommandSetup, CommandSetupContext> SETUP_COMMAND = ShuttleEventTracker.of(
			ShuttleCommandSetup.class,
			CommandSetupContext.class,
			(context, events) -> events.forEach(event -> event.setupCommands(context)));

	public static final ShuttleEventTracker<ShuttleWorldDataEvent, WorldDataContext> WORLD_DATA = ShuttleEventTracker.of(
			ShuttleWorldDataEvent.class,
			WorldDataContext.class,
			(context, events) -> {
				if (context.loadingContext) {
					events.forEach(event -> {
						context.setCurrentEventId(event.pluginId());
						event.onWorldDataLoad(context);
					});
				} else {
					events.forEach(event -> event.onWorldDataSave(context));
				}
			});
}
