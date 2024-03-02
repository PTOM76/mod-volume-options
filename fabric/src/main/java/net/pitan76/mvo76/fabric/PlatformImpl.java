package net.pitan76.mvo76.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.pitan76.mvo76.ModInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlatformImpl {
    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static List<String> getModIdList() {
        return FabricLoader.getInstance().getAllMods().stream().map(mod -> mod.getMetadata().getId()).collect(Collectors.toList());
    }

    public static List<ModInfo> getModInfoList() {
        List<ModInfo> list = new ArrayList<>();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            list.add(new ModInfo(mod.getMetadata().getId(), mod.getMetadata().getName()));
        }
        return list;
    }
}
