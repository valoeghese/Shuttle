package tk.valoeghese.shuttle.impl.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import tk.valoeghese.shuttle.api.server.SetupEvents.BiomeSetupContext;
import tk.valoeghese.shuttle.api.world.gen.Generator;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;
import tk.valoeghese.shuttle.impl.world.decorator.CountHeightRangeSolidBottomFeature;
import tk.valoeghese.shuttle.impl.world.decorator.CountHeightRangeSolidBottomPlacement;
import tk.valoeghese.shuttle.impl.world.decorator.GeneratorFeature;

/**
 * @apiNote
 * This really only exists to hide the feature adding code from users browsing the source.
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
		this.biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, create(generator, placement));
	}

	@Override
	public void addUndergroundStructure(Generator generator, GeneratorPlacement placement) {
		this.biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, create(generator, placement));
	}

	@Override
	public void addUndergroundDecoration(Generator generator, GeneratorPlacement placement) {
		this.biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, create(generator, placement));
	}

	@Override
	public void addVegetalDecoration(Generator generator, GeneratorPlacement placement) {
		this.biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, create(generator, placement));
	}

	private static ConfiguredFeature<?, ?> create(Generator generator, GeneratorPlacement placement) {
		if (placement instanceof CountHeightRangeSolidBottomPlacement) {
			return new CountHeightRangeSolidBottomFeature(((CountHeightRangeSolidBottomPlacement) placement).minY, generator)
					.configure(FeatureConfig.DEFAULT)
					.createDecoratedFeature(placement.createVanillaDecorator());
		} else {
			return new GeneratorFeature(generator)
					.configure(FeatureConfig.DEFAULT)
					.createDecoratedFeature(placement.createVanillaDecorator());
		}
	}
}
