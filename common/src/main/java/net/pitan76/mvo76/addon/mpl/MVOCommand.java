package net.pitan76.mvo76.addon.mpl;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.DoubleCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.DoubleCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mvo76.Config;

import java.io.IOException;

public class MVOCommand extends LiteralCommand {
    @Override
    public void execute(ServerCommandEvent event) {

    }

    @Override
    public void init(CommandSettings settings) {
        addArgumentCommand("reload", new LiteralCommand() {
            @Override
            public void execute(ServerCommandEvent event) {
                try {
                    Config.load();
                    event.sendSuccess(TextUtil.literal("Config file reloaded"), false);
                } catch (IOException e) {
                    e.printStackTrace();
                    event.sendFailure(TextUtil.literal("Failed to reload config file"));
                }
            }
        });

        addArgumentCommand("save", new LiteralCommand() {
            @Override
            public void execute(ServerCommandEvent event) {
                try {
                    Config.save();
                    event.sendSuccess(TextUtil.literal("Config file saved"), false);
                } catch (IOException e) {
                    e.printStackTrace();
                    event.sendFailure(TextUtil.literal("Failed to save config file"));
                }
            }
        });

        addArgumentCommand("setVolume", new LiteralCommand() {
            @Override
            public void execute(ServerCommandEvent event) {

            }

            @Override
            public void init(CommandSettings settings) {
                addArgumentCommand("modid", new StringCommand() {
                    @Override
                    public void init(CommandSettings settings) {
                        addArgumentCommand("value", new DoubleCommand() {
                            @Override
                            public String getArgumentName() {
                                return "volume";
                            }

                            @Override
                            public void execute(DoubleCommandEvent event) {
                                String modid = StringArgumentType.getString(event.context, "modid");
                                Double value = event.getValue();
                                Config.setVolume(modid, value);
                                event.sendSuccess(TextUtil.literal("Set volume of " + modid + " to " + value), false);
                            }
                        });
                    }

                    @Override
                    public void execute(StringCommandEvent event) {
                    }

                    @Override
                    public String getArgumentName() {
                        return "modid";
                    }
                });
            }
        });

        addArgumentCommand("getVolume", new LiteralCommand() {
            @Override
            public void init(CommandSettings settings) {
                addArgumentCommand("modid", new StringCommand() {
                    @Override
                    public void execute(StringCommandEvent event) {
                        String modid = StringArgumentType.getString(event.context, "modid");
                        double volume = Config.getVolume(modid);
                        event.sendSuccess(TextUtil.literal("Volume of " + modid + " is " + volume), false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "modid";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent event) {

            }
        });
    }
}
