package tk.valoeghese.shuttle.impl;

import java.util.UUID;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import tk.valoeghese.shuttle.api.command.PermissionLevel;
import tk.valoeghese.shuttle.api.player.Player;
import tk.valoeghese.shuttle.api.util.Vec2i;

public class PlayerImpl implements Player {
	public PlayerImpl(ServerPlayerEntity parent) {
		this.parent = parent;
	}

	private final ServerPlayerEntity parent;

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
		return this.parent.getPos();
	}

	@Override
	public BlockPos getBlockCoordinates() {
		return this.parent.getBlockPos();
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
}
