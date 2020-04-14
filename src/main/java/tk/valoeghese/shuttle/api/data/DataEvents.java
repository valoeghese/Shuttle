package tk.valoeghese.shuttle.api.data;

import tk.valoeghese.shuttle.api.event.ShuttleEventListener;

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
	public static interface ShuttleWorldDataEvent extends ShuttleEventListener {
		/**
		 * Runs on world data load.
		 * @param context the context providing an interface for loading world data.
		 */
		void onWorldDataLoad(WorldDataLoadContext context);
		/**
		 * Runs on world data save.
		 * @param context the context providing an interface for saving world data.
		 */
		void onWorldDataSave(WorldDataSaveContext context);
	}

	/**
	 * Context providing an interface to saving data.
	 */
	public static interface WorldDataSaveContext {
		/**
		 * Saves the specified {@link WorldTrackedData} to the world data.
		 */
		void saveData(WorldTrackedData data);
	}

	/**
	 * Context providing an interface to loading data.
	 */
	public static interface WorldDataLoadContext {
		/**
		 * Loads the specified {@link WorldTrackedData} from the world data. Returns a new world tracked data if it doesn't exist.
		 */
		WorldTrackedData loadData(String name);
	}
}
