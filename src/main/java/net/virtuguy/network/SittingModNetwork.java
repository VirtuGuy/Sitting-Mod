package net.virtuguy.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.virtuguy.SittingMod;
import net.virtuguy.client.SitHandler;
import net.virtuguy.entity.ModEntities;
import net.virtuguy.entity.SitEntity;

public class SittingModNetwork {
    // Packet ids
    public static final Identifier SIT_PACKET_ID = Identifier.of(SittingMod.MOD_ID, "sit");

    // Registers the payloads
    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(SitPayload.ID, SitPayload.CODEC);
    }

    // Handles the server payloads
    public static void handleServerPayloads() {
        // Receives the player sit payload
        ServerPlayNetworking.registerGlobalReceiver(SitPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            SitHandler.setSitting(payload.sit(), player);
        });
    }
}
