package tk.valoeghese.shuttle.impl.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.PersistentState;

public final class DummyPersistentState extends PersistentState {
	public DummyPersistentState(String key) {
		super(key);
	}

	private CompoundTag tag;

	@Override
	public void fromTag(CompoundTag tag) {
		this.tag = tag;
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		return this.tag;
	}
}
