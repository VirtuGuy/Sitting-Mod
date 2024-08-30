package net.virtuguy.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SitPayload(boolean sit) implements CustomPayload {
    public static final CustomPayload.Id<SitPayload> ID = new CustomPayload.Id<>(SittingModNetwork.SIT_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, SitPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, SitPayload::sit, SitPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
