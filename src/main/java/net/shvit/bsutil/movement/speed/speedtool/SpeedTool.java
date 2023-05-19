package net.shvit.bsutil.movement.speed.speedtool;

import net.shvit.bsutil.BSUtil;
import net.shvit.bsutil.chat.Message;
import net.shvit.bsutil.chat.Messages;
import net.shvit.bsutil.item.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public record SpeedTool(Player owner) {

    public static Material material;
    public static float leftClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.left")).toString()),
            rightClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.right")).toString());

    /*
    ========================
            Material
    ========================
    */

    public SpeedTool material(Material material) {
        if (material == null) {

            SpeedTool.material = Material.getMaterial(BSUtil.getSpeedTools().get("BSUtil.defaults.globalSpeedTool.item").toString());

        } else {

            SpeedTool.material = material;

        }

        return this;
    }

    public Material material() {
        return material;
    }


    /*
    ============================
            Click-Values
    ============================
     */

    public SpeedTool clickValues(float leftClick, float rightClick) {

        SpeedTool.leftClick = leftClick;
        SpeedTool.rightClick = rightClick;
        return this;

    }

    public SpeedTool leftClick(float clickValue) {

        SpeedTool.leftClick = clickValue;
        return this;

    }

    public float leftClick() {
        return leftClick;
    }

    public SpeedTool rightClick(float clickValue) {

        SpeedTool.rightClick = clickValue;
        return this;

    }

    public float rightClick() {
        return rightClick;
    }

    /*
    ==================================
            Values-From-Config
    ==================================
     */

    public SpeedTool fromConfig() {
        try {
            if (BSUtil.getSpeedTools().contains(owner.getName())) {

                SpeedTool.material = Material.getMaterial(Objects.requireNonNull(BSUtil.getSpeedTools().get(owner.getName() + ".item")).toString());
                SpeedTool.leftClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getSpeedTools().get(owner.getName() + ".left")).toString());
                SpeedTool.rightClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getSpeedTools().get(owner.getName() + ".right")).toString());

            } else {

                SpeedTool.material = Material.getMaterial(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.item")).toString());
                SpeedTool.leftClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.left")).toString());
                SpeedTool.rightClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.right")).toString());

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return this;
    }

    public SpeedTool clickValuesFromConfig() {

        try {
            if (BSUtil.getSpeedTools().contains(owner.getName())) {

                SpeedTool.leftClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getSpeedTools().get(owner.getName() + ".left")).toString());
                SpeedTool.rightClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getSpeedTools().get(owner.getName() + ".right")).toString());

            } else {

                SpeedTool.leftClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.left")).toString());
                SpeedTool.rightClick = Float.parseFloat(Objects.requireNonNull(BSUtil.getPlugin().getConfig().get("BSUtil.defaults.globalSpeedTool.right")).toString());

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return this;
    }

    /*
    ================================
            Values-To-Config
    ================================
    */

    public void toConfig() {

        BSUtil.getSpeedTools().set(owner.getName() + ".item", SpeedTool.material.toString());
        BSUtil.getSpeedTools().set(owner.getName() + ".left", SpeedTool.leftClick);
        BSUtil.getSpeedTools().set(owner.getName() + ".right", SpeedTool.rightClick);
        BSUtil.getSpeedTools().save();

        if (owner.getInventory().getItemInMainHand().getType().equals(SpeedTool.material)) {

            SpeedToolHandler.giveTool(owner);

        }
    }

    /*
    ==============================
            Values-To-Item
    ==============================
    */

    public ItemStack toItem() {

        Message lore = new Message().addText(leftClick + " ", Messages.HIGHLIGHTED_TEXT).addText("|", Messages.MAIN_TEXT).addText(" " + rightClick, Messages.HIGHLIGHTED_TEXT);
        return new Item(SpeedTool.material).displayname("SpeedTool").lore(lore).toItemStack();

    }

}
