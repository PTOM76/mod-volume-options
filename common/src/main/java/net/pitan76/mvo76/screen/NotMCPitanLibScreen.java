package net.pitan76.mvo76.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;

public class NotMCPitanLibScreen extends Screen {
    protected Screen parent;

    public NotMCPitanLibScreen(Screen parent) {
        super(new LiteralText(""));
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
    public void render(MatrixStack stack, int mouseX, int mouseY, float delta) {
        try {
            String str = "Not MCPitanLib. Required for this screen.";
            drawCenteredTextWithShadow(stack, textRenderer, new LiteralText(str).asOrderedText(), width / 2, height / 2, 0xFFFFFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
