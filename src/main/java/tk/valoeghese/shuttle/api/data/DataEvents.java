package tk.valoeghese.shuttle.api.data;

import java.util.function.Consumer;

import net.minecraft.world.PersistentState;
import tk.valoeghese.shuttle.api.event.ShuttleEvent;
import tk.valoeghese.shuttle.impl.data.DummyPersistentState;

/**
 * Collection of event interfaces which relate to the saving and loading of data.
 */
public final class DataEvents {
	private DataEvents() {
		// NO-OP
	}

	/**
	 * Event Subscriber which is called on world data load and save. Use this to load and save your {@link WorldTrackedData} instances.
	 */
	public static interface ShuttleWorldDataEvent extends ShuttleEvent {
		/**
		 * Run on world data load.
		 * @param context the context providing an interface for loading world data.
		 */
		void onWorldDataLoad(WorldDataLoadContext context);
		/**
		 * Run on world data save.
		 * @param context the context providing an interface for saving world data.
		 */
		void onWorldDataSave(WorldDataSaveContext context);
	}

	/**
	 * Context providing an interface to saving data.
	 */
	public abstract static class WorldDataSaveContext {
		public WorldDataSaveContext(Consumer<PersistentState> storeFunction) {
			this.storeFunction = storeFunction;
		}

		private final Consumer<PersistentState> storeFunction;

		/**
		 * Saves the specified {@link WorldTrackedData} to the world data.
		 */
		public void saveData(WorldTrackedData data) {
			PersistentState state = new DummyPersistentState(data.getSaveName());
			state.fromTag(data.getTag());
			this.storeFunction.accept(state);
		}
	}

	/**
	 * Context providing an interface to loading data.
	 */
	public static interface WorldDataLoadContext {
		/**
		 * Loads the specified {@link WorldTrackedData} to the world data. Returns a new world tracked data if it doesn't exist.
		 */
		void loadData(WorldTrackedData data);
	}
}
