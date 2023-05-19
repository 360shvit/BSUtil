package net.shvit.bsutil.movement.warp;

import net.shvit.bsutil.BSUtil;
import org.bukkit.Location;


public class Warp {

    private String name;
    private Location location;

    public Warp(String name) {

        if (BSUtil.getWarps().contains(name)) {

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
        if (BSUtil.getWarps().contains(name)) {
            this.name = name;

            try {
                this.location = Location.deserialize(BSUtil.getWarps().getSection(name + ".location"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            BSUtil.logger().info("Could not load Warp: File does not contain given warp name");
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
            loc = Location.deserialize(BSUtil.getWarps().getSection(this.name + ".location"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return loc;
    }

    public void toConfig() {

        if (BSUtil.getWarps().contains(this.name)) {
            return;
        }

        BSUtil.getWarps().set(this.name + ".location", this.location.serialize());
        BSUtil.getWarps().save();

    }

    public void remove() {

        if (BSUtil.getWarps().contains(this.name)) {

            BSUtil.getWarps().set(this.name, null);
            BSUtil.getWarps().save();

        }

    }

    public String name() {
        return this.name;
    }

}
