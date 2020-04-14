package tk.valoeghese.shuttleexample;

import tk.valoeghese.shuttle.api.ShuttleEventSubscriber;
import tk.valoeghese.shuttle.api.chat.ChatColour;
import tk.valoeghese.shuttle.api.chat.ChatMessageBuilder;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.command.CommandRuntimeInfo;
import tk.valoeghese.shuttle.api.command.arg.CommandArgType;
import tk.valoeghese.shuttle.api.command.arg.CommandArguments;
import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataLoadContext;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataSaveContext;
import tk.valoeghese.shuttle.api.data.WorldTrackedData;
import tk.valoeghese.shuttle.api.event.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.event.SetupEvents.ShuttleCommandSetup;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTimerEvent;
import tk.valoeghese.shuttle.api.event.TickEvents.TickContext;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.api.util.Vec2i;

public class ShuttleTest extends ShuttleEventSubscriber implements ShuttleTimerEvent, ShuttleCommandSetup, ShuttleWorldDataEvent {
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

	private WorldTrackedData trackedData;

	@Override
	public void onWorldDataLoad(WorldDataLoadContext context) {
		this.trackedData = context.loadData("data");
	}

	@Override
	public void onWorldDataSave(WorldDataSaveContext context) {
		context.saveData(this.trackedData);
	}
}
