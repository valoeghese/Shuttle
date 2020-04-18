package tk.valoeghese.shuttle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.util.registry.Registry;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.server.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.server.TickEvents.TickContext;
import tk.valoeghese.shuttle.impl.world.BiomeSetupContextImpl;

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

		Registry.BIOME.forEach(biome -> {
			BiomeSetupContextImpl context = new BiomeSetupContextImpl(biome);
			ShuttleEvents.PER_BIOME_SETUP.postEvent(context);
		});

		RegistryEntryAddedCallback.event(Registry.BIOME).register((id, registryName, biome) -> {
			BiomeSetupContextImpl context = new BiomeSetupContextImpl(biome);
			ShuttleEvents.PER_BIOME_SETUP.postEvent(context);
		});
	}

	public static void setupPlugins() {
		LOGGER.info("Shuttle is setting up plugins!");

		CommandSetupContext context = new CommandSetupContext();
		ShuttleEvents.SETUP_COMMAND.postEvent(context);
	}
}
