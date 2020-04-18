package tk.valoeghese.shuttle.impl.world;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import tk.valoeghese.shuttle.api.world.biome.BiomeType;
import tk.valoeghese.shuttle.impl.Wrappers;

public class BiomeImpl implements tk.valoeghese.shuttle.api.world.biome.Biome {
	private BiomeImpl(Biome parent, String registryName) {
		this.parent = parent;
		this.registryName = registryName;
		this.type = Wrappers.wrap(parent.getCategory());
	}

	private final Biome parent;
	private final String registryName;
	private final BiomeType type;

	@Override
	public Biome getRawBiome() {
		return this.parent;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}

	@Override
	public BiomeType getType() {
		return this.type;
	}

	public static BiomeImpl of(Biome biome) {
		return BIOMES.computeIfAbsent(biome, b -> {
			String registryName = Registry.BIOME.getId(b).toString();
			BiomeImpl result = new BiomeImpl(b, registryName);
			REGISTRY.put(registryName, result);
			return result;
		});
	}

	public static BiomeImpl of(String registryName) {
		return REGISTRY.computeIfAbsent(registryName, n -> {
			Biome biome = Registry.BIOME.get(new Identifier(n));
			BiomeImpl result = new BiomeImpl(biome, n);
			BIOMES.put(biome, result);
			return result;
		});
	}

	public static final Map<Biome, BiomeImpl> BIOMES = new HashMap<>();
	public static final Map<String, BiomeImpl> REGISTRY = new HashMap<>();
}
