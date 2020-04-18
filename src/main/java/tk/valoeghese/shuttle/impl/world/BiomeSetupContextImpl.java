package tk.valoeghese.shuttle.impl.world;

import net.minecraft.world.biome.Biome;
import tk.valoeghese.shuttle.api.server.SetupEvents.BiomeSetupContext;
import tk.valoeghese.shuttle.api.world.gen.Generator;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;

/**
 * @apiNote
 * This really only exists to hide the wacky feature adding code from users browsing the source.
 * Might move this code to {@link BiomeSetupContext} and remove this impl class at some point.
 */
public class BiomeSetupContextImpl extends BiomeSetupContext {
	public BiomeSetupContextImpl(Biome biome) {
		super(BiomeImpl.of(biome));

		this.biome = biome;
	}

	private final Biome biome;

	@Override
	public void addSurfaceStructure(Generator generator, GeneratorPlacement placement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUndergroundStructure(Generator generator, GeneratorPlacement placemen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addVegetalDecoration(Generator generator, GeneratorPlacement placemen) {
		// TODO Auto-generated method stub

	}
}
