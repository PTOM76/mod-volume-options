package net.pitan76.mvo76;

import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class MVOUtil {
    public static List<String> getSoundEventNameSpaces() {
        List<String> soundEventNameSpaces = new ArrayList<>();
        // Todo: MCPitanlib
        Registry.SOUND_EVENT.getIds().forEach(id -> {
            String nameSpace = id.getNamespace();
            if (!soundEventNameSpaces.contains(nameSpace)) {
                soundEventNameSpaces.add(nameSpace);
            }
        });
        return soundEventNameSpaces;
    }
}
