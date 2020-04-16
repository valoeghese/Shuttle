package tk.valoeghese.shuttle.api.util;

/**
 * Represents a block position in the world.
 */
public class BlockPos {
	public BlockPos(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final int x;
	public final int y;
	public final int z;

	public BlockPos up() {
		return new BlockPos(this.x, this.y + 1, this.z);
	}

	public BlockPos down() {
		return new BlockPos(this.x, this.y - 1, this.z);
	}

	public boolean isValid() {
		return this.y >= 0 && this.y < 256;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	@Override
	public int hashCode() {
		// hashcode implementation taken from minecraft's BlockPos class
		return (this.y + this.z * 31) * 31 + this.x;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BlockPos) {
			BlockPos other = (BlockPos) obj;
			return other.x == this.x && other.y == this.y && other.z == this.z;
		} else {
			return false;
		}
	}

	public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);
}
