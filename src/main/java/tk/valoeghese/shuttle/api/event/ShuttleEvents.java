package tk.valoeghese.shuttle.api.event;

import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockInteractionContext;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockPlacementContext;
import tk.valoeghese.shuttle.api.player.PlayerEvents.ShuttlePlayerBlockBreakEvent;
import tk.valoeghese.shuttle.api.player.PlayerEvents.ShuttlePlayerBlockPlaceEvent;
import tk.valoeghese.shuttle.api.server.SetupEvents.BiomeSetupContext;
import tk.valoeghese.shuttle.api.server.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.server.SetupEvents.ShuttleCommandSetup;
import tk.valoeghese.shuttle.api.server.SetupEvents.ShuttlePerBiomeSetupEvent;
import tk.valoeghese.shuttle.api.server.TickEvents.ShuttleTickEvent;
import tk.valoeghese.shuttle.api.server.TickEvents.ShuttleTimerEvent;
import tk.valoeghese.shuttle.api.server.TickEvents.TickContext;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ChunkShapeContext;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ReplaceBlocksContext;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ShuttleChunkShapeEvent;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ShuttleReplaceBlocksEvent;
import tk.valoeghese.shuttle.impl.data.WorldDataContext;

/**
 * Class containing all the default shuttle events. Does not contain any plugin-added events or 
 */
public final class ShuttleEvents {
	public static final ShuttleEventTracker<ShuttleChunkShapeEvent, ChunkShapeContext> CHUNK_SHAPE = ShuttleEventTracker.of(
			ShuttleChunkShapeEvent.class,
			ChunkShapeContext.class,
			(context, events) -> events.forEach(event -> event.onChunkShape(context)));

	public static final ShuttleEventTracker<ShuttlePlayerBlockBreakEvent, PlayerBlockInteractionContext> PLAYER_BLOCK_BREAK = ShuttleEventTracker.of(
			ShuttlePlayerBlockBreakEvent.class,
			PlayerBlockInteractionContext.class,
			(context, events) -> {
				for (ShuttlePlayerBlockBreakEvent event : events) {
					EventResult result = event.onPlayerBlockBreak(context);

					if (result == EventResult.FAIL) {
						context.notifyEvent(0);
						break;
					} else if (result == EventResult.SUCCESS) {
						context.notifyEvent(1);
						break;
					}
				}
			});

	public static final ShuttleEventTracker<ShuttlePlayerBlockPlaceEvent, PlayerBlockPlacementContext> PLAYER_BLOCK_PLACE = ShuttleEventTracker.of(
			ShuttlePlayerBlockPlaceEvent.class,
			PlayerBlockPlacementContext.class,
			(context, events) -> {
				for (ShuttlePlayerBlockPlaceEvent event : events) {
					EventResult result = event.onPlayerBlockPlace(context);

					if (result == EventResult.FAIL) {
						context.notifyEvent(0);
						break;
					} else if (result == EventResult.SUCCESS) {
						context.notifyEvent(1);
						break;
					}
				}
			});

	public static final ShuttleEventTracker<ShuttleReplaceBlocksEvent, ReplaceBlocksContext> REPLACE_BLOCKS = ShuttleEventTracker.of(
			ShuttleReplaceBlocksEvent.class,
			ReplaceBlocksContext.class,
			(context, events) -> {
				for (ShuttleReplaceBlocksEvent event : events) {
					EventResult result = event.onReplaceBlocks(context);

					if (result == EventResult.FAIL) {
						context.notifyEvent(0);
						break;
					} else if (result == EventResult.SUCCESS) {
						context.notifyEvent(1);
						break;
					}
				}
			});

	public static final ShuttleEventTracker<ShuttleCommandSetup, CommandSetupContext> SETUP_COMMAND = ShuttleEventTracker.of(
			ShuttleCommandSetup.class,
			CommandSetupContext.class,
			(context, events) -> events.forEach(event -> event.setupCommands(context)));

	public static final ShuttleEventTracker<ShuttleTickEvent, TickContext> TICK = ShuttleEventTracker.of(
			ShuttleTickEvent.class,
			TickContext.class,
			(context, events) -> events.forEach(event -> event.onTick(context)));

	public static final ShuttleEventTracker<ShuttleTimerEvent, TickContext> TIMER = ShuttleEventTracker.of(
			ShuttleTimerEvent.class,
			TickContext.class,
			(context, events) -> events.forEach(event -> {
				if (context.getElapsedTicks() % event.getTimerTicks() == 0) {
					event.onTimerCountdown(context);
				}
			}));

	public static final ShuttleEventTracker<ShuttleWorldDataEvent, WorldDataContext> WORLD_DATA = ShuttleEventTracker.of(
			ShuttleWorldDataEvent.class,
			WorldDataContext.class,
			(context, events) -> {
				if (context.loadingContext) {
					events.forEach(event -> {
						context.setCurrentEventId(event.pluginId());
						event.onWorldDataLoad(context);
					});
				} else {
					events.forEach(event -> event.onWorldDataSave(context));
				}
			});

	public static final ShuttleEventTracker<ShuttlePerBiomeSetupEvent, BiomeSetupContext> PER_BIOME_SETUP = ShuttleEventTracker.of(
			ShuttlePerBiomeSetupEvent.class,
			BiomeSetupContext.class,
			(context, events) -> events.forEach(event -> event.setupBiome(context)));
}
