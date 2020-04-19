package tk.valoeghese.shuttleexample;

import java.util.Random;

import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.world.World;
import tk.valoeghese.shuttle.api.world.gen.Generator;

public final class EndCityChestGenerator implements Generator {
	@Override
	public boolean generate(World world, Random random, int chunkStartX, int chunkStartZ, BlockPos placement) {
		return world.setLootChest(placement, "minecraft:chests/end_city_treasure");
	}
}
