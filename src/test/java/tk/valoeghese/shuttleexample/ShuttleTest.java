package tk.valoeghese.shuttleexample;

import tk.valoeghese.shuttle.api.ShuttleEventSubscriber;
import tk.valoeghese.shuttle.api.command.Command;
import tk.valoeghese.shuttle.api.command.CommandArgType;
import tk.valoeghese.shuttle.api.command.CommandArguments;
import tk.valoeghese.shuttle.api.event.SetupEvents.CommandSetupContext;
import tk.valoeghese.shuttle.api.event.SetupEvents.ShuttleCommandSetup;
import tk.valoeghese.shuttle.api.event.TickEvents.ShuttleTimerEvent;
import tk.valoeghese.shuttle.api.event.TickEvents.TickContext;
import tk.valoeghese.shuttle.api.player.Player;

public class ShuttleTest extends ShuttleEventSubscriber implements ShuttleTimerEvent, ShuttleCommandSetup {
	@Override
	public void onTimerCountdown(TickContext context) {
		for (Player player : context.getPlayers()) {
			player.sendMessage("Hello from Shuttle!");
		}
	}

	@Override
	public int getTimerTicks() {
		return ticks(0, 4);
	}

	@Override
	public void setupCommands(CommandSetupContext context) {
		Command test = new Command("test");
		test.addCommandArg("thing", CommandArgType.BOOLEAN);
		test.setCallback(this::test);

		Command other = test.subCommand("other");
		other.setCallback(this::test2);

		context.registerCommand(test);
	}

	private boolean test(CommandArguments args) {
		log(args.getBoolean("thing"));
		return true;
	}

	private boolean test2(CommandArguments args) {
		warn("concern");
		return true;
	}
}
