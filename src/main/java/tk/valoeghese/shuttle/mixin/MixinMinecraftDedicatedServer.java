package tk.valoeghese.shuttle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import tk.valoeghese.shuttle.Shuttle;

@Mixin(MinecraftDedicatedServer.class)
public class MixinMinecraftDedicatedServer {
	@Inject(at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;)V", ordinal = 0), method = "setupServer")
	private void setupPluginsServer() {
		Shuttle.setupPlugins();
	}
}
