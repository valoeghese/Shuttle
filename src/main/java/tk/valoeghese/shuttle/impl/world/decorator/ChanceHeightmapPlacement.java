package tk.valoeghese.shuttle.impl.world.decorator;

import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;

public class ChanceHeightmapPlacement implements GeneratorPlacement {
	public ChanceHeightmapPlacement(int chance) {
		this.chance = chance;
	}

	private final int chance;

	@Override
	public ConfiguredDecorator<?> createVanillaDecorator() {
		return Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(this.chance));
	}
}
