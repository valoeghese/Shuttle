package tk.valoeghese.shuttleexample;

import tk.valoeghese.shuttle.api.ShuttleEventSubscriber;
import tk.valoeghese.shuttle.api.event.TickEvents.TickContext;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTimerEvent;
import tk.valoeghese.shuttle.api.player.Player;

public class ShuttleTest extends ShuttleEventSubscriber implements ShuttleTimerEvent {
	@Override
	public void onTimerCountdown(TickContext context) {
		for (Player player : context.getPlayers()) {
			player.sendMessage("Hello from Shuttle!");
		}
	}

	@Override
	public int getTimerTicks() {
		return ticks(0, 4);
	}
}
