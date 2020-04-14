package tk.valoeghese.shuttle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import tk.valoeghese.shuttle.api.event.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.event.TickEvents.TickContext;
import tk.valoeghese.shuttle.impl.event.ShuttleInternalEvents;

public class Shuttle implements ModInitializer {
	private static final Logger LOGGER = LogManager.getLogger("Shuttle");

	@Override
	public void onInitialize() {
		LOGGER.info("Shuttle is setting up!");

		ServerTickCallback.EVENT.register(server -> {
			TickContext context = new TickContext(server);
			ShuttleInternalEvents.TICK.postEvent(context);
			ShuttleInternalEvents.TIMER.postEvent(context);
		});
	}

	public static void setupPlugins() {
		LOGGER.info("Shuttle is setting up plugins!");

		CommandSetupContext context = new CommandSetupContext();
		ShuttleInternalEvents.SETUP_COMMAND.postEvent(context);
	}
}
