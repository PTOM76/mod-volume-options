package net.pitan76.mvo76.addon.mpl;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;

public class MPLUtil {
    public static String txt2str_TextUtil(Text text) {
        return TextUtil.txt2str(text);
    }

    public static Text translatable_TextUtil(String key) {
        return TextUtil.translatable(key);
    }

    public static Text literal_TextUtil(String text) {
        return TextUtil.literal(text);
    }

    public static void setScreen_ClientUtil(Screen screen) {
        ClientUtil.setScreen(screen);
    }

    public static Screen getScreen_ClientUtil() {
        return ClientUtil.getScreen();
    }

    public static ButtonWidget createButtonWidget_ScreenUtil(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress) {
        return ScreenUtil.createButtonWidget(x, y, width, height, message, onPress);
    }
}
