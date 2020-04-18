package tk.valoeghese.shuttle.api.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundTag;
import tk.valoeghese.shuttle.Unstable;
import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.util.ChunkPos;
import tk.valoeghese.shuttle.impl.TagDataTypes;

/**
 * Abstraction of a persistent state compound tag, common across all dimensions in the server world.
 */
public class PersistentData {
	/**
	 * Creates a WorldTrackedTata from the Persistent State. Use {@link ShuttleWorldDataEvent#onWorldDataLoad} over directly calling this constructor.
	 */
	@Unstable
	public PersistentData(String saveName, CompoundTag tag) {
		this.saveName = saveName;
		this.tag = tag;
	}

	private final CompoundTag tag;
	private final Map<String, PersistentData> subData = new HashMap<>();
	private final String saveName;

	// value setters

	public int putInt(String name, int value) {
		this.tag.putInt(name, value);
		return value;
	}

	public ChunkPos putVec2i(String name, ChunkPos vector) {
		this.tag.putLong(name, toLong(vector.x, vector.z));
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

	public ChunkPos getVec2i(String name, ChunkPos defaultValue) {
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
	 * @return the {@link PersistentData} sub data instance, if it exists. Otherwise return null.
	 */
	public PersistentData getSubDataOrNull(String name) {
		PersistentData data = this.subData.get(name);

		if (data != null) {
			return data;
		} else if (this.tag.contains(name, TagDataTypes.COMPOUND)) {
			String saveName = this.saveName + "$" + name;
			return this.subData.put(name, new PersistentData(saveName, this.tag.getCompound(name)));
		} else {
			return null;
		}
	}

	/**
	 * @return the {@link PersistentData} sub data instance, if it exists. Otherwise create and return a new {@link PersistentData} instance.
	 */
	public PersistentData getOrCreateSubData(String name) {
		return this.subData.computeIfAbsent(name, n -> {
			String saveName = this.saveName + "$" + name;

			if (this.tag.contains(n, TagDataTypes.COMPOUND)) {
				return this.subData.put(n, new PersistentData(saveName, this.tag.getCompound(n)));
			} else {
				CompoundTag tag = new CompoundTag();
				this.tag.put(n, tag);
				return this.subData.put(name, new PersistentData(saveName, tag));
			}
		});
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

	private static ChunkPos toVec2i(long hash) {
		int x = (int) (hash & 0xFFFFFFFFL);
		hash >>= 32;
		int y = (int) (hash & 0xFFFFFFFFL);
		return new ChunkPos(x, y);
	}
}
