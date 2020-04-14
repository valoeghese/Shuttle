package tk.valoeghese.shuttle.impl.data;

import java.util.function.Consumer;
import java.util.function.Function;

import net.minecraft.world.PersistentState;
import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataLoadContext;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataSaveContext;
import tk.valoeghese.shuttle.api.data.WorldTrackedData;
import tk.valoeghese.shuttle.api.event.Context;

public class WorldDataContext implements WorldDataSaveContext, WorldDataLoadContext, Context<ShuttleWorldDataEvent> {
	public WorldDataContext(boolean loadingContext) {
		this.loadingContext = loadingContext;
	}

	public final boolean loadingContext;
	private String currentEventId;
	private Consumer<PersistentState> setFunction;
	private Function<String, PersistentState> loadFunction;

	public WorldDataContext storeFunction(Consumer<PersistentState> storeFunction) {
		this.setFunction = storeFunction;
		return this;
	}

	public WorldDataContext loadFunction(Function<String, PersistentState> loadFunction) {
		this.loadFunction = loadFunction;
		return this;
	}

	@Override
	public WorldTrackedData loadData(String name) {
		String saveName = this.currentEventId + "." + name;
		PersistentState state = this.loadFunction.apply(saveName);
		return new WorldTrackedData(saveName, state);
	}

	@Override
	public void saveData(WorldTrackedData data) {
		PersistentState state = new DummyPersistentState(data.getSaveName());
		state.fromTag(data.getTag());
		this.setFunction.accept(state);
	}

	public void setCurrentEventId(String id) {
		this.currentEventId = id;
	}
}
