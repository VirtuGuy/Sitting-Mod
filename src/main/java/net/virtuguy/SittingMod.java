package net.virtuguy;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.virtuguy.client.SitHandler;
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

		// Handles the server payloads
		SittingModNetwork.handleServerPayloads();
	}
}