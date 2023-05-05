package net.shvit.bsutils;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.shvit.bsutils.chat.Message;
import net.shvit.bsutils.command.SpeedCommand;
import net.shvit.bsutils.command.SpeedToolCommand;
import net.shvit.bsutils.command.WarpCommand;
import net.shvit.bsutils.listener.SpeedEvent;
import net.shvit.bsutils.io.FileBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BSUtils extends JavaPlugin {

    public static BSUtils plugin;
    public static TextComponent prefix;
    public static FileBuilder speedTools, warps;

    public static BSUtils getPlugin() {
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

        plugin.getLogger().info("Initialized BSUtils!");
    }

    public void initConfig() {

        if (!plugin.getConfig().contains("BSUtils.prefix")) {
            plugin.getConfig().set("BSUtils.prefix.startCap", "[");
            plugin.getConfig().set("BSUtils.prefix.capColor", "10132122");
            plugin.getConfig().set("BSUtils.prefix.body", "");
            plugin.getConfig().set("BSUtils.prefix.color", "43690");
            plugin.getConfig().set("BSUtils.prefix.endCap", "] ");
            plugin.saveConfig();
        }

        if (!plugin.getConfig().contains("BSUtils.globalSpeedTool")) {
            plugin.getConfig().set("BSUtils.globalSpeedTool.item", Material.DIAMOND_HOE.toString());
            plugin.getConfig().set("BSUtils.globalSpeedTool.left", 1.0);
            plugin.getConfig().set("BSUtils.globalSpeedTool.right", 10.0);
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

        TextColor capColor = TextColor.color(Integer.parseInt(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("BSUtils.prefix.capColor")).toString()));
        TextColor color = TextColor.color(Integer.parseInt(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("BSUtils.prefix.color")).toString()));

        prefix = new Message()
                .addText(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("BSUtils.prefix.startCap")).toString(), capColor)
                .addText(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("BSUtils.prefix.body")).toString(), color, TextDecoration.BOLD)
                .addText(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("BSUtils.prefix.endCap")).toString(), capColor).build();

    }

}
