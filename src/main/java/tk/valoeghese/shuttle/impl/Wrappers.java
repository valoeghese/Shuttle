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
		return new BlockStateImpl(state);
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

	private static final Function<Block, Int2ObjectMap<BlockState>> I2OF = b -> new Int2ObjectArrayMap<>();
	private static final Map<Block, Int2ObjectMap<BlockState>> BLOCK_STATE_CACHE = new HashMap<>();
}
