package tk.valoeghese.shuttle.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import tk.valoeghese.shuttle.api.event.EventRegistry;
import tk.valoeghese.shuttle.api.event.ShuttleEvent;

/**
 * {@link ModInitializer} which registers itself to the {@link EventRegistry shuttle event registry} and
 * provides {@link Logger logging} and other utility methods.
 */
public abstract class ShuttleEventSubscriber implements ModInitializer, ShuttleEvent {
	protected static Logger LOGGER;

	@Override
	public void onInitialize() {
		String[] names = this.getClass().getName().split("\\.");
		LOGGER = LogManager.getLogger(names[names.length - 1]);
		EventRegistry.register(this);
	}

	public static final void log(String message) {
		LOGGER.info(message);
	}

	public static final void warn(String message) {
		LOGGER.warn(message);
	}

	public static final void alert(String message) {
		LOGGER.error(message);
	}

	public static final int ticks(int minutes, int seconds) {
		return 20 * (60 * minutes + seconds);
	}
}
