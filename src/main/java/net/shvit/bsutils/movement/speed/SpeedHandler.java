package net.shvit.bsutils.movement.speed;

import net.shvit.bsutils.BSUtils;
import net.shvit.bsutils.chat.Messages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedHandler {

    public static <T> float speedMath(Player player, T input) {

        float speed = getSpeed(player);

        if (input instanceof Float) {

            speed = (float) input / 10;

        } else if (input instanceof String) {
            try {

                speed = Float.parseFloat((String) input) / 10;

            } catch (Exception exception) {

                exception.printStackTrace();

            }
        } else {
            BSUtils.getPlugin().getLogger().info("Not a valid input type only expects String or Float!");
        }

        checkSpeed(player, speed);

        return speed;
    }

    public static void checkSpeed(Player player, float speed) {
        if (speed < 0 | speed > 1) {
            Messages.sendInputRange(player);
        }
    }

    public static float getSpeed(@NotNull Player player) {

        if (player.isFlying()) {
            return player.getFlySpeed();

        } else {
            return player.getWalkSpeed();

        }
    }

    public static <T> void setSpeed(Player player, T input) {

        if (getSpeed(player) == speedMath(player, input)) {

            Messages.sendCurrentSpeed(player);

        } else {

            if (player.isFlying()) {

                player.setFlySpeed(speedMath(player, input));
                Messages.sendCurrentSpeed(player);

            } else {

                player.setWalkSpeed(speedMath(player, input));
                Messages.sendCurrentSpeed(player);

            }
        }
    }

    public static <T> void setFlySpeed(Player player, T input) {

        if (getSpeed(player) == speedMath(player, input)) {

            Messages.sendCurrentSpeed(player);

        } else {

            player.setFlySpeed(speedMath(player, input));
            Messages.sendCurrentSpeed(player);

        }

    }

    public static <T> void setWalkSpeed(Player player, T input) {

        if (getSpeed(player) == speedMath(player, input)) {

            Messages.sendCurrentSpeed(player);

        } else {

            player.setWalkSpeed(speedMath(player, input));
            Messages.sendCurrentSpeed(player);

        }
    }

}
