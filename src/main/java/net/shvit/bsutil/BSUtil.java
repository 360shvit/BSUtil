package net.shvit.bsutil;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.shvit.bsutil.chat.Message;
import net.shvit.bsutil.command.SpeedCommand;
import net.shvit.bsutil.command.SpeedToolCommand;
import net.shvit.bsutil.command.WarpCommand;
import net.shvit.bsutil.io.FileBuilder;
import net.shvit.bsutil.listener.SpeedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Logger;

public final class BSUtil extends JavaPlugin {

    public static BSUtil plugin;
    public static TextComponent prefix;
    public static FileBuilder speedTools, warps;

    public static BSUtil getPlugin() {
        return plugin;
    }

    public static TextComponent getPrefix() {
        return prefix;
    }

    public static FileBuilder getSpeedTools() {
        return speedTools;
    }

    public static FileBuilder getWarps() {

        return warps;

    }

    @NotNull
    public static Logger logger() {
        return plugin.getServer().getLogger();
    }

    @Override
    public void onEnable() {

        plugin = this;

        init();

    }

    @Override
    public void onDisable() {

        plugin = null;
    }

    public void init() {

        Objects.requireNonNull(this.getCommand("warp")).setExecutor(new WarpCommand());
        Objects.requireNonNull(this.getCommand("speedtool")).setExecutor(new SpeedToolCommand());
        Objects.requireNonNull(this.getCommand("speed")).setExecutor(new SpeedCommand());

        PluginManager pluginManger = Bukkit.getPluginManager();
        pluginManger.registerEvents(new SpeedEvent(), this);

        initSpeedToolConfig();
        initWarpsConfig();
        initConfig();

        prefix();

        plugin.getLogger().info("Initialized BSUtil!");
    }

    public void initConfig() {

        if (!plugin.getConfig().contains("BSUtil.defaults.prefix")) {
            plugin.getConfig().set("BSUtil.defaults.prefix.startCap", "[");
            plugin.getConfig().set("BSUtil.defaults.prefix.capColor", "10132122");
            plugin.getConfig().set("BSUtil.defaults.prefix.body", "");
            plugin.getConfig().set("BSUtil.defaults.prefix.color", "43690");
            plugin.getConfig().set("BSUtil.defaults.prefix.endCap", "] ");
            plugin.saveConfig();
        }

        if (!plugin.getConfig().contains("BSUtil.defaults.settings.warp")) {
            plugin.getConfig().set("BSUtil.defaults.settings.warp.list-length", 10);
            plugin.saveConfig();
        }

        if (!plugin.getConfig().contains("BSUtil.defaults.globalSpeedTool")) {
            plugin.getConfig().set("BSUtil.defaults.globalSpeedTool.item", Material.DIAMOND_HOE.toString());
            plugin.getConfig().set("BSUtil.defaults.globalSpeedTool.left", 1.0);
            plugin.getConfig().set("BSUtil.defaults.globalSpeedTool.right", 10.0);
            plugin.saveConfig();
        }


        plugin.getLogger().info("Config setup performed!");

    }

    public void initSpeedToolConfig() {

        speedTools = new FileBuilder(plugin.getDataFolder().getPath(), "speedTools.yml").save();

    }

    public void initWarpsConfig() {

        warps = new FileBuilder(plugin.getDataFolder().getPath(), "warps.yml").save();

    }

    public void prefix() {

        TextColor capColor = TextColor.color(Integer.parseInt(Objects
                .requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.prefix.capColor"))
                .toString()));
        TextColor color = TextColor.color(Integer.parseInt(Objects
                .requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.prefix.color"))
                .toString()));

        prefix = new Message()
                .addText(Objects
                        .requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.prefix.startCap"))
                        .toString(), capColor)
                .addText(
                        Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.prefix.body")).toString(),
                        color,
                        TextDecoration.BOLD
                )
                .addText(
                        Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.prefix.endCap")).toString(),
                        capColor
                ).build();

    }

}
