package tk.valoeghese.shuttle.api.chat;

public class ChatMessageBuilder {
	public ChatMessageBuilder() {
	}

	private final StringBuilder content = new StringBuilder();
	private ChatColour currentColour = ChatColour.WHITE;
	private ChatFormat currentFormat = ChatFormat.RESET;

	public ChatMessageBuilder append(String string) {
		this.setColour(ChatColour.WHITE, true); // colours reset the format anyway
		this.content.append(string);
		return this;
	}

	public ChatMessageBuilder append(String string, ChatColour colour) {
		this.setColour(colour, true);
		this.content.append(string);
		return this;
	}

	public ChatMessageBuilder append(String string, ChatFormat format) {
		this.setFormat(format, true);
		this.content.append(string);
		return this;
	}

	public ChatMessageBuilder append(String string, ChatColour colour, ChatFormat format) {
		this.setColour(colour, true); // apply colour before format!
		this.setFormat(format, true);
		this.content.append(string);
		return this;
	}

	private void setColour(ChatColour colour, boolean append) {
		if (this.currentColour != colour) {
			this.currentColour = colour;
			this.currentFormat = ChatFormat.RESET;

			if (append) {
				this.content.append(colour);
			}
		}
	}

	private void setFormat(ChatFormat format, boolean append) {
		if (this.currentFormat != format) {
			this.currentFormat = format;

			if (append) {
				this.content.append(format);
			}

			if (format == ChatFormat.RESET) {
				this.currentColour = ChatColour.WHITE;
			}
		}
	}

	@Override
	public String toString() {
		return this.content.toString();
	}
}
