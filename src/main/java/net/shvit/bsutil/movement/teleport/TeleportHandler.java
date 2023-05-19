package net.shvit.bsutil.movement.teleport;

import net.shvit.bsutil.chat.Messages;
import net.shvit.bsutil.movement.warp.Warp;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportHandler {

    public static <T> void teleport(Player player, T input) {

        if (input instanceof Warp warp) {

            player.teleport(checkLocation(warp.location()));
            Messages.sendToDestination(player, warp);

        } else if (input instanceof Location location) {

            player.teleport(checkLocation(location));
            Messages.sendToDestination(player, location);
        }
    }

    public static Location checkLocation(Location location) {

        if (location.getBlock().isSolid()) {

            location.setY(location.getWorld().getHighestBlockYAt(location) + 1);

        }

        return location;

    }

}
