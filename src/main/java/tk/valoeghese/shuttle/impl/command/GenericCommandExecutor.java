package tk.valoeghese.shuttle.impl.command;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import tk.valoeghese.shuttle.api.command.CommandExecutor;
import tk.valoeghese.shuttle.api.command.PermissionLevel;
import tk.valoeghese.shuttle.api.util.BlockPos;
import tk.valoeghese.shuttle.api.util.ChunkPos;
import tk.valoeghese.shuttle.api.util.Vec3d;
import tk.valoeghese.shuttle.impl.Wrappers;

public class GenericCommandExecutor implements CommandExecutor {
	GenericCommandExecutor(ServerCommandSource source) {
		this.source = source;
		this.entity = source.getEntity();
	}

	private final ServerCommandSource source;
	private final Entity entity;

	@Override
	public void sendMessage(String message) {
		this.source.sendFeedback(new LiteralText(message), false);
	}

	@Override
	public String getName() {
		return this.source.getName();
	}

	@Override
	public ChunkPos getChunkCoordinates() {
		return this.entity == null ? ChunkPos.ORIGIN : new ChunkPos(this.entity.chunkX, this.entity.chunkZ);
	}

	@Override
	public BlockPos getBlockCoordinates() {
		return this.entity == null ? BlockPos.ORIGIN : Wrappers.wrap(this.entity.getBlockPos());
	}

	@Override
	public Vec3d getCoordinates() {
		return this.entity == null ? Vec3d.ORIGIN : Wrappers.wrap(this.entity.getPos());
	}

	@Override
	public PermissionLevel getPermissionLevel() {
		return this.source.hasPermissionLevel(2) ? PermissionLevel.OP : PermissionLevel.NORMAL;
	}
}
