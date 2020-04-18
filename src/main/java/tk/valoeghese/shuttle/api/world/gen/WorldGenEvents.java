package tk.valoeghese.shuttle.api.world.gen;

import java.util.Random;

import tk.valoeghese.shuttle.Unstable;
import tk.valoeghese.shuttle.api.event.Context;
import tk.valoeghese.shuttle.api.event.EventResult;
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
	 * Event subscriber which is called before the chunk generator replaces the base blocks with biome specific blocks in a chunk. Use this to alter the blocks of a chunk.
	 * The chunk usually consists of air, stone, and water at this stage of generation.
	 */
	public static interface ShuttleReplaceBlocksEvent extends ShuttleEventListener {
		/**
		 * Called when the chunk is going through the stage of generation where default blocks are replaced with biome specific blocks.
		 * @return {@link EventResult#FAIL} to cancel all further event processing and run vanilla generation,<br/>
		 * {@link EventResult#PASS} to pass this event on to further processing, and<br/>
		 * {@link EventResult#SUCCESS} to cancel all further event processing and do not proceed with vanilla generation. Use this result to completely replace vanilla biome blocks.
		 */
		EventResult onReplaceBlocks(ReplaceBlocksContext context);
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

	/**
	 * Context providing information about the chunk that is about to go through the replace-blocks stage of generation.
	 */
	public static class ReplaceBlocksContext implements Context<ShuttleReplaceBlocksEvent> {
		public ReplaceBlocksContext(GeneratingChunk chunk, Block defaultBlock, Block defaultFluid, Random random) {
			this.chunk = chunk;
			this.defaultBlock = defaultBlock;
			this.defaultFluid = defaultFluid;
			this.random = random;
		}

		private final GeneratingChunk chunk;
		private final Block defaultBlock, defaultFluid;
		private final Random random;
		private boolean cancelVanilla = false;

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
		 * @return the chunk that is generating. Use this to replace the blocks in the world, or get information about the world.
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

		/**
		 * @return the boolean result of whether vanilla generation is cancelled. Used by the implementation.
		 */
		@Unstable
		public boolean getResult() {
			return this.cancelVanilla;
		}

		@Override
		public void notifyEvent(int item) {
			this.cancelVanilla = item == 1;
		}
	}
}
