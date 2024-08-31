package net.virtuguy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.virtuguy.SittingMod;

public class ModEntities {
    public static final EntityType<Entity> SIT_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SittingMod.MOD_ID, "sit_entity"),
            EntityType.Builder.create(SitEntity::new, SpawnGroup.MISC)
                    .disableSummon()
                    .passengerAttachments(0f)
                    .build()
    );

    public static void initialize() {
        SittingMod.LOGGER.info("SittingMod entities initialized!");
    }
}
