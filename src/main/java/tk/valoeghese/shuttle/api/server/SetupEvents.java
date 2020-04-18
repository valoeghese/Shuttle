package tk.valoeghese.shuttle.api.server;

import java.util.function.Consumer;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.ServerCommandSource;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.event.Context;
import tk.valoeghese.shuttle.api.event.ShuttleEventListener;
import tk.valoeghese.shuttle.api.world.biome.Biome;
import tk.valoeghese.shuttle.api.world.gen.Generator;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;
import tk.valoeghese.shuttle.impl.command.BrigadierCommandBuilder;

/**
 * Collection of event interfaces which pertain to different phases of mod setup.
 */
public final class SetupEvents {
	private SetupEvents() {
		// NO-OP
	}

	/**
	 * Event Listener for setting up and registering commands.
	 */
	public static interface ShuttleCommandSetup extends ShuttleEventListener {
		/**
		 * Called at command setup, on the launch of the game.
		 */
		void setupCommands(CommandSetupContext context);
	}

	/**
	 * Event Listener for setting up stuff per biome, such as adding {@link Generator generators}.
	 */
	public static interface ShuttlePerBiomeSetupEvent extends ShuttleEventListener {
		/**
		 * Called during biome initialisation.
		 */
		void setupBiome(BiomeSetupContext context);
	}

	/**
	 * Context providing an interface to register commands.
	 */
	public static class CommandSetupContext implements Context<ShuttleCommandSetup> {
		/**
		 * Registers a command built with shuttle's command api.
		 * 
		 * @param command the shuttle command instance
		 */
		public void registerCommand(Command command) {
			if (command.isSubCommand()) {
				throw new RuntimeException("Cannot directly register a sub command! Sub commands are registered when the main command is registered.");
			}

			BrigadierCommandBuilder.buildAndRegister(command);
		}

		/**
		 * Registers a command built directly with the brigadier library to the game.
		 * 
		 * @param onlyOnDedicated if true, the command is only registered on a dedicated (i.e. not singleplayer integrated) server.
		 * @param commandSetup consumer which sets up the command from the supplied command dispatcher.
		 */
		public void registerBrigadierCommand(boolean onlyOnDedicated, Consumer<CommandDispatcher<ServerCommandSource>> commandSetup) {
			CommandRegistry.INSTANCE.register(false, commandSetup);
		}
	}

	/**
	 * Context for biome setup. Contains useful methods for adding {@link Generator generators} to the biome.
	 */
	public abstract static class BiomeSetupContext implements Context<ShuttlePerBiomeSetupEvent> {
		public BiomeSetupContext(Biome biome) {
			this.biome = biome;
		}

		private final Biome biome;

		public Biome getBiome() {
			return this.biome;
		}

		/**
		 * @param generator the generator to generate at the stage minecraft adds surface structures.
		 */
		public abstract void addSurfaceStructure(Generator generator, GeneratorPlacement placement);
		/**
		 * @param generator the generator to generate at the stage minecraft adds underground structures.
		 */
		public abstract void addUndergroundStructure(Generator generator, GeneratorPlacement placemen);
		/**
		 * @param generator the generator to generate at the stage minecraft adds vegetal decoration such as grass and trees.
		 */
		public abstract void addVegetalDecoration(Generator generator, GeneratorPlacement placemen);
	}
}
