package tk.valoeghese.shuttle.api.world.gen;

import java.util.Random;

import tk.valoeghese.shuttle.api.event.Context;
import tk.valoeghese.shuttle.api.event.ShuttleEventListener;
import tk.valoeghese.shuttle.api.world.block.Block;

/**
 * Events relating to world generation.
 */
public final class WorldGenEvents {
	private WorldGenEvents() {
		// NO-OP
	}

	/**
	 * Event subscriber which is called after the chunk generator shapes a chunk. Use this to alter the base shape of the chunk.
	 * The chunk usually consists of air, stone, and water at this stage of generation.
	 */
	public static interface ShuttleChunkShapeEvent extends ShuttleEventListener {
		/**
		 * Called when the chunk is being shaped.
		 */
		void onChunkShape(ChunkShapeContext context);
	}

	/**
	 * Context providing the chunk that is being shaped and other utilities.
	 */
	public static class ChunkShapeContext implements Context<ShuttleChunkShapeEvent> {
		public ChunkShapeContext(GeneratingChunk chunk, Block defaultBlock, Block defaultFluid, Random random) {
			this.chunk = chunk;
			this.defaultBlock = defaultBlock;
			this.defaultFluid = defaultFluid;
			this.random = random;
		}

		private final GeneratingChunk chunk;
		private final Block defaultBlock, defaultFluid;
		private final Random random;

		/**
		 * @return the default block of the chunk, used to represent where there will be a solid block. Usually stone.
		 */
		public Block getDefaultBlock() {
			return this.defaultBlock;
		}

		/**
		 * @return the default fluid of the chunk, used to represent where there will be a fluid due to being under sea level. Usually water.
		 */
		public Block getDefaultFluid() {
			return this.defaultFluid;
		}

		/**
		 * @return the chunk that is generating. Use this to modify the shape of the world, or get information about the world.
		 */
		public GeneratingChunk getChunk() {
			return this.chunk;
		}

		/**
		 * @return a random instance for generation
		 */
		public Random getRandom() {
			return this.random;
		}
	}
}
