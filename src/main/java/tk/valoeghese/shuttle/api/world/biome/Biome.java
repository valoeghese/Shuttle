package tk.valoeghese.shuttle.api.world.biome;

import tk.valoeghese.shuttle.Unstable;

/**
 * Represents a minecraft biome.
 */
public interface Biome {
	/**
	 * @return the raw, vanilla biome.
	 */
	@Unstable
	net.minecraft.world.biome.Biome getRawBiome();
	/**
	 * @return the registry id of the biome. For example, plains is minecraft:plains.
	 */
	String getRegistryName();
	/**
	 * @return the type of the biome.
	 */
	BiomeType getType();
}
