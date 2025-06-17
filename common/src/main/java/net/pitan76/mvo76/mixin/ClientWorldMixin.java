package net.pitan76.mvo76.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mvo76.Config;
import net.pitan76.mvo76.ModVolumeOptions;
import net.pitan76.mvo76.addon.mpl.MPLUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {

    @Shadow
    public abstract void playSound(double x, double y, double z, SoundEvent event, SoundCategory category, float volume, float pitch, boolean useDistance);

    @Unique
    private static boolean mvo76$cancelSound = true;

    @Inject(method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", at = @At("HEAD"), cancellable = true)
    public void mvo76$playSound(double x, double y, double z, SoundEvent event, SoundCategory category, float volume, float pitch, boolean useDistance, CallbackInfo ci) {
        if (!mvo76$cancelSound) {
            mvo76$cancelSound = true;
            return;
        }
        String namespace;
        if (ModVolumeOptions.isMCPItanLibLoaded) {
            namespace = MPLUtil.getSoundNamespace_MPLUtil(event);
        } else {
            namespace = event.getId().getNamespace();
        }

        if (ModVolumeOptions.disabledModIds.contains(namespace)) return;
        if (!Config.hasVolume(namespace)) return;

        ci.cancel();
        float newVolume = ((float) Config.getVolume(namespace)) * volume;

        mvo76$cancelSound = false;
        playSound(x, y, z, event, category, newVolume, pitch, useDistance);
    }
}
