package net.virtuguy;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.virtuguy.entity.ModEntities;
import net.virtuguy.entity.SitEntity;
import net.virtuguy.network.SitPayload;
import net.virtuguy.network.SittingModNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SittingMod implements ModInitializer {
	public static final String MOD_ID = "sittingmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		// Registers the network payloads
		SittingModNetwork.registerPayloads();

		// Initializes the mod entities
		ModEntities.initialize();

		// Receives the player sit payload
		ServerPlayNetworking.registerGlobalReceiver(SitPayload.ID, (payload, context) -> {
			boolean sit = payload.sit();
			PlayerEntity player = context.player();
			World world = player.getWorld();

			if (sit) {
				if (player.isOnGround()) {
					SitEntity sitEntity = (SitEntity) ModEntities.SIT_ENTITY.create(world);
					assert sitEntity != null;
					sitEntity.setPosition(player.getPos());
					sitEntity.setYaw(player.getYaw());

					world.spawnEntity(sitEntity);
					player.startRiding(sitEntity);
				}
			} else {
				if (player.hasVehicle() && Objects.requireNonNull(player.getVehicle()).getClass() == SitEntity.class)
					player.stopRiding();
			}
		});
	}
}