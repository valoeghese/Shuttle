package tk.valoeghese.shuttle.api.chat;

public enum ChatFormat {
	OBFUSCATED("\u00A7k"),
	BOLD("\u00A7l"),
	STRIKETHROUGH("\u00A7m"),
	UNDERLINE("\u00A7n"),
	ITALIC("\u00A7o"),
	RESET("\u00A7r");

	private ChatFormat(String repr) {
		this.repr = repr;
	}

	private final String repr;

	@Override
	public String toString() {
		return this.repr;
	}
}
