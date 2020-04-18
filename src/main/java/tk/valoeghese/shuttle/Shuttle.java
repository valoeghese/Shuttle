package tk.valoeghese.shuttle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import tk.valoeghese.shuttle.api.event.ShuttleEvents;
import tk.valoeghese.shuttle.api.server.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.server.TickEvents.TickContext;
import tk.valoeghese.shuttle.impl.world.BiomeSetupContextImpl;
import tk.valoeghese.shuttle.impl.world.DimensionUtils;

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

		Registry.DIMENSION_TYPE.forEach(dimension -> {
			if (dimension != DimensionType.OVERWORLD && dimension != DimensionType.THE_NETHER && dimension != DimensionType.THE_END) {
				DimensionUtils.addModdedDimension(
						Registry.DIMENSION_TYPE.getId(dimension).toString(),
						dimension);
			}
		});
		
		RegistryEntryAddedCallback.event(Registry.DIMENSION_TYPE).register((id, registryName, dimension) -> {
			if (dimension != DimensionType.OVERWORLD && dimension != DimensionType.THE_NETHER && dimension != DimensionType.THE_END) {
				DimensionUtils.addModdedDimension(
						registryName,
						dimension);
			}
		});
	}

	public static void setupPlugins() {
		LOGGER.info("Shuttle is setting up plugins!");

		CommandSetupContext context = new CommandSetupContext();
		ShuttleEvents.SETUP_COMMAND.postEvent(context);
	}
}
