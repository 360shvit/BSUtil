package net.shvit.bsutils.command;

import net.shvit.bsutils.BSUtils;
import net.shvit.bsutils.chat.Messages;
import net.shvit.bsutils.movement.warp.Warp;
import net.shvit.bsutils.movement.warp.WarpHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    private Player player;
    private String[] args;

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof Player)) {
            return false;
        }

        this.player = (Player) sender;
        this.args = args;

        handleCommandBounds();

        if (label.equalsIgnoreCase("warp")) {
            handleWarp();
        } else if (label.equalsIgnoreCase("setwarp")) {
            WarpHandler.createWarp(args[0], player);
        } else if (label.equalsIgnoreCase("delwarp")) {
            WarpHandler.removeWarp(args[0], player);
        }

        return false;
    }

    private void handleCommandBounds() {
        if (args.length == 0 || args[0].equalsIgnoreCase("info")) {
            Messages.sendWarpInfo(player);
        } else if (args[0].equalsIgnoreCase("list") && !(args.length == 1 || args.length == 2)) {
            Messages.sendWarpInfo(player);
        } else {
            Messages.sendWarpInfo(player);
        }
    }

    private void handleWarp() {

        if (args[0].equalsIgnoreCase("list")) {
            if (BSUtils.getWarps().isEmpty()) {
                Messages.sendNoWarps(player);
            } else {
                Messages.sendWarpList(player, args.length == 1 ? "1" : args[1]);
            }
        } else {
            WarpHandler.sendToWarp(player, new Warp(args[0]));
        }
    }

}
