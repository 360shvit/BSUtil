package net.shvit.bsutils.chat;

import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.shvit.bsutils.BSUtils;
import net.shvit.bsutils.movement.speed.SpeedHandler;
import net.shvit.bsutils.movement.warp.Warp;
import net.shvit.bsutils.movement.warp.WarpHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class Messages {

    public static final TextColor HIGHLIGHTED_TEXT = TextColor.color(0xF3F3),
            MAIN_TEXT = TextColor.color(0x00AAAA),
            WHITE = TextColor.color(0xFFFFFF),
            GRAY = TextColor.color(0x9A9A9A),
            RED = TextColor.color(0xFF604C),
            GREEN = TextColor.color(0x45C93E);
    /*
    ==============================================
                Speed related messages
    ==============================================
    */

    public static void sendSpeedToolInfo(@NotNull Player player) {

        Message message = new Message().newLine()
                .addText("       === ", GRAY).addText("SpeedTool Command", WHITE, TextDecoration.BOLD).addText(" ===", GRAY).newLine().newLine()
                .addClickText("/speedtool", ClickEvent.suggestCommand("/speedtool"), MAIN_TEXT).newLine()
                .addText("  Gives the currently set speedtool", HIGHLIGHTED_TEXT).newLine().newLine()
                .addClickText("/speedtool", ClickEvent.suggestCommand("/speedtool info"), MAIN_TEXT).newLine()
                .addText("  Opens this info page", HIGHLIGHTED_TEXT).newLine().newLine()
                .addClickText("/speedtool", ClickEvent.suggestCommand("/speedtool 2.45 10.0"), MAIN_TEXT)
                .addText(" <").addHoverText("arg1", "Valid input range: 0.0 - 10.0", WHITE).addText(">")
                .addText(" <").addHoverText("arg2", "Valid input range: 0.0 - 10.0 | -l | -r", WHITE).addText(">").newLine()
                .addText("  Sets the left- and,or right-click values", HIGHLIGHTED_TEXT).newLine()
                .addText("  Specify the value by setting arg2 to", HIGHLIGHTED_TEXT).newLine()
                .addText("      -l for left-click", HIGHLIGHTED_TEXT).newLine()
                .addText("      -r for right-click", HIGHLIGHTED_TEXT).newLine().newLine()
                .addClickText("/setspeedtool", ClickEvent.suggestCommand("/setspeedtool"), MAIN_TEXT).newLine()
                .addText("  Changes the speedtool item to the held item", HIGHLIGHTED_TEXT);

        message.send(player);
    }

    public static void sendSpeedInfo(@NotNull Player player) {

        Message message = new Message().newLine()
                .addText("       === ", GRAY).addText("Speed Command", WHITE, TextDecoration.BOLD).addText(" ===", GRAY).newLine().newLine()
                .addClickText("/speed", ClickEvent.suggestCommand("/speed"), MAIN_TEXT).newLine()
                .addText("  Returns the current speed value", HIGHLIGHTED_TEXT).newLine().newLine()
                .addClickText("/speed", ClickEvent.suggestCommand("/speed 2.45"), MAIN_TEXT)
                .addText(" <").addHoverText("arg1", "Valid input range: 0.0 - 10.0 | info", WHITE).addText(">")
                .addText("  Sets the speed to input value", HIGHLIGHTED_TEXT).newLine()
                .addText("  info as input opens this info page", HIGHLIGHTED_TEXT).newLine()
                .addClickText("/flyspeed", ClickEvent.suggestCommand("/flyspeed | /fs"), MAIN_TEXT).newLine()
                .addText("  Sets the flyspeed to input value", HIGHLIGHTED_TEXT)
                .addClickText("/walkspeed", ClickEvent.suggestCommand("/flyspeed | /fs"), MAIN_TEXT).newLine()
                .addText("  Sets the walkspeed to input value", HIGHLIGHTED_TEXT);

        message.send(player);
    }

    public static void sendCurrentSpeed(@NotNull Player player) {

        if (player.isFlying()) {

            sendCurrentFlySpeed(player);

        } else {

            sendCurrentWalkSpeed(player);

        }

    }

    public static void sendCurrentFlySpeed(@NotNull Player player) {

        Message message = new Message().addPrefix().addText("Current flying speed set to: ", MAIN_TEXT).addText(String.valueOf(SpeedHandler.getSpeed(player) * 10), HIGHLIGHTED_TEXT, TextDecoration.BOLD);
        message.send(player);

    }

    public static void sendCurrentWalkSpeed(@NotNull Player player) {

        Message message = new Message().addPrefix().addText("Current walking speed set to: ", MAIN_TEXT).addText(String.valueOf(SpeedHandler.getSpeed(player) * 10), HIGHLIGHTED_TEXT, TextDecoration.BOLD);
        message.send(player);

    }

    public static void sendInputRange(@NotNull Player player) {

        Message message = new Message().addPrefix().addText("Valid input range: ", MAIN_TEXT).addText("0.0 - 10", HIGHLIGHTED_TEXT);
        message.send(player);

    }

    public static void sendSpeedToolSetTo(@NotNull Player player) {

        Message lore, message;

        if (BSUtils.getSpeedTools().contains(player.getName())) {
            lore = new Message()
                    .addText(BSUtils.getSpeedTools().get(player.getName() + ".left").toString(), Messages.HIGHLIGHTED_TEXT)
                    .addText(" | ", Messages.MAIN_TEXT).addText(BSUtils.getSpeedTools().get(player.getName() + ".right").toString(), Messages.HIGHLIGHTED_TEXT);

            message = new Message().addPrefix().addText("Speedtool set to: ", MAIN_TEXT).addHoverText(Objects.requireNonNull(BSUtils.getSpeedTools().get(player.getName() + ".item")).toString(), lore, HIGHLIGHTED_TEXT);

        }else {

            lore = new Message()
                    .addText(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("Buildtools.globalSpeedTool.left")).toString(), Messages.HIGHLIGHTED_TEXT)
                    .addText(" | ", Messages.MAIN_TEXT).addText(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("Buildtools.globalSpeedTool.right")).toString(), Messages.HIGHLIGHTED_TEXT);

            message = new Message().addPrefix().addText("Speedtool set to: ", MAIN_TEXT).addHoverText(Objects.requireNonNull(BSUtils.getPlugin().getConfig().get("Buildtools.globalSpeedTool.item")).toString(), lore, HIGHLIGHTED_TEXT);

        }

        message.send(player);

    }

    public static void sendSetClickValues(@NotNull Player player, String @NotNull [] args) {

        Message message = new Message().addPrefix().addText("Speedtool values set to: ", MAIN_TEXT).addText(args[0], HIGHLIGHTED_TEXT, TextDecoration.BOLD).addText(" | ", MAIN_TEXT).addText(args[1], HIGHLIGHTED_TEXT, TextDecoration.BOLD);
        message.send(player);

    }
