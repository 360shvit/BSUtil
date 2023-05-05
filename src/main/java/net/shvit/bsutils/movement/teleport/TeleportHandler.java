package net.shvit.bsutils.movement.teleport;

import net.shvit.bsutils.chat.Messages;
import net.shvit.bsutils.movement.warp.Warp;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportHandler {

    public static <T> void teleport(Player player, T location) {

        if(location instanceof Warp) {
            player.teleport(checkLocation(((Warp) location).location()));
            Messages.sendToDestination(player, (Warp) location);
        }else if(location instanceof Location) {
            player.teleport(checkLocation((Location) location));
            Messages.sendToDestination(player, (Location) location);
        }

    }

    public static Location checkLocation(Location location) {

        if(location.getBlock().isSolid()) {

            location.setY(location.getWorld().getHighestBlockYAt(location) + 1);

        }

        return location;

    }

}
