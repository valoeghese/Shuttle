package tk.valoeghese.shuttle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.server.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.server.TickEvents.TickContext;

public class Shuttle implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Shuttle");

	@Override
	public void onInitialize() {
		LOGGER.info("Shuttle is initialising!");

		ServerTickCallback.EVENT.register(server -> {
			TickContext context = new TickContext(server);
			ShuttleEvents.TICK.postEvent(context);
			ShuttleEvents.TIMER.postEvent(context);
		});
	}

	public static void setupPlugins() {
		LOGGER.info("Shuttle is setting up plugins!");

		CommandSetupContext context = new CommandSetupContext();
		ShuttleEvents.SETUP_COMMAND.postEvent(context);
	}
}
