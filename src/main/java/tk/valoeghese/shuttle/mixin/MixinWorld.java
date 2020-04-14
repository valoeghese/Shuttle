package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(World.class)
public class MixinWorld {
	@Inject(at = @At("HEAD"), method = )
	public boolean breakBlock(BlockPos pos, boolean drop, Entity breakingEntity) {
		return drop;
	}
}
