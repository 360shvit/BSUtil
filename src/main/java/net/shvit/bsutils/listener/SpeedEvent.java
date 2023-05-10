package net.shvit.bsutils.listener;

import net.shvit.bsutils.movement.speed.speedtool.SpeedTool;
import net.shvit.bsutils.movement.speed.speedtool.SpeedToolHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SpeedEvent implements Listener {

    @EventHandler
    public void onClick(@NotNull PlayerInteractEvent event) {

        if (event.getItem() != null) {

            Player player = event.getPlayer();
            SpeedTool speedTool = new SpeedTool(player).fromConfig();

            if (event.getItem().getType().equals(speedTool.material())) {

                if (!event.getItem().getItemMeta().hasLore()) {

                    SpeedToolHandler.giveTool(player);

                } else if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasLore() && !Objects.equals(event
                        .getItem()
                        .getItemMeta()
                        .lore(), speedTool.toItem().getItemMeta().lore())) {

                    SpeedToolHandler.giveTool(player);

                }

                SpeedToolHandler.interact(player, event, speedTool);

            }
        }
    }

}
