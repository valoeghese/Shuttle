package tk.valoeghese.shuttle.api.data;

import net.minecraft.nbt.CompoundTag;
import tk.valoeghese.shuttle.impl.TagDataTypes;

/**
 * Abstraction of a persistent state compound tag.
 */
public class WorldTrackedData {
	public WorldTrackedData(String saveName) {
		this.tag = new CompoundTag();
		this.saveName = saveName;
	}

	private final CompoundTag tag;
	private final String saveName;

	// value setters

	public int putInt(String name, int value) {
		this.tag.putInt(name, value);
		return value;
	}

	public float putFloat(String name, float value) {
		this.tag.putFloat(name, value);
		return value;
	}

	public String putString(String name, String value) {
		this.tag.putString(name, value);
		return value;
	}

	public boolean putBoolean(String name, boolean value) {
		this.tag.putBoolean(name, value);
		return value;
	}

	// value getters

	public int getInt(String name, int defaultValue) {
		return this.tag.contains(name, TagDataTypes.INT) ? this.tag.getInt(name) : this.putInt(name, defaultValue);
	}

	public float getFloat(String name, float defaultValue) {
		return this.tag.contains(name, TagDataTypes.FLOAT) ? this.tag.getFloat(name) : this.putFloat(name, defaultValue);
	}

	public String getString(String name, String defaultValue) {
		return this.tag.contains(name, TagDataTypes.STRING) ? this.tag.getString(name) : this.putString(name, defaultValue);
	}

	public boolean getBoolean(String name, boolean defaultValue) {
		return this.tag.contains(name, TagDataTypes.BYTE) ? this.tag.getBoolean(name) : this.putBoolean(name, defaultValue);
	}

	/**
	 * Returns the underlying tag around which this is a wrapper.
	 */
	public CompoundTag getTag() {
		return this.tag;
	}

	/**
	 * @return the save name of this tracked data
	 */
	public String getSaveName() {
		return saveName;
	}
}
