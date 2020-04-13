package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import tk.valoeghese.shuttle.Shuttle;

@Mixin(remap = false, value = RenderSystem.class)
/**
 * @reason the client has an integrated server.
 */
public class MixinRenderSystem {
	@Inject(at = @At("HEAD"), method = "setupDefaultState", cancellable = true)
	private static void setupPluginsClient(int x, int y, int width, int height, CallbackInfo info) {
		Shuttle.setupPlugins();
	}
}
