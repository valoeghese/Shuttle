package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(World.class)
public class MixinWorld {
	@Inject(at = @At("HEAD"), method = "breakBlock")
	private void breakBlock(BlockPos pos, boolean drop, Entity breakingEntity, CallbackInfoReturnable<Boolean> info) {
		
	}
}
