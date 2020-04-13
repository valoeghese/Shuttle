package tk.valoeghese.shuttle.impl;

import java.util.UUID;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import tk.valoeghese.shuttle.api.chat.ChatMessage;
import tk.valoeghese.shuttle.api.player.Player;

public class PlayerImpl implements Player {
	public PlayerImpl(ServerPlayerEntity parent) {
		this.parent = parent;
	}

	private final ServerPlayerEntity parent;

	@Override
	public String getUsername() {
		return this.parent.getEntityName();
	}

	@Override
	public UUID getUUID() {
		return this.parent.getUuid();
	}

	@Override
	public void sendMessage(String message) {
		this.parent.sendMessage(new LiteralText(message));
	}

	@Override
	public void sendMessage(ChatMessage message) {
		this.sendMessage(message.toString());
	}
}