//TODO
    public static void sendWarpInfo(Player player) {

        Message message = new Message().newLine()
                .addText("       === ", GRAY).addText("Warp Command", WHITE, TextDecoration.BOLD).addText(" ===", GRAY).newLine().newLine()
                .addClickText("/warp", ClickEvent.suggestCommand("/warp <arg1> [arg2]"), MAIN_TEXT)
                .addText(" <").addHoverText("arg1", "<name> | <info> [args2]", WHITE).addText(">")
                .addText("  Teleports to the given warp", HIGHLIGHTED_TEXT).newLine()
                .addText("  info opens this help page", HIGHLIGHTED_TEXT).newLine()
                .addClickText("/setwarp", ClickEvent.suggestCommand("/setwarp <name>"), MAIN_TEXT).newLine()
                .addText("  Sets a warp", HIGHLIGHTED_TEXT).newLine()
                .addClickText("/delwarp", ClickEvent.suggestCommand("/delwarp <name>"), MAIN_TEXT).newLine()
                .addText(" deletes a given warp", HIGHLIGHTED_TEXT);

        message.send(player);

    }

    public static void sendWarpList(Player player, String pageArg) {

        TreeMap<Integer, List<Warp>> warpPages = WarpHandler.getWarpListPages();
        int page;

        try {
            page = Integer.parseInt(pageArg);
        }catch (Exception exception) {
            exception.printStackTrace();
            page = warpPages.size();
        }


        if(page > warpPages.size()) {
            page = warpPages.size();
        }

        List<Warp> warps = warpPages.get(page);

        Message message = new Message();

        message.addText("       === ", GRAY).addText("Warp List - Page " + page + "/" + warpPages.size(), WHITE, TextDecoration.BOLD).addText(" ===", GRAY).newLine().newLine();

        for(Warp name : warps) {
            message.addClickText(name.name(), ClickEvent.suggestCommand("/warp " + name), HIGHLIGHTED_TEXT)
                    .addClickHoverText(" [x]", "delete", RED, ClickEvent.runCommand("/delwarp " + name)).newLine();
        }
        if(page != 1 && page != warpPages.size()) {
            message.addClickText("Previous Page", ClickEvent.runCommand("/warp list " + (page - 1)), GRAY)
                    .addText("|", GRAY)
                    .addClickText("Next Page", ClickEvent.runCommand("/warp list " + (page + 1)), GRAY).newLine();
        }else if(page < warpPages.size()) {
            message.addClickText("Next Page", ClickEvent.runCommand("/warp list " + (page + 1)), GRAY).newLine();
        }else if(page == warpPages.size()) {
            message.addClickText("Previous Page", ClickEvent.runCommand("/warp list " + (page - 1)), GRAY).newLine();
        }

        message.send(player);

    }

    public static void sendNoWarps(Player player) {

        Message message = new Message();

        message.addText("There currently are no Warps!", RED);

        message.send(player);


    }


    public static void sendWarpNotExists(Player player, Warp warp) {

        Message message = new Message();

        message.addText(warp.name(), RED)
                .addClickText(" does not exist!", ClickEvent.suggestCommand("/setwarp " + warp.name()), HIGHLIGHTED_TEXT);

        message.send(player);
    }

    public static void sendWarpCreated(Player player, Warp warp) {

        Message message = new Message();

        message.addText("Successfully created: ", HIGHLIGHTED_TEXT)
                .addClickText((warp.name() +  "!"), ClickEvent.runCommand("/warp " + warp.name()), GREEN);

        message.send(player);

    }

    public static void sendWarpRemoved(Player player, Warp warp) {

        Message message = new Message();

        message.addText("Successfully removed: ", HIGHLIGHTED_TEXT)
                .addText((warp.name() +  "!"), RED);

        message.send(player);

    }

    public static void sendToDestination(Player player, Warp warp) {

        Message message = new Message();


        message.addText("Successfully teleported to: ", HIGHLIGHTED_TEXT)
                    .addText(warp.name() + "!", GREEN);

        message.send(player);

    }

    public static void sendToDestination(Player player, Location location) {

        Message message = new Message();

        message.addText("Successfully teleported to: ", HIGHLIGHTED_TEXT)
                .addText("x: ", WHITE).addText(location.getBlockX() + " ", GRAY)
                .addText("y: ", WHITE).addText(location.getBlockY() + " ", GRAY)
                .addText("z: ", WHITE).addText(location.getBlockZ() + "", GRAY);

        message.send(player);
    }

    public static void sendWarpAlreadyCreated(Player player) {

        Message message = new Message();

        message.addText("This warp already exists!");


        message.send(player);
    }
}

