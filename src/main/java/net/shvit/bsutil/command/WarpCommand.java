package net.shvit.bsutil.command;

import net.shvit.bsutil.BSUtil;
import net.shvit.bsutil.chat.Messages;
import net.shvit.bsutil.movement.warp.Warp;
import net.shvit.bsutil.movement.warp.WarpHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    private Player player;
    private String[] args;

    private String label;


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
        this.label = label;

        if(!commandBounds()) {
            return false;
        }

        if (label.equalsIgnoreCase("warp")) {
            handleWarp();
        } else if (label.equalsIgnoreCase("setwarp")) {
            WarpHandler.createWarp(args[0], player);
        } else if (label.equalsIgnoreCase("delwarp")) {
            WarpHandler.removeWarp(args[0], player);
        }

        return false;
    }

    private boolean commandBounds() {

        boolean correctBounds = true;

        if (args.length == 0
                ||  args[0].equalsIgnoreCase("info")
                || (args[0].equalsIgnoreCase("list") && args.length > 2)
                || (label.equalsIgnoreCase("delwarp") || label.equalsIgnoreCase("setwarp")) && args.length > 1) {

            Messages.sendWarpInfo(player);
            correctBounds = false;
        }

        return correctBounds;
    }

    private void handleWarp() {

        if (args[0].equalsIgnoreCase("list")) {
            if (BSUtil.getWarps().isEmpty()) {
                Messages.sendNoWarps(player);
            } else {
                Messages.sendWarpList(player, args.length == 1 ? "1" : args[1]);
            }
        } else {
            WarpHandler.sendToWarp(player, new Warp(args[0]));
        }
    }

}
