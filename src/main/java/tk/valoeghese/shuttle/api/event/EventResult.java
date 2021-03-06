package tk.valoeghese.shuttle.api.event;

/**
 * Enum representing the result of an event which specifies a result. Details on the meaning of each is described in the particular implementing event.
 */
public enum EventResult {
	FAIL,
	PASS,
	SUCCESS;

	/**
	 * @return whether the event should cancel further processing of an event, under normal event behaviour.
	 */
	public boolean isCancellable() {
		return this != PASS;
	}
}
