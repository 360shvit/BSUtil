package net.shvit.bsutils.command;

import net.shvit.bsutils.chat.Messages;
import net.shvit.bsutils.movement.speed.speedtool.SpeedToolHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedToolCommand implements CommandExecutor {

    private Player player;
    private String[] args;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            this.player = (Player) sender;
            this.args = args;

            if (label.equalsIgnoreCase("setspeedtool")) {

                handleSetSpeedTool();

            } else if (label.equalsIgnoreCase("speedtool")) {

                handleSpeedTool();

            }
        }

        return false;
    }

    private void handleSetSpeedTool() {

        SpeedToolHandler.setTool(this.player);
        SpeedToolHandler.giveTool(this.player);
        Messages.sendSpeedToolSetTo(this.player);

    }

    private void handleSpeedTool() {

        if(this.args.length >= 3 || this.args.length == 1) {

            Messages.sendSpeedToolInfo(this.player);

        }else if (this.args.length == 0) {

            SpeedToolHandler.giveTool(this.player);

        } else {

            handleSetClickValues();

        }
    }

    private void handleSetClickValues() {

        if (this.args[1].equalsIgnoreCase("-l")) {

            SpeedToolHandler.setLeftClickValue(this.player, this.args[0]);

        } else if (this.args[1].equalsIgnoreCase("-r")) {

            SpeedToolHandler.setRightClickValue(this.player, this.args[0]);

        } else {

            SpeedToolHandler.setClickValues(this.player, this.args[0], this.args[1]);

        }
        Messages.sendSetClickValues(this.player, this.args);
    }
}
