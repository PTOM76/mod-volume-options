package net.pitan76.mvo76.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mvo76.ModVolumeOptions;

@Mod(ModVolumeOptions.MOD_ID)
public class MVOForge {
    public MVOForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    public void onClientSetup(final FMLClientSetupEvent e) {
        ModVolumeOptions.init();
    }
}