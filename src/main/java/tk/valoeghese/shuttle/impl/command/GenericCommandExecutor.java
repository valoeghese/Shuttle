package tk.valoeghese.shuttle.impl.command;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import tk.valoeghese.shuttle.api.command.CommandExecutor;
import tk.valoeghese.shuttle.api.util.Vec2i;

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
	public Vec2i getChunkCoordinates() {
		return this.entity == null ? Vec2i.ORIGIN : new Vec2i(this.entity.chunkX, this.entity.chunkZ);
	}

	@Override
	public BlockPos getBlockCoordinates() {
		return this.entity == null ? BlockPos.ORIGIN : this.entity.getBlockPos();
	}

	@Override
	public Vec3d getCoordinates() {
		return this.entity == null ? Vec3d.ZERO : this.entity.getPos();
	}
}
