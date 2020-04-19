package tk.valoeghese.shuttle.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.dimension.DimensionType;

// This is originally from an All Rights Reserved licensed project, Lint
// But I was the one who added it, anyway, and it's basically just adapted from minecraft's teleport command code
// So I think it's fine
public class TeleportUtils {
	public static boolean teleport(LivingEntity entity, DimensionType dim, BlockPos pos) {
		// chunkpos
		ChunkPos cPos = new ChunkPos(pos);
		// get world for dimension
		ServerWorld world = entity.getServer().getWorld(dim);

		if (entity.world == world) {
			return false;
		}

		if (entity instanceof ServerPlayerEntity) {
			// teleport ticket
			world.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, cPos, 1, entity.getEntityId());
			// stop riding
			entity.stopRiding();
			// load chunks
			world.getChunk(pos);
			world.getChunk(pos.add(0, 0, 16));
			world.getChunk(pos.add(16, 0, 0));
			world.getChunk(pos.add(16, 0, 16));
			// teleport
			((ServerPlayerEntity) entity).teleport(world, pos.getX() + 0.5, world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1, pos.getZ() + 0.5, entity.yaw, entity.pitch);
			// close containers
			((PlayerEntity) entity).openContainer(null);
			return true;
		} else {
			// magic mojang code
			float yaw = MathHelper.wrapDegrees(entity.yaw);
			float pitch = MathHelper.wrapDegrees(entity.pitch);
			pitch = MathHelper.clamp(pitch, -90.0F, 90.0F);
			entity.detach();
			entity.dimension = world.dimension.getType();
			Entity eRef = entity;

			entity = (LivingEntity) entity.getType().create(world);
			if (entity == null) {
				return false;
			}

			entity.copyFrom(eRef);
			entity.refreshPositionAndAngles(pos.getX() + 0.5, world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1, pos.getZ() + 0.5, yaw, pitch);
			entity.setHeadYaw(yaw);
			world.onDimensionChanged(entity);
			entity.removed = true;
			return true;
		}
	}

	public static boolean teleport(LivingEntity entity, DimensionType dim, Vec3d position) {
		// chunkpos
		BlockPos pos = new BlockPos(position);
		ChunkPos cPos = new ChunkPos(pos);
		// get world for dimension
		ServerWorld world = entity.getServer().getWorld(dim);

		if (entity.world == world) {
			return false;
		}

		if (entity instanceof ServerPlayerEntity) {
			// teleport ticket
			world.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, cPos, 1, entity.getEntityId());
			// stop riding
			entity.stopRiding();
			// load chunks
			world.getChunk(pos);
			world.getChunk(pos.add(0, 0, 16));
			world.getChunk(pos.add(16, 0, 0));
			world.getChunk(pos.add(16, 0, 16));
			// teleport
			((ServerPlayerEntity) entity).teleport(world, position.x, world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1, position.getZ(), entity.yaw, entity.pitch);
			// close containers
			((PlayerEntity) entity).openContainer(null);
			return true;
		} else {
			// magic mojang code
			float yaw = MathHelper.wrapDegrees(entity.yaw);
			float pitch = MathHelper.wrapDegrees(entity.pitch);
			pitch = MathHelper.clamp(pitch, -90.0F, 90.0F);
			entity.detach();
			entity.dimension = world.dimension.getType();
			Entity eRef = entity;

			entity = (LivingEntity) entity.getType().create(world);
			if (entity == null) {
				return false;
			}

			entity.copyFrom(eRef);
			entity.refreshPositionAndAngles(position.x, world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1, position.z, yaw, pitch);
			entity.setHeadYaw(yaw);
			world.onDimensionChanged(entity);
			entity.removed = true;
			return true;
		}
	}
}
