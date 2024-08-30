package net.virtuguy.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.virtuguy.SittingMod;
import net.virtuguy.network.SitPayload;
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
    static boolean sitting = false;
    static boolean pressingSitKey = false;

    // Toggles the sitting position
    private static void toggleSit() {
        sitting = !sitting;
        ClientPlayNetworking.send(new SitPayload(sitting));
    }

    public static void initialize() {
        SittingMod.LOGGER.info("SittingMod Key Handler initialized!");

        // Registers the end client tick event for sitting
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (SIT_KEY.isPressed() && !pressingSitKey) {
                    pressingSitKey = true;
                    sitting = client.player.hasVehicle();
                    toggleSit();
                }

                if (!SIT_KEY.isPressed() && pressingSitKey)
                    pressingSitKey = false;
            }
        });
    }
}
