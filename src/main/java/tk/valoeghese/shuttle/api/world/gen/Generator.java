package tk.valoeghese.shuttle.api.world.gen;

import tk.valoeghese.shuttle.api.world.World;

/**
 * Interface representing a generator which populates things such as trees and mushrooms in the world.
 */
public interface Generator {
	void generate(World world, int startX, int startZ);
}
