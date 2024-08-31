package net.virtuguy.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.virtuguy.entity.ModEntities;
import net.virtuguy.entity.SitEntity;
import net.virtuguy.network.SitPayload;

import java.util.Objects;

public class SitHandler {
    /**
     * Sets the player's sitting position.
     * <p><b>
     * Make player null if you want to run the method from the client
     * </b>
     */
    public static void setSitting(boolean sit, ServerPlayerEntity player) {
        if (player == null)
            ClientPlayNetworking.send(new SitPayload(sit));
        else {
            if (sit && player.isOnGround()) {
                SitEntity sitEntity = (SitEntity) ModEntities.SIT_ENTITY.create(player.getWorld());
                assert sitEntity != null;
                sitEntity.setPosition(player.getPos());
                sitEntity.setYaw(player.getYaw());

                player.getWorld().spawnEntity(sitEntity);
                player.startRiding(sitEntity);
            } else if (!sit && isSitting(player))
                player.stopRiding();
        }
    }

    public static boolean isSitting(PlayerEntity player) {
        return (player.hasVehicle() && Objects.requireNonNull(player.getVehicle()).getType() == ModEntities.SIT_ENTITY);
    }
}
