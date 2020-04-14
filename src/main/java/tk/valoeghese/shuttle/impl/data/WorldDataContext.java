package tk.valoeghese.shuttle.impl.data;

import java.util.function.Consumer;

import net.minecraft.world.PersistentState;
import tk.valoeghese.shuttle.api.data.DataEvents.ShuttleWorldDataEvent;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataLoadContext;
import tk.valoeghese.shuttle.api.data.DataEvents.WorldDataSaveContext;
import tk.valoeghese.shuttle.api.data.WorldTrackedData;
import tk.valoeghese.shuttle.api.event.Context;

public class WorldDataContext extends WorldDataSaveContext implements WorldDataLoadContext, Context<ShuttleWorldDataEvent> {
	public WorldDataContext(Consumer<PersistentState> storeFunction, boolean loadingContext) {
		super(storeFunction);

		this.loadingContext = loadingContext;
	}

	public final boolean loadingContext;

	@Override
	public void loadData(WorldTrackedData data) {
		// TODO Auto-generated method stub

	}
}
