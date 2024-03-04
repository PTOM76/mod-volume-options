package net.pitan76.mvo76.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.mvo76.Platform;
import net.pitan76.mvo76.screen.ModVolumeOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin extends GameOptionsScreen {
    public SoundOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void mvo76$init(CallbackInfo ci) {
        if (!Platform.isModLoaded("mcpitanlib")) return;
        SoundOptionsScreen screen = (SoundOptionsScreen) (Object) this;
        addDrawableChild(ScreenUtil.createButtonWidget(screen.width / 2 + 70, 7, 120, 20, TextUtil.translatable("screen.mvo.options.title").append("..."), (button) -> {
            MinecraftClient.getInstance().setScreen(new ModVolumeOptionsScreen(MinecraftClient.getInstance().currentScreen));
        }));
    }
}
