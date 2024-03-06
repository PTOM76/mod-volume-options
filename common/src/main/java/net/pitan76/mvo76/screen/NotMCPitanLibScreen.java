package net.pitan76.mvo76.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;

public class NotMCPitanLibScreen extends Screen {
    protected Screen parent;

    public NotMCPitanLibScreen(Screen parent) {
        super(Text.literal(""));
        this.parent = parent;
    }

    @Override
    public void init() {
        try {
            addDrawableChild(ScreenUtil.createButtonWidget(width / 2 - 100, height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client == null) return;
                client.options.write();
                client.setScreen(parent);
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        try {
            String str = "Not MCPitanLib. Required for this screen.";
            context.drawCenteredTextWithShadow(textRenderer, Text.literal(str).asOrderedText(), width / 2, height / 2, 0xFFFFFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
