package net.shvit.bsutil.command;

import net.shvit.bsutil.chat.Messages;
import net.shvit.bsutil.movement.speed.SpeedHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCommand implements CommandExecutor {

    private Player player;
    private String[] args;

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

        if (this.args.length != 1) {
            handleCommandBounds();
            return false;
        }

        if (label.equalsIgnoreCase("speed")) {
            SpeedHandler.setSpeed(this.player, args[0]);
        } else if (label.equalsIgnoreCase("flyspeed") || label.equalsIgnoreCase("fs")) {
            SpeedHandler.setFlySpeed(this.player, args[0]);
        } else if (label.equalsIgnoreCase("walkspeed") || label.equalsIgnoreCase("ws")) {
            SpeedHandler.setWalkSpeed(this.player, args[0]);
        }

        return true;
    }

    private void handleCommandBounds() {
        if (this.args.length != 0 && (this.args.length >= 2 || this.args[0].equalsIgnoreCase("info"))) {
            Messages.sendSpeedInfo(player);
        } else {
            Messages.sendCurrentSpeed(this.player);
        }
    }


}
