package net.virtuguy;

import net.fabricmc.api.ModInitializer;

import net.virtuguy.entity.ModEntities;
import net.virtuguy.network.SittingModNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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