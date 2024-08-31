package net.virtuguy.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.virtuguy.SittingMod;
import net.virtuguy.client.SitHandler;

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
