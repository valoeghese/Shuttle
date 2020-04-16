package tk.valoeghese.shuttle.impl.player;

import java.util.UUID;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import tk.valoeghese.shuttle.api.command.PermissionLevel;
import tk.valoeghese.shuttle.api.player.Inventory;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.Vec2i;
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
	public Vec2i getChunkCoordinates() {
		return new Vec2i(this.parent.chunkX, this.parent.chunkZ);
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
}
