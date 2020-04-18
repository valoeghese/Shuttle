package tk.valoeghese.shuttle.api.world.gen;

import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import tk.valoeghese.shuttle.Unstable;
import tk.valoeghese.shuttle.impl.world.decorator.ChanceHeightmapPlacement;
import tk.valoeghese.shuttle.impl.world.decorator.CountHeightRangeSolidBottomPlacement;
import tk.valoeghese.shuttle.impl.world.decorator.CountHeightmapPlacement;
import tk.valoeghese.shuttle.impl.world.decorator.DefaultPlacement;

/**
 * Chooses the positions at which a {@link Generator} should generate. Created through the various factory methods provided in this class.
 */
public interface GeneratorPlacement {
	/**
	 * @return a vanilla configured decorator created from this placement. Used by the implementation.
	 */
	@Unstable
	ConfiguredDecorator<?> createVanillaDecorator();

	/**
	 * Collection of factory methods for creating {@link GeneratorPlacement GeneratorPlacements} with a specified count.
	 */
	static final class Count {
		private Count() {
			// NO-OP
		}

		/**
		 * Creates a generator placement that specifies blocks along the surface of the world, with the specified count of times to generate.
		 * @param count the number of times to generate the {@link Generator generator}.
		 */
		static GeneratorPlacement surfaceHeightmap(int count) {
			return new CountHeightmapPlacement(count);
		}

		/**
		 * Creates a generator placement that specified blocks along the floor (i.e. air blocks with a full, solid cube block below).
		 * @param count the number of times to generate the {@link Generator generator}.
		 * @param minY the minimum y at which to generate the generator.
		 * @param maxY the maximum y at which to generate the generator.
		 */
		static GeneratorPlacement onFloorWithinRange(int count, int minY, int maxY) {
			return new CountHeightRangeSolidBottomPlacement(count, minY, maxY);
		}
	}

	/**
	 * Collection of factory methods for creating {@link GeneratorPlacement GeneratorPlacements} with a specified chance per chunk.
	 */
	static final class Chance {
		/**
		 * Creates a generator placement that specifies blocks along the surface of the world, with the specified chance of generation per chunk.
		 * @param chance the generator has a 1/chance chance of generating per chunk
		 */
		static GeneratorPlacement surfaceHeightmap(int chance) {
			return new ChanceHeightmapPlacement(chance);
		}
	}

	/**
	 * @return a generator placement which gives the default block position in a chunk for generation, every chunk.
	 */
	static GeneratorPlacement noPlacement() {
		return new DefaultPlacement();
	}
}
