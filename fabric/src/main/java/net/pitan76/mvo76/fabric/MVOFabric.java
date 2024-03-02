package net.pitan76.mvo76.fabric;

import net.pitan76.mvo76.ModVolumeOptions;
import net.fabricmc.api.ModInitializer;

public class MVOFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModVolumeOptions.init();
    }
}