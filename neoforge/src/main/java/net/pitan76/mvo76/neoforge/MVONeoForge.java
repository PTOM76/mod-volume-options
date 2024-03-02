package net.pitan76.mvo76.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.mvo76.ModVolumeOptions;

@Mod(ModVolumeOptions.MOD_ID)
public class MVONeoForge {
    public MVONeoForge(IEventBus modEventBus) {
        modEventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(final FMLClientSetupEvent e) {
        ModVolumeOptions.init();
    }
}