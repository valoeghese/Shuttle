package tk.valoeghese.shuttle.impl.world.decorator;

import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;

public class CountHeightmapPlacement implements GeneratorPlacement {
	public CountHeightmapPlacement(int count) {
		this.count = count;
	}

	private final int count;

	@Override
	public ConfiguredDecorator<?> createVanillaDecorator() {
		return Decorator.COUNT_HEIGHTMAP.configure(new CountDecoratorConfig(this.count));
	}
}
