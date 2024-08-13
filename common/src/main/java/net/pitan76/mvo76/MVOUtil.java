package net.pitan76.mvo76;

import net.pitan76.mcpitanlib.api.util.SoundEventUtil;

import java.util.ArrayList;
import java.util.List;

public class MVOUtil {
    public static List<String> getSoundEventNameSpaces() {
        List<String> soundEventNameSpaces = new ArrayList<>();
        SoundEventUtil.getAllSoundEventIds().forEach(id -> {
            String nameSpace = id.getNamespace();
            if (!soundEventNameSpaces.contains(nameSpace)) {
                soundEventNameSpaces.add(nameSpace);
            }
        });
        return soundEventNameSpaces;
    }
}
