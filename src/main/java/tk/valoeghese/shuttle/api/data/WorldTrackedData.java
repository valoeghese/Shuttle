package tk.valoeghese.shuttle.api.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.PersistentState;
import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.util.Vec2i;
import tk.valoeghese.shuttle.impl.TagDataTypes;

/**
 * Abstraction of a persistent state compound tag, common across each server instance.
 */
public class WorldTrackedData {
	/**
	 * Creates a WorldTrackedTata from the Persistent State. Use {@link ShuttleWorldDataEvent#onWorldDataLoad} over directly calling this constructor.
	 */
	public WorldTrackedData(String saveName, PersistentState state) {
		this.tag = state == null ? new CompoundTag() : state.toTag(new CompoundTag());
		this.saveName = saveName;
	}

	private final CompoundTag tag;
	private final String saveName;

	// value setters

	public int putInt(String name, int value) {
		this.tag.putInt(name, value);
		return value;
	}

	public Vec2i putVec2i(String name, Vec2i vector) {
		this.tag.putLong(name, toLong(vector.x, vector.y));
		return vector;
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

	public Vec2i getVec2i(String name, Vec2i defaultValue) {
		return this.tag.contains(name, TagDataTypes.LONG) ? toVec2i(this.tag.getLong(name)) : this.putVec2i(name, defaultValue);
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
		return this.saveName;
	}

	private static long toLong(int x, int y) {
		return ((long) x & 0xFFFFFFFFL) | (((long) y & 0xFFFFFFFFL) << 32);
	}

	private static Vec2i toVec2i(long hash) {
		int x = (int) (hash & 0xFFFFFFFFL);
		hash >>= 32;
		int y = (int) (hash & 0xFFFFFFFFL);
		return new Vec2i(x, y);
	}
}
