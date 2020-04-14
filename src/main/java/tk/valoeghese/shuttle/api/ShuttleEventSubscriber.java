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
	protected Logger LOGGER;
	private String id;

	@Override
	public void onInitialize() {
		String[] names = this.getClass().getName().split("\\.");
		this.id = names[names.length - 1];
		this.LOGGER = LogManager.getLogger(this.id);
		EventRegistry.register(this);
	}

	public final void log(Object message) {
		this.LOGGER.info(message);
	}

	public final void warn(Object message) {
		this.LOGGER.warn(message);
	}

	public final void alert(Object message) {
		this.LOGGER.error(message);
	}

	public static final int ticks(int minutes, int seconds) {
		return 20 * (60 * minutes + seconds);
	}

	@Override
	public String id() {
		return this.id;
	}
}
