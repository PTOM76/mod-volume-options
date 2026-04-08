package net.pitan76.mvo76.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class NotMCPitanLibScreen extends Screen {
    protected Screen parent;

    public NotMCPitanLibScreen(Screen parent) {
        super(Component.literal(""));
        this.parent = parent;
    }

    @Override
    public void init() {
        try {
            Button.Builder builder = Button.builder(CommonComponents.GUI_DONE, (button) -> {
                Minecraft client = Minecraft.getInstance();
                if (client == null) return;
                client.options.save();
                client.setScreen(parent);
            }).bounds(width / 2 - 100, height - 27, 200, 20);

            addRenderableWidget(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        try {
            String str = "Not MCPitanLib. Required for this screen.";
            context.drawCenteredString(font, Component.literal(str).getVisualOrderText(), width / 2, height / 2, 0xFFFFFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}