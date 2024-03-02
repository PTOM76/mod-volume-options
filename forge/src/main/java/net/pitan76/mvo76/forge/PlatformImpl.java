package net.pitan76.mvo76.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.IModInfo;
import net.pitan76.mvo76.ModInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlatformImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static List<String> getModIdList() {
        return ModList.get().getMods().stream().map(IModInfo::getModId).collect(Collectors.toList());
    }

    public static List<ModInfo> getModInfoList() {
        List<ModInfo> list = new ArrayList<>();
        for (IModInfo mod : ModList.get().getMods()) {
            list.add(new ModInfo(mod.getModId(), mod.getDisplayName()));
        }
        return list;
    }
}
