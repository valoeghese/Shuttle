package tk.valoeghese.shuttle.api.world.gen;

import java.util.Random;

import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.world.World;

/**
 * Interface representing a generator which populates things such as trees and mushrooms in the world.
 */
public interface Generator {
	/**
	 * Called when the generator is to generate.
	 * @param world the world to generate in. Only consists of a specific region of chunks due to how minecraft's terrain generation works.
	 * @param random a {@link Random random number generator} instance for generation.
	 * @param chunkStartX the start block x of the chunk in which to generate. Generate from startX to startX + 15.
	 * @param chunkStartZ the start block z of the chunk in which to generate. Generate from startZ to startZ + 15.
	 * @param placement the position supplied by the {@link GeneratorPlacement}. Most of the time you should use this over chunkStartX and chunkStartZ.
	 * @return whether the generator successfully generated.
	 */
	boolean generate(World world, Random random, int chunkStartX, int chunkStartZ, BlockPos placement);
}
