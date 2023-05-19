package net.shvit.bsutil.movement.speed.speedtool;

import net.shvit.bsutil.movement.speed.SpeedHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class SpeedToolHandler {

    public static void setTool(@NotNull Player player) {

        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {

            SpeedTool speedTool = new SpeedTool(player)
                    .material(player.getInventory().getItemInMainHand().getType())
                    .clickValuesFromConfig();
            speedTool.toConfig();

        }

    }

    public static void setClickValues(Player player, String leftClick, String rightClick) {

        SpeedTool speedTool = new SpeedTool(player).fromConfig()
                .clickValues(Float.parseFloat(leftClick), Float.parseFloat(rightClick));

        speedTool.toConfig();

    }

    public static void setLeftClickValue(Player player, String leftClick) {

        SpeedTool speedTool = new SpeedTool(player).fromConfig()
                .leftClick(Float.parseFloat(leftClick));

        speedTool.toConfig();

    }

    public static void setRightClickValue(Player player, String rightClick) {

        SpeedTool speedTool = new SpeedTool(player).fromConfig()
                .rightClick(Float.parseFloat(rightClick));

        speedTool.toConfig();

    }

    public static void giveTool(Player player) {

        SpeedTool speedTool = new SpeedTool(player).fromConfig();
        player.getInventory().setItem(player.getInventory().getHeldItemSlot(), speedTool.toItem());

    }

    public static void giveTool(Player player, int slot) {

        SpeedTool speedTool = new SpeedTool(player).fromConfig();
        player.getInventory().setItem(slot, speedTool.toItem());

    }

    public static void interact(Player player, @NotNull PlayerInteractEvent event, SpeedTool speedTool) {

        if (event.getAction().isLeftClick()) {

            SpeedHandler.setSpeed(player, speedTool.leftClick());

        } else if (event.getAction().isRightClick()) {

            SpeedHandler.setSpeed(player, speedTool.rightClick());

        }
    }

}
