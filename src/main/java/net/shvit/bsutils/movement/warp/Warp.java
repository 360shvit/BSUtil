package net.shvit.bsutils.movement.warp;

import net.shvit.bsutils.BSUtils;
import net.shvit.bsutils.chat.Messages;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public record Warp(String name) {

    public static Location location;

    public Warp location(Location loc) {

       location = loc;

        return this;
    }

    public Location location() {
        return location;
    }

    public Warp fromConfig() {
        try {
            if (BSUtils.getWarps().contains(name)) {

                location = (Location) BSUtils.getWarps().get(name + ".location");

            }else {
                return this;
            }

        }catch (Exception exception)  {

            exception.printStackTrace();
        }
        return this;
    }

    public void toConfig() {

        if(BSUtils.getWarps().contains(name)) {

            //TODO Send message Warp Already Exists
            return;
        }

        BSUtils.getWarps().set(name + ".location", location);
        BSUtils.getWarps().save();
    }

    public void remove() {

        if (BSUtils.getWarps().contains(name)) {

            BSUtils.getWarps().set(name, null);
            BSUtils.getWarps().save();


        }

    }

}
