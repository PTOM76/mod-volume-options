package net.pitan76.mvo76.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.pitan76.mvo76.ModVolumeOptions;
import net.pitan76.mvo76.addon.mpl.MPLUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin extends GameOptionsScreen {
    public SoundOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "addOptions", at = @At("TAIL"))
    private void mvo76$init(CallbackInfo ci) {
        if (!ModVolumeOptions.isMCPitanLibLoaded) return;

        SoundOptionsScreen screen = (SoundOptionsScreen) (Object) this;
        String btnText = MPLUtil.txt2str_TextUtil(MPLUtil.translatable_TextUtil("screen.mvo.options.title")) + "...";
        addDrawableChild(MPLUtil.createButtonWidget_ScreenUtil(screen.width / 2 + 70, 7, 120, 20, MPLUtil.literal_TextUtil(btnText),
                (button) -> MPLUtil.setScreenModVolumeOptionsScreen()));
    }
}
