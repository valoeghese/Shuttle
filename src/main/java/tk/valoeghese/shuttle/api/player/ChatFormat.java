package tk.valoeghese.shuttle.api.player;

public enum ChatFormat {
	OBFUSCATED("§k"),
	BOLD("§l"),
	STRIKETHROUGH("§m"),
	UNDERLINE("§n"),
	ITALIC("§o"),
	RESET("§r");

	private ChatFormat(String repr) {
		this.repr = repr;
	}

	private final String repr;

	@Override
	public String toString() {
		return this.repr;
	}
}
