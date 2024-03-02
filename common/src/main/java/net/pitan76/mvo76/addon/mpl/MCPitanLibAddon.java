package net.pitan76.mvo76.addon.mpl;

import net.pitan76.mcpitanlib.api.command.CommandRegistry;

public class MCPitanLibAddon {
    public static void init() {
        CommandRegistry.register("mvo76", new MVOCommand());
    }
}
