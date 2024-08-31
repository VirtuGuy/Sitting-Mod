package net.virtuguy.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.virtuguy.SittingMod;
import net.virtuguy.handler.SitHandler;
import org.lwjgl.glfw.GLFW;

public class KeyHandler {
    // KeyBinds
    public static final KeyBinding SIT_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.sittingmod.sitting",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "category.sittingmod"
    ));

    // Sitting
    static boolean pressingSitKey = false;

    public static void initialize() {
        SittingMod.LOGGER.info("SittingMod Key Handler initialized!");

        // Registers the end client tick event for sitting
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = client.player;
            if (player != null) {
                if (SIT_KEY.isPressed() && !pressingSitKey) {
                    pressingSitKey = true;
                    SitHandler.setSitting(!SitHandler.isSitting(player), null);
                }
                if (!SIT_KEY.isPressed() && pressingSitKey)
                    pressingSitKey = false;
            }
        });
    }
}
