package tk.valoeghese.shuttle.api.util;

/**
 * Represents a set of three doubles as a vector.
 */
public class Vec3d {
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final double x;
	public final double y;
	public final double z;

	/**
	 * @return the distance to the other {@link Vec3d}, squared
	 */
	public double squaredDistanceTo(Vec3d other) {
		double dx = Math.abs(this.x - other.x);
		double dy = Math.abs(this.y - other.y);
		double dz = Math.abs(this.z- other.z);

		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * @return the distance to the other {@link Vec3d}
	 */
	public double distanceTo(Vec3d other) {
		return Math.sqrt(this.squaredDistanceTo(other));
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	@Override
	public int hashCode() {
		// hashcode implementation taken from minecraft's Vec3d class
		long l = Double.doubleToLongBits(this.x);
		int i = (int)(l ^ l >>> 32);
		l = Double.doubleToLongBits(this.y);
		i = 31 * i + (int)(l ^ l >>> 32);
		l = Double.doubleToLongBits(this.z);
		i = 31 * i + (int)(l ^ l >>> 32);
		return i;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3d) {
			Vec3d other = (Vec3d) obj;
			return other.x == this.x && other.y == this.y && other.z == this.z;
		} else {
			return false;
		}
	}

	public static final Vec3d ORIGIN = new Vec3d(0, 0, 0);
}
