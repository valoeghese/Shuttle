package tk.valoeghese.shuttle.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.Vec3d;
import tk.valoeghese.shuttle.api.world.biome.BiomeType;
import tk.valoeghese.shuttle.api.world.block.Block;
import tk.valoeghese.shuttle.api.world.block.BlockProperty;
import tk.valoeghese.shuttle.impl.world.block.BlockStateImpl;

public final class Wrappers {
	public static BlockPos wrap(net.minecraft.util.math.BlockPos pos) {
		return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
	}

	public static Vec3d wrap(net.minecraft.util.math.Vec3d vector) {
		return new Vec3d(vector.getX(), vector.getY(), vector.getZ());
	}

	public static BlockState unwrap(Block block) {
		return BLOCK_STATE_CACHE.computeIfAbsent(block, I2OF)
				.computeIfAbsent(Arrays.hashCode(block.getStateModifiers()), i -> compute(block));
	}

	public static Block wrap(BlockState state) {
		return BlockStateImpl.blockOf(state);
	}

	private static BlockState compute(Block block) {
		BlockState result = block.getRawBlock().getDefaultState();
		StateManager<net.minecraft.block.Block, BlockState> manager = block.getRawBlock().getStateManager();

		for (BlockProperty blockProperty : block.getStateModifiers()) {
			//result = result.get
			Property<?> property = manager.getProperty(blockProperty.getName());

			if (property != null) {
				result = trySetValue(result, property, blockProperty.getValue());
			}
		}

		return result;
	}

	private static <T extends Comparable<T>> BlockState trySetValue(BlockState state, Property<T> property, String valueString) {
		Optional<T> value = property.parse(valueString);

		if (value.isPresent()) {
			return state.with(property, value.get());
		}

		return state;
	}

	public static BiomeType wrap(net.minecraft.world.biome.Biome.Category category) {
		switch (category) {
		case BEACH:
			return BiomeType.BEACH;
		case DESERT:
			return BiomeType.DESERT;
		case EXTREME_HILLS:
			return BiomeType.EXTREME_HILLS;
		case FOREST:
			return BiomeType.FOREST;
		case ICY:
			return BiomeType.ICY;
		case JUNGLE:
			return BiomeType.JUNGLE;
		case MESA:
			return BiomeType.MESA;
		case MUSHROOM:
			return BiomeType.MUSHROOM;
		case NETHER:
			return BiomeType.NETHER;
		case OCEAN:
			return BiomeType.OCEAN;
		case PLAINS:
			return BiomeType.PLAINS;
		case RIVER:
			return BiomeType.RIVER;
		case SAVANNA:
			return BiomeType.SAVANNA;
		case SWAMP:
			return BiomeType.SWAMP;
		case TAIGA:
			return BiomeType.TAIGA;
		case THEEND:
			return BiomeType.THEEND;
		case NONE:
		default:
			return BiomeType.NONE;
		}
	}

	public static net.minecraft.world.biome.Biome.Category unwrap(BiomeType type) {
		switch(type) {
		case BEACH:
			return net.minecraft.world.biome.Biome.Category.BEACH;
		case DESERT:
			return net.minecraft.world.biome.Biome.Category.DESERT;
		case EXTREME_HILLS:
			return net.minecraft.world.biome.Biome.Category.EXTREME_HILLS;
		case FOREST:
			return net.minecraft.world.biome.Biome.Category.FOREST;
		case ICY:
			return net.minecraft.world.biome.Biome.Category.ICY;
		case JUNGLE:
			return net.minecraft.world.biome.Biome.Category.JUNGLE;
		case MESA:
			return net.minecraft.world.biome.Biome.Category.MESA;
		case MUSHROOM:
			return net.minecraft.world.biome.Biome.Category.MUSHROOM;
		case NETHER:
			return net.minecraft.world.biome.Biome.Category.NETHER;
		case OCEAN:
			return net.minecraft.world.biome.Biome.Category.OCEAN;
		case PLAINS:
			return net.minecraft.world.biome.Biome.Category.PLAINS;
		case RIVER:
			return net.minecraft.world.biome.Biome.Category.RIVER;
		case SAVANNA:
			return net.minecraft.world.biome.Biome.Category.SAVANNA;
		case SWAMP:
			return net.minecraft.world.biome.Biome.Category.SWAMP;
		case TAIGA:
			return net.minecraft.world.biome.Biome.Category.TAIGA;
		case THEEND:
			return net.minecraft.world.biome.Biome.Category.THEEND;
		case NONE:
		default:
			return net.minecraft.world.biome.Biome.Category.NONE;
		}
	}

	public static net.minecraft.util.math.BlockPos unwrap(BlockPos pos) {
		return new net.minecraft.util.math.BlockPos(pos.x, pos.y, pos.z);
	}

	public static net.minecraft.util.math.Vec3d unwrap(Vec3d vector) {
		return new net.minecraft.util.math.Vec3d(vector.x, vector.y, vector.z);
	}

	private static final Function<Block, Int2ObjectMap<BlockState>> I2OF = b -> new Int2ObjectArrayMap<>();
	private static final Map<Block, Int2ObjectMap<BlockState>> BLOCK_STATE_CACHE = new HashMap<>();
}
