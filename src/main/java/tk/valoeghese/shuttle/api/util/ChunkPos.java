package tk.valoeghese.shuttle.api.util;

/**
 * Represents a position of a chunk in the world chunk grid.
 */
public class ChunkPos {
	public ChunkPos(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public final int x;
	public final int z;

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.z + ")";
	}

	@Override
	public int hashCode() {
		// hashcode implementation taken from minecraft's ChunkPos class
		int i = 1664525 * this.x + 1013904223;
		int j = 1664525 * (this.z ^ -559038737) + 1013904223;
		return i ^ j;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChunkPos) {
			ChunkPos other = (ChunkPos) obj;
			return other.x == this.x && other.z == this.z;
		} else {
			return false;
		}
	}

	public static final ChunkPos ORIGIN = new ChunkPos(0, 0);
}
