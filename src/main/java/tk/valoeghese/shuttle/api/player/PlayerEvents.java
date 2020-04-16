package tk.valoeghese.shuttle.api.player;

import tk.valoeghese.shuttle.api.event.Context;
import tk.valoeghese.shuttle.api.event.EventResult;
import tk.valoeghese.shuttle.api.event.ShuttleEventListener;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.Vec2i;
import tk.valoeghese.shuttle.api.world.World;
import tk.valoeghese.shuttle.api.world.block.Block;

/**
 * Events to do with the player.
 */
public class PlayerEvents {
	private PlayerEvents() {
		// NO-OP
	}

	/**
	 * Event Listener which is called when a player attempts to break a block.
	 */
	public static interface ShuttlePlayerBlockBreakEvent extends ShuttleEventListener {
		/**
		 * Called when a player tries to break a block.
		 * @return {@link EventResult#FAIL} to cancel the block break,
		 * {@link EventResult#PASS} to pass the result on to further event processing,
		 * or {@link EventResult#SUCCESS} to cancel further processing and allow the block break.
		 * If all event listeners pass, normal block break behaviour happens.
		 */
		EventResult onPlayerBlockBreak(PlayerBlockInteractionContext context);
	}

	/**
	 * Event Listener which is called when a player attempts to place a block.
	 */
	public static interface ShuttlePlayerBlockPlaceEvent extends ShuttleEventListener {
		/**
		 * Called when a player tries to place a block.
		 * @return {@link EventResult#FAIL} to cancel the block place,
		 * {@link EventResult#PASS} to pass the result on to further event processing,
		 * or {@link EventResult#SUCCESS} to cancel further processing and allow the block place.
		 * If all event listeners pass, normal block place behaviour happens.
		 */
		EventResult onPlayerBlockPlace(PlayerBlockPlacementContext context);
	}

	/**
	 * Context providing information about player interaction with blocks in the world.
	 */
	public static class PlayerBlockInteractionContext implements Context<ShuttlePlayerBlockEvents> {
		public PlayerBlockInteractionContext(Player player, World world, Block block, BlockPos pos) {
			this.player = player;
			this.block = block;
			this.pos = pos;
			this.world = world;
			this.chunkPos = new Vec2i(pos.x >> 4, pos.z >> 4);
		}

		private final Player player;
		private final World world;
		protected Block block;
		private final BlockPos pos;
		private final Vec2i chunkPos;
		private boolean success = false;
		private boolean fail = false;

		/**
		 * @return the player interacting with the world.
		 */
		public Player getPlayer() {
			return this.player;
		}

		/**
		 * @return the world the player is interacting with.
		 */
		public World getWorld() {
			return this.world;
		}

		/**
		 * @return the block with which the player is attempting to interact.
		 */
		public Block getBlock() {
			return this.block;
		}

		/**
		 * @return the position where the block the player is trying to interact with is.
		 */
		public BlockPos getBlockPos() {
			return this.pos;
		}

		/**
		 * @return the chunk position where the block the player is trying to interact with is.
		 */
		public Vec2i getChunkPos() {
			return this.chunkPos;
		}

		@Override
		public void notifyEvent(int item) {
			if (item == 1) {
				this.success = true;
			} else if (item == 0) {
				this.fail = true;
			}
		}

		/**
		 * @return the event result. Used by the implementation.
		 */
		public EventResult getResult() {
			return this.success ? EventResult.SUCCESS : (this.fail ? EventResult.FAIL : EventResult.PASS);
		}
	}

	/**
	 * {@link PlayerBlockPlacementContext} providing more utilities specific to block placement.
	 */
	public static class PlayerBlockPlacementContext extends PlayerBlockInteractionContext {
		public PlayerBlockPlacementContext(Player player, World world, Block block, BlockPos pos) {
			super(player, world, block, pos);
		}

		private boolean modifiedBlock = false;

		/**
		 * Sets the block to be placed by the player.
		 * @param block the block to set to be placed.
		 */
		public void setBlock(Block block) {
			this.modifiedBlock = true;
			this.block = block;
		}

		/**
		 * @return whether an event subscriber has modified the resulting block. Used by the implementation.
		 */
		public boolean blockModified() {
			return this.modifiedBlock;
		}
	}

	private static interface ShuttlePlayerBlockEvents extends ShuttlePlayerBlockBreakEvent, ShuttlePlayerBlockPlaceEvent {
	}
}
