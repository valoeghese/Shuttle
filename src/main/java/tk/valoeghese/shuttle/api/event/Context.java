package tk.valoeghese.shuttle.api.event;

public interface Context<T extends ShuttleEventListener> {
	/**
	 * Used by the event tracker to notify the event provider of something. Typically to do with success/fail of the event.
	 */
	default void notifyEvent(int item) {
		// This may or may not be used by an event implementation.
	}
}
