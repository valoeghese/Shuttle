package tk.valoeghese.shuttle.api.event;

/**
 * Base event listener for shuttle events.
 */
public interface ShuttleEventListener {
	/**
	 * @return the id of the plugin this event listener is of.
	 */
	String pluginId();
}
