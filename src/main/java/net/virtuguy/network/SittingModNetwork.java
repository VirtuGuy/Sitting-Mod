package net.virtuguy.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;
import net.virtuguy.SittingMod;

public class SittingModNetwork {
    // Packet ids
    public static final Identifier SIT_PACKET_ID = Identifier.of(SittingMod.MOD_ID, "sit");

    // Registers the payloads
    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(SitPayload.ID, SitPayload.CODEC);
    }
}
