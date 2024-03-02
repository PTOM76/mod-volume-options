package net.pitan76.mvo76;

import net.pitan76.mvo76.addon.mpl.MCPitanLibAddon;

import java.util.ArrayList;
import java.util.List;

public class ModVolumeOptions {
    public static final String MOD_ID = "mvo76";
    public static final String MOD_NAME = "Mod Volume Options";

    public static List<String> disabledModIds = new ArrayList<>();

    public static void init() {
        Config.init();

        if (Platform.isModLoaded("mcpitanlib")) {
            MCPitanLibAddon.init();
        }
        initDisabledModIds();
    }

    public static void initDisabledModIds() {
        disabledModIds.add("minecraft");
        disabledModIds.add(MOD_ID);
    }
}