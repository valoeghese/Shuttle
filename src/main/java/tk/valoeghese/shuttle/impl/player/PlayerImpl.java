package tk.valoeghese.shuttle.impl.player;

import java.util.UUID;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import tk.valoeghese.shuttle.api.command.PermissionLevel;
import tk.valoeghese.shuttle.api.player.Inventory;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.ChunkPos;
import tk.valoeghese.shuttle.api.util.Vec3d;
import tk.valoeghese.shuttle.impl.Wrappers;

public class PlayerImpl implements Player {
	public PlayerImpl(ServerPlayerEntity parent) {
		this.parent = parent;
		this.inventory = new PlayerInventoryImpl(this.parent.inventory);
	}

	private final ServerPlayerEntity parent;
	private final PlayerInventoryImpl inventory;

	@Override
	public String getName() {
		return this.parent.getEntityName();
	}

	@Override
	public UUID getUUID() {
		return this.parent.getUuid();
	}

	@Override
	public void sendMessage(String message) {
		this.parent.sendMessage(new LiteralText(message));
	}

	@Override
	public ChunkPos getChunkCoordinates() {
		return new ChunkPos(this.parent.chunkX, this.parent.chunkZ);
	}

	@Override
	public Vec3d getCoordinates() {
		return Wrappers.wrap(this.parent.getPos());
	}

	@Override
	public BlockPos getBlockCoordinates() {
		return Wrappers.wrap(this.parent.getBlockPos());
	}

	@Override
	public boolean isInCreativeMode() {
		return this.parent.isCreative();
	}

	@Override
	public boolean isInSpectatorMode() {
		return this.parent.isSpectator();
	}

	@Override
	public PermissionLevel getPermissionLevel() {
		return this.parent.allowsPermissionLevel(2) ? PermissionLevel.OP : PermissionLevel.NORMAL;
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public void teleportTo(Vec3d position) {
		this.parent.setPos(position.x, position.y, position.z);
	}

	@Override
	public void teleportTo(BlockPos position) {
		this.parent.setPos(position.x + 0.5, position.y + 0.5, position.z + 0.5);
	}

	@Override
	public int hashCode() {
		return 3 * this.getUUID().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			return this.getUUID().equals(((Player) obj).getUUID());
		} else {
			return false;
		}
	}
}
