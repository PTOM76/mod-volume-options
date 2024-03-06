package net.pitan76.mvo76.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.SimpleScreen;
import net.pitan76.mcpitanlib.api.client.gui.screen.ScreenTexts;
import net.pitan76.mcpitanlib.api.client.gui.widget.SimpleListWidget;
import net.pitan76.mcpitanlib.api.client.gui.widget.SimpleSliderWidget;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.client.render.screen.RenderBackgroundTextureArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.mvo76.*;

import java.io.IOException;
import java.util.List;

public class ModVolumeOptionsScreen extends SimpleScreen {
    protected final Screen parent;
    protected SimpleListWidget listWidget;

    public ModVolumeOptionsScreen(Screen parent, Text title) {
        super(title);
        this.parent = parent;
    }

    public ModVolumeOptionsScreen(Screen parent) {
        this(parent, TextUtil.translatable("screen.mvo.options.title"));
    }

    @Override
    public void initOverride() {
        listWidget = new SimpleListWidget(MinecraftClient.getInstance(), width, height, 32, 25);

        List<ModInfo> modList = Platform.getModInfoList();
        if (modList == null) return;

        List<String> soundEventNameSpaces = MVOUtil.getSoundEventNameSpaces();
        for (String nameSpace : soundEventNameSpaces) {
            if (ModVolumeOptions.disabledModIds.contains(nameSpace)) continue;

            String name = modList.stream().filter(modInfo -> modInfo.getId().equals(nameSpace)).findFirst().map(modInfo -> modInfo.name).orElse(nameSpace);

            SimpleSliderWidget option = new SimpleSliderWidget(listWidget, 310, TextUtil.literal(name), Config.getVolume(nameSpace), (arg, d) ->
                    d == 0.0 ? getGenericValueText(arg, ScreenTexts.OFF) : getPercentValueText(arg, d),
                    (d) -> Config.setVolume(nameSpace, d));

            listWidget.add(option);
        }

        addSelectableChild_compatibility(listWidget);
        addDrawableChild_compatibility(ScreenUtil.createButtonWidget(width / 2 - 100, height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null) return;
            client.options.write();
            ClientUtil.setScreen(parent);
        }));
    }

    private static Text getPercentValueText(Text prefix, double value) {
        return TextUtil.translatable("options.percent_value", prefix, (int)(value * 100.0));
    }

    public static Text getGenericValueText(Text prefix, Text value) {
        return TextUtil.translatable("options.generic_value", prefix, value);
    }

    @Override
    public void removedOverride() {
        if (client == null) return;
        try {
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeOverride() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        ClientUtil.setScreen(this.parent);
    }

    @Override
    public void render(RenderArgs args) {
        this.renderBackground(args);
        this.listWidget.render(args);
        ScreenUtil.RendererUtil.drawText(textRenderer, args.drawObjectDM, title, width / 2 - ScreenUtil.getWidth(title) / 2, 20, 16777215);
        super.render(args);
    }

    @Override
    public void renderBackground(RenderArgs args) {
        renderBackgroundTexture(new RenderBackgroundTextureArgs(args));
    }
}
