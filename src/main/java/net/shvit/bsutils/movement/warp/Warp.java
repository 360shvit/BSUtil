package net.shvit.bsutils.movement.warp;

import net.shvit.bsutils.BSUtils;
import org.bukkit.Location;


public class Warp {

    private String name;
    private Location location;

    public Warp(String name) {

        if (BSUtils.getWarps().contains(name)) {

            load(name);

        } else {
            this.name = name;
        }

    }

    public void load(String name) {

        //input -> null = return
        if (name == null) {
            return;
        }

        //Config contains input init name
        if (BSUtils.getWarps().contains(name)) {
            this.name = name;

            try {
                this.location = Location.deserialize(BSUtils.getWarps().getSection(name + ".location"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            BSUtils.logger().info("Could not load Warp: File does not contain given warp name");
        }
    }

    public Warp location(Location location) {

        if (location != null) {
            this.location = location;
        }
        return this;
    }

    public Location location() {

        Location loc = null;

        try {
            loc = Location.deserialize(BSUtils.getWarps().getSection(this.name + ".location"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return loc;
    }

    public void toConfig() {

        if (BSUtils.getWarps().contains(this.name)) {
            return;
        }

        BSUtils.getWarps().set(this.name + ".location", this.location.serialize());
        BSUtils.getWarps().save();

    }

    public void remove() {

        if (BSUtils.getWarps().contains(this.name)) {

            BSUtils.getWarps().set(this.name, null);
            BSUtils.getWarps().save();

        }

    }

    public String name() {
        return this.name;
    }

}
