package net.pitan76.mvo76.fabric.addon;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.pitan76.mvo76.Platform;
import net.pitan76.mvo76.screen.ConfigScreen;
import net.pitan76.mvo76.screen.NotMCPitanLibScreen;

public class ModMenuAddon implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (Platform.isModLoaded("mcpitanlib"))
            return ConfigScreen::new;
        else
            return NotMCPitanLibScreen::new;
    }
}
