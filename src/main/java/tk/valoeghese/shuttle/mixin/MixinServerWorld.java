package tk.valoeghese.shuttle.mixin;

import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;
import tk.valoeghese.shuttle.impl.data.DummyPersistentState;
import tk.valoeghese.shuttle.impl.data.WorldDataContext;
import tk.valoeghese.shuttle.impl.event.ShuttleInternalEvents;

@Mixin(ServerWorld.class)
public class MixinServerWorld {
	@Inject(at = @At("RETURN"), method = "<init>")
	private void init(MinecraftServer server, Executor workerExecutor, WorldSaveHandler worldSaveHandler, LevelProperties properties, DimensionType dimensionType, Profiler profiler, WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info) {
		// get state manager
		PersistentStateManager stateManager = ((ServerWorld) (Object) this).getPersistentStateManager();
		// post event
		ShuttleInternalEvents.WORLD_DATA.postEvent(
				new WorldDataContext(true)
				.loadFunction(
						key -> stateManager.get(() -> new DummyPersistentState(key), key)
						)
				);
	}
}
