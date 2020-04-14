package tk.valoeghese.shuttle.api.util;

/**
 * Represents a pair of two integers as a vector.
 */
public class Vec2i {
	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public final int x;
	public final int y;

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	@Override
	public int hashCode() {
		// hashcode implementation taken from minecraft's ChunkPos class
		int i = 1664525 * this.x + 1013904223;
		int j = 1664525 * (this.y ^ -559038737) + 1013904223;
		return i ^ j;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec2i) {
			Vec2i other = (Vec2i) obj;
			return other.x == this.x && other.y == this.y;
		} else {
			return false;
		}
	}

	public static final Vec2i ORIGIN = new Vec2i(0, 0);
}
