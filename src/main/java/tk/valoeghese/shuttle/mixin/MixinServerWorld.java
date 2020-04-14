package tk.valoeghese.shuttle.mixin;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;
import tk.valoeghese.shuttle.Shuttle;
import tk.valoeghese.shuttle.impl.data.DummyPersistentState;
import tk.valoeghese.shuttle.impl.data.WorldDataContext;
import tk.valoeghese.shuttle.impl.event.ShuttleInternalEvents;

@Mixin(ServerWorld.class)
public class MixinServerWorld {
	@Inject(at = @At("RETURN"), method = "<init>")
	@SuppressWarnings("resource")
	private void init(MinecraftServer server, Executor workerExecutor, WorldSaveHandler worldSaveHandler, LevelProperties properties, DimensionType dimensionType, Profiler profiler, WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info) {
		ServerWorld self = ((ServerWorld) (Object) this);

		if (self.getDimension().getType() == DimensionType.OVERWORLD) {
			Shuttle.LOGGER.info("Loading plugin persistent data");
			// get state manager
			PersistentStateManager stateManager = self.getPersistentStateManager();
			// post event
			ShuttleInternalEvents.WORLD_DATA.postEvent(
					new WorldDataContext(true)
					.loadFunction(
							key -> stateManager.get(() -> new DummyPersistentState(key), key)
							)
					);
		}
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;saveWorldData()V"), method = "saveLevel")
	@SuppressWarnings("resource")
	private void saveLevel(CallbackInfo info) {
		ServerWorld self = ((ServerWorld) (Object) this);

		if (self.getDimension().getType() == DimensionType.OVERWORLD) {
			Shuttle.LOGGER.info("Saving plugin persistent data");
			Consumer<PersistentState> stateSetter = self.getPersistentStateManager()::set;
			ShuttleInternalEvents.WORLD_DATA.postEvent(new WorldDataContext(false).storeFunction(stateSetter));
		}
	}
}
