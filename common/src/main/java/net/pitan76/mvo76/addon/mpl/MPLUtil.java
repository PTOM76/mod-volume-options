package net.pitan76.mvo76.addon.mpl;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.util.SoundEventUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.mvo76.screen.ModVolumeOptionsScreen;

public class MPLUtil {
    public static String txt2str_TextUtil(Component text) {
        return TextUtil.txt2str(text);
    }

    public static Component translatable_TextUtil(String key) {
        return TextUtil.translatable(key);
    }

    public static Component literal_TextUtil(String text) {
        return TextUtil.literal(text);
    }

    public static void setScreen_ClientUtil(Screen screen) {
        ClientUtil.setScreen(screen);
    }

    public static void setScreenModVolumeOptionsScreen() {
        setScreen_ClientUtil(new ModVolumeOptionsScreen(MPLUtil.getScreen_ClientUtil()));
    }

    public static Screen getScreen_ClientUtil() {
        return ClientUtil.getScreen();
    }

    public static Button createButtonWidget_ScreenUtil(int x, int y, int width, int height, Component message, Button.OnPress onPress) {
        return ScreenUtil.createButtonWidget(x, y, width, height, message, onPress);
    }

    public static String getSoundNamespace_MPLUtil(SoundEvent event) {
        return SoundEventUtil.getCompatId(event).getNamespace();
    }
}
