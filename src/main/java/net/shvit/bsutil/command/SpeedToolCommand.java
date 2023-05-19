package net.shvit.bsutil.command;

import net.shvit.bsutil.chat.Messages;
import net.shvit.bsutil.movement.speed.speedtool.SpeedToolHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedToolCommand implements CommandExecutor {

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

        if (label.equalsIgnoreCase("setspeedtool")) {
            handleSetSpeedTool();
        } else if (label.equalsIgnoreCase("speedtool")) {
            handleSpeedTool();
        }

        return false;
    }

    private void handleSetSpeedTool() {
        SpeedToolHandler.setTool(player);
        SpeedToolHandler.giveTool(player);
        Messages.sendSpeedToolSetTo(player);
    }

    private void handleSpeedTool() {
        if (args.length >= 3 || args.length == 1) {
            Messages.sendSpeedToolInfo(player);
        } else if (args.length == 0) {
            SpeedToolHandler.giveTool(player);
        } else {
            handleSetClickValues();
        }
    }

    private void handleSetClickValues() {
        if (args[1].equalsIgnoreCase("-l")) {
            SpeedToolHandler.setLeftClickValue(player, args[0]);
        } else if (args[1].equalsIgnoreCase("-r")) {
            SpeedToolHandler.setRightClickValue(player, args[0]);
        } else {
            SpeedToolHandler.setClickValues(player, args[0], args[1]);
        }
        Messages.sendSetClickValues(player, args);
    }

}
