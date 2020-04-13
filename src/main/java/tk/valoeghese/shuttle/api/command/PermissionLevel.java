package tk.valoeghese.shuttle.api.command;

public enum PermissionLevel {
	NORMAL(0),
	OP(2);

	private PermissionLevel(int value) {
		this.value = value;
	}

	private final int value;

	public int intValue() {
		return this.value;
	}
}
