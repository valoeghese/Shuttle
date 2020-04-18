package tk.valoeghese.shuttle.api.world.gen;

import tk.valoeghese.shuttle.api.world.World;

/**
 * Interface representing a generator which populates things such as trees and mushrooms in the world.
 */
public interface Generator {
	/**
	 * Called when the generator is to generate.
	 * @param world the world to generate in. Only consists of a specific region of chunks due to how minecraft's terrain generation works.
	 * @param startX the start x of the chunk in which to generate. Generate from startX to startX + 15.
	 * @param startZ the start z of the chunk in which to generate. Generate from startZ to startZ + 15.
	 */
	void generate(World world, int startX, int startZ);
}
