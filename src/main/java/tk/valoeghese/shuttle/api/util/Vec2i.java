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

	public static final Vec2i ORIGIN = new Vec2i(0, 0);
}
