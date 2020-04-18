package tk.valoeghese.shuttle.impl.world.decorator;

import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import tk.valoeghese.shuttle.api.world.gen.GeneratorPlacement;

public class CountHeightRangeSolidBottomPlacement implements GeneratorPlacement {
	public CountHeightRangeSolidBottomPlacement(int count, int minY, int maxY) {
		this.count = count;
		this.minY = minY;
		this.maxY = maxY;
	}

	private final int count;
	public final int minY;
	private final int maxY;

	/**
	 * @implNote Solid-Bottom part is done in the feature.
	 */
	@Override
	public ConfiguredDecorator<?> createVanillaDecorator() {
		// y = nextInt(max - topOffset) + bottomOffset
		return Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(this.count, this.minY, this.minY, this.maxY + 1));
	}
}
