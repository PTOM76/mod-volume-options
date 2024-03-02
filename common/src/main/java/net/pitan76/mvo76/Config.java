package net.pitan76.mvo76;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Config {
    // Volume rate for each mod
    public static Map<String, Double> volumeMap = new HashMap<>();
    public static File configFile = new File(Platform.getConfigDir().toFile(), "mod_volume_controller.json");

    public static void setVolume(String modid, double volume) {
        volumeMap.put(modid, volume);
    }

    public static double getVolume(String modid) {
        return volumeMap.getOrDefault(modid, 1.0);
    }

    public static void removeVolume(String modid) {
        volumeMap.remove(modid);
    }

    public static boolean hasVolume(String modid) {
        return volumeMap.containsKey(modid);
    }

    public static void init() {
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(volumeMap);
        // Save json to file
        Files.write(configFile.toPath(), json.getBytes());
    }

    public static void load() throws IOException {
        if (configFile.exists()) {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(configFile.toPath()));
            volumeMap = gson.fromJson(json, volumeMap.getClass());
        } else {
            if (!configFile.getParentFile().exists())
                configFile.getParentFile().mkdirs();

            save();
        }
    }

    public static File getConfigFile() {
        return configFile;
    }
}
