package net.pitan76.mvo76.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.SimpleScreen;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.mvo76.*;
import net.pitan76.mvo76.screen.widget.SimpleListWidget;
import net.pitan76.mvo76.screen.widget.SimpleSliderWidget;

import java.io.IOException;
import java.util.List;

public class ConfigScreen extends SimpleScreen {
    protected final Screen parent;
    protected SimpleListWidget listWidget;

    public ConfigScreen(Screen parent, Text title) {
        super(title);
        this.parent = parent;
    }

    public ConfigScreen(Screen parent) {
        this(parent, TextUtil.translatable("screen.mvo.options.title"));
    }

    @Override
    public void initOverride() {
        listWidget = new SimpleListWidget(client, width, height - 64, 32, 25);
        addDrawableChild_compatibility(listWidget);

        List<ModInfo> modList = Platform.getModInfoList();
        if (modList == null) return;

        List<String> soundEventNameSpaces = MVOUtil.getSoundEventNameSpaces();
        for (String nameSpace : soundEventNameSpaces) {
            if (ModVolumeOptions.disabledModIds.contains(nameSpace)) continue;

            String name = modList.stream().filter(modInfo -> modInfo.getId().equals(nameSpace)).findFirst().map(modInfo -> modInfo.name).orElse(nameSpace);
            //String key = name;
           // String key = "options.mvo76." + nameSpace + ".volume";
            // register translation key to lang string

            // todo: MCPitanlib ScreenTexts
            SimpleSliderWidget option = new SimpleSliderWidget(listWidget, 300, TextUtil.literal(name), Config.getVolume(nameSpace), (arg, d) ->
                    d == 0.0 ? getGenericValueText(arg, ScreenTexts.OFF) : getPercentValueText(arg, d),
                    (d) -> Config.setVolume(nameSpace, d));

            listWidget.add(option);
        }

        addDrawableChild_compatibility(ScreenUtil.createButtonWidget(width / 2 - 100, height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
            if (client == null) return;
            client.options.write();
            client.setScreen(parent);
        }));
    }

    private static Text getPercentValueText(Text prefix, double value) {
        return TextUtil.translatable("options.percent_value", prefix, (int)(value * 100.0));
    }

    public static Text getGenericValueText(Text prefix, Text value) {
        return TextUtil.translatable("options.generic_value", prefix, value);
    }

    public void removed() {
        if (client == null) return;
        try {
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (client == null) return;
        client.setScreen(this.parent);
    }

    @Override
    public void render(RenderArgs args) {
        super.render(args);
        ScreenUtil.RendererUtil.drawText(textRenderer, args.drawObjectDM, title, width / 2 - ScreenUtil.getWidth(title) / 2, 20, 16777215);
    }

    /*
    // todo: mcpitanlib
    @Override
    public void renderBackground(RenderArgs args) {
        renderBackgroundTexture(args.drawObjectDM.getContext());
    }
     */
}
