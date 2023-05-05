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

    public boolean onCommand(@NotNull  CommandSender sender, @NotNull  Command command, @NotNull  String label, @NotNull String[] args) {

        if(sender instanceof Player) {
            this.player = (Player) sender;
            this.args = args;

            handleCommandBounds();

            if(label.equalsIgnoreCase("warp")) {

                handleWarp();

            }else if(label.equalsIgnoreCase("setwarp")) {

                WarpHandler.createWarp(this.args[0], this.player);

            }else if(label.equalsIgnoreCase("delwarp")) {

                WarpHandler.removeWarp(this.args[0], this.player);

            }


        }

        return false;
    }

    public void handleCommandBounds() {

        if (this.args.length == 0) {

            Messages.sendWarpInfo(this.player);

        } else if (this.args[0].equalsIgnoreCase("list")) {

            if (!(this.args.length == 1 || this.args.length == 2)) {
                Messages.sendWarpInfo(this.player);
            }

        } else if (this.args.length != 1) {

            Messages.sendWarpInfo(this.player);

        }
    }

    public void handleWarp() {

        if(this.args.length == 0) {

            Messages.sendWarpInfo(this.player);

        }else if(this.args[0].equalsIgnoreCase("info")) {

            Messages.sendWarpInfo(this.player);

        }else if(this.args[0].equalsIgnoreCase("list")) {

            if(BSUtils.getWarps().isEmpty()) {

                Messages.sendNoWarps(this.player);
                return;
            }

            if(this.args.length == 1) {

                Messages.sendWarpList(this.player, "1");

            }else if(this.args.length == 2) {

                Messages.sendWarpList(this.player, this.args[1]);

            }

        }else {

            WarpHandler.sendToWarp(this.player, new Warp(this.args[0]).fromConfig());
            BSUtils.getPlugin().getLogger().info("WarpCommand; 96; " + this.args[0]);

        }

    }

}
