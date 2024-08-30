package net.virtuguy.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.virtuguy.SittingMod;
import net.virtuguy.entity.ModEntities;
import net.virtuguy.render.SitEntityRenderer;

public class SittingModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SittingMod.LOGGER.info("Hello Fabric client!");

        // Registers the sit entity renderer
        EntityRendererRegistry.register(ModEntities.SIT_ENTITY, SitEntityRenderer::new);

        // Initializes the Key Handler
        KeyHandler.initialize();
    }
}
