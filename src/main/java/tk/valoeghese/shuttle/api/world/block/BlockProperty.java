package tk.valoeghese.shuttle.api.world.block;

/**
 * Modifier of a block's state.
 */
public final class BlockProperty {
	public BlockProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}

	private final String name;
	private final String value;

	/**
	 * @return the name of the block property
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the value of the block property
	 */
	public String getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 29 * hash + this.value.hashCode();
		hash = 29 * hash + this.value.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof BlockProperty) {
			return this.name.equals(((BlockProperty) obj).name) && this.value.equals(((BlockProperty) obj).value);
		} else {
			return false;
		}
	}
}
