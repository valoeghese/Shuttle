package tk.valoeghese.shuttle.impl.world.decorator;

import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;

public final class DefaultPlacement implements GeneratorPlacement {
	@Override
	public ConfiguredDecorator<?> createVanillaDecorator() {
		return Decorator.NOPE.configure(DecoratorConfig.DEFAULT);
	}
}
