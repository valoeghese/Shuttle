package tk.valoeghese.shuttle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTickContext;
import tk.valoeghese.shuttle.impl.ShuttleEvents;

public class Shuttle implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Shuttle is setting up!");
		ServerTickCallback.EVENT.register(server -> {
			ShuttleTickContext context = new ShuttleTickContext(server);
			ShuttleEvents.TICK.postEvent(context);
			ShuttleEvents.TIMER.postEvent(context);
		});
	}
}
