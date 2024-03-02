package net.pitan76.mvo76;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;
import java.util.List;

public class Platform {
    @ExpectPlatform
    public static Path getGameDir() {
        return null;
    }

    @ExpectPlatform
    public static Path getConfigDir() {
        return null;
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        return false;
    }

    @ExpectPlatform
    public static List<String> getModIdList() {
        return null;
    }

    @ExpectPlatform
    public static List<ModInfo> getModInfoList() {
        return null;
    }
}
