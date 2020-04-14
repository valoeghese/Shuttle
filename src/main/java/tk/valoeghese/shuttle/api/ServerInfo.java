package tk.valoeghese.shuttle.api;

import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.impl.player.PlayerImpl;

/**
 * Class providing information about the {@link MinecraftServer server}.
 */
public abstract class ServerInfo {
	public ServerInfo(MinecraftServer server) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		final int playerCount = players.size();
		this.players = new Player[playerCount];

		for (int i = 0; i < playerCount; ++i) {
			this.players[i] = new PlayerImpl(players.get(i));
		}

		this.elapsedTicks = server.getTicks();
	}

	private final Player[] players;
	private final int elapsedTicks;

	/**
	 * @return an array of players on the server.
	 */
	public Player[] getPlayers() {
		return this.players;
	}

	/**
	 * @return the number of elapsed ticks since server start.
	 */
	public int getElapsedTicks() {
		return this.elapsedTicks;
	}
}
