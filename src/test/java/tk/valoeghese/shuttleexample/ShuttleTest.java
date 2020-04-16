package tk.valoeghese.shuttleexample;

import java.util.Random;

import tk.valoeghese.shuttle.api.ShuttlePlugin;
import tk.valoeghese.shuttle.api.chat.ChatColour;
import tk.valoeghese.shuttle.api.chat.ChatMessageBuilder;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.command.CommandRuntimeInfo;
import tk.valoeghese.shuttle.api.command.arg.CommandArgType;
import tk.valoeghese.shuttle.api.command.arg.CommandArguments;
import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataLoadContext;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataSaveContext;
import tk.valoeghese.shuttle.api.data.PersistentData;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockInteractionContext;
import tk.valoeghese.shuttle.api.player.PlayerEvents.PlayerBlockPlacementContext;
import tk.valoeghese.shuttle.api.player.PlayerEvents.ShuttlePlayerBlockBreakEvent;
import tk.valoeghese.shuttle.api.player.PlayerEvents.ShuttlePlayerBlockPlaceEvent;
import tk.valoeghese.shuttle.api.server.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.server.SetupEvents.ShuttleCommandSetup;
import tk.valoeghese.shuttle.api.server.TickEvents.ShuttleTimerEvent;
import tk.valoeghese.shuttle.api.server.TickEvents.TickContext;
import tk.valoeghese.shuttle.api.util.Vec2i;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.dimension.Dimensions;
import tk.valoeghese.shuttle.api.world.gen.GeneratingChunk;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ChunkShapeContext;
import tk.valoeghese.shuttle.api.world.gen.WorldGenEvents.ShuttleChunkShapeEvent;

public class ShuttleTest extends ShuttlePlugin
implements ShuttleTimerEvent, ShuttleCommandSetup, ShuttleWorldDataEvent, ShuttlePlayerBlockBreakEvent,
ShuttlePlayerBlockPlaceEvent, ShuttleChunkShapeEvent {
	@Override
	public void onTimerCountdown(TickContext context) {
		for (Player player : context.getPlayers()) {
			player.sendMessage(new ChatMessageBuilder().append("Hello from Shuttle!", ChatColour.AQUA));
		}
	}

	@Override
	public int getTimerTicks() {
		return ticks(0, 20);
	}

	@Override
	public void setupCommands(CommandSetupContext context) {
		// test command

		Command test = new Command("test");
		test.addCommandArg("thing", CommandArgType.BOOLEAN);
		test.setCallback(this::test);

		Command other = test.subCommand("other");
		other.setCallback(this::test2);

		context.registerCommand(test);

		// tracked data test command

		Command trackedDataTest = new Command("coord");

		Command log = trackedDataTest.subCommand("log");
		log.setCallback((args, cxt) -> {
			this.trackedData.putVec2i(cxt.getExecutor().getName(), cxt.getExecutor().getChunkCoordinates());
			return true;
		});

		Command get = trackedDataTest.subCommand("get");
		get.setCallback((args, cxt) -> {
			cxt.getExecutor().sendMessage(this.trackedData.getVec2i(cxt.getExecutor().getName(), Vec2i.ORIGIN).toString());
			return true;
		});

		context.registerCommand(trackedDataTest);
	}

	private boolean test(CommandArguments args, CommandRuntimeInfo context) {
		log(args.getBoolean("thing"));
		return true;
	}

	private boolean test2(CommandArguments args, CommandRuntimeInfo context) {
		warn("concern");
		return true;
	}

	private PersistentData trackedData;

	@Override
	public void onWorldDataLoad(WorldDataLoadContext context) {
		this.trackedData = context.loadData("data");
	}

	@Override
	public void onWorldDataSave(WorldDataSaveContext context) {
		context.saveData(this.trackedData);
	}

	@Override
	public EventResult onPlayerBlockBreak(PlayerBlockInteractionContext context) {
		if (context.getChunkPos().equals(Vec2i.ORIGIN)) {
			context.getWorld().setBlock(context.getBlockPos(), GOLD_BLOCK);
			return EventResult.FAIL;
		}

		return EventResult.PASS;
	}

	@Override
	public EventResult onPlayerBlockPlace(PlayerBlockPlacementContext context) {
		if (context.getChunkPos().equals(Vec2i.ORIGIN)) {
			return EventResult.FAIL;
		}

		context.setBlock(GOLD_BLOCK);
		return EventResult.PASS;
	}

	public static final Block GOLD_BLOCK = Block.get("minecraft:gold_block");

	@Override
	public void onChunkShape(ChunkShapeContext context) {
		GeneratingChunk chunk = context.getChunk();

		// If in the nether
		if (chunk.getDimension() == Dimensions.NETHER) {
			Random random = context.getRandom();

			// a random one-in-three chance per chunk
			if (random.nextInt(3) == 0) {
				// pick local chunk x and z at random
				int x = random.nextInt(16);
				int z = random.nextInt(16);

				// count down from y = 127 to y = 0
				for (int y = 127; y >= 0; --y) {
					// one in 5 chance to set gold block
					if (random.nextInt(5) == 0) {
						if (chunk.getBlock(x, y, z) == context.getDefaultBlock()) {
							chunk.setBlock(x, y, z, GOLD_BLOCK);
						}
					}
				}
			}
		}
	}
}
