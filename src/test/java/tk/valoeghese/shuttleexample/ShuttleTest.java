package tk.valoeghese.shuttleexample;

import tk.valoeghese.shuttle.api.ShuttleEventSubscriber;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTickContext;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTimerEvent;

public class ShuttleTest extends ShuttleEventSubscriber implements ShuttleTimerEvent {
	@Override
	public void onTimerCountdown(ShuttleTickContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTimerTicks() {
		return 20 * 4; // 4 seconds
	}
}
