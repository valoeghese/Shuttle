package tk.valoeghese.shuttle.mixin;

import java.io.File;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import tk.valoeghese.shuttle.impl.data.WorldDataContext;
import tk.valoeghese.shuttle.impl.event.ShuttleInternalEvents;

@Mixin(PersistentStateManager.class)
public abstract class MixinPersistentStateManager {
	@Shadow
	protected abstract File getFile(String id);

	@Inject(at = @At("HEAD"), method = "save")
	private void onSave(CallbackInfo info) {
		Consumer<PersistentState> stateSetter = ((PersistentStateManager) (Object) this)::set;
		ShuttleInternalEvents.WORLD_DATA.postEvent(new WorldDataContext(false).storeFunction(stateSetter));
	}
}
