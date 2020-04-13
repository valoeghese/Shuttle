package tk.valoeghese.shuttle.api.player;

import java.util.UUID;

public interface Player {
	String getUsername();
	UUID getUUID();
	void sendMessage(String message);
	void sendMessage(ChatMessage message);
}
