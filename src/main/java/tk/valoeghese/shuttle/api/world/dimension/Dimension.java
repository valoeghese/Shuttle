package tk.valoeghese.shuttle.api.world.dimension;

/**
 * Represents a dimension in the game.
 */
public interface Dimension {
	/**
	 * @return the raw int id of this dimension. It is recommended to use the registry name over the id when possible, as some mods (such as fabric-registry-sync) can remap the integer ids.
	 */
	int getId();
	/**
	 * @return the registry name string of this dimension. For example, the overworld is minecraft:overworld.
	 */
	String getRegistryName();
}
