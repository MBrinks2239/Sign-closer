package stellar.signcloser;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignCloser implements ModInitializer {
	public static KeyBinding toggleKey;
	public static boolean isSignGuiEnabled = true;

	@Override
	public void onInitialize() {
		toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.signcloser.toggle",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_H,
				"category.signcloser"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (toggleKey.wasPressed()) {
				isSignGuiEnabled = !isSignGuiEnabled;
				String messageKey = isSignGuiEnabled ? "message.signcloser.disabled" : "message.signcloser.enabled";
				Formatting color = isSignGuiEnabled ? Formatting.RED : Formatting.GREEN;
                assert client.player != null;
                client.player.sendMessage(
						Text.translatable(messageKey).formatted(color),
						true
				);
			}
		});
	}
}