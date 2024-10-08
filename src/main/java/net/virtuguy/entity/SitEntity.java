package net.virtuguy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

public class SitEntity extends Entity {
    public SitEntity(EntityType<Entity> type, World world) {
        super(type, world);
        setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();

        // Discards the sit entity if no player is sitting
        if (!getWorld().isClient) {
            if (!hasPassengers() || Objects.requireNonNull(getFirstPassenger()).doesNotCollide(0f, 0.1f, 0f))
                discard();
        }
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        return passenger.getPos().add(0f, 0.6f, 0f);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}
}
