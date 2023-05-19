package net.shvit.bsutil.util;

import org.jetbrains.annotations.NotNull;

public class Utils {

    public static @NotNull String argsToString(String @NotNull [] args, int start) {

        StringBuilder builder = new StringBuilder();

        for (int i = start; i < args.length; i++) {

            builder.append(args[i]).append(" ");

        }

        return builder.toString();

    }

    public static <T> Double[] argsToDouble(T args, int start, int stop) {

        Double[] doubleArgs = new Double[0];

        if (args instanceof String[] newArgs) {

            doubleArgs = new Double[newArgs.length - start];
            int index = 0;

            for (int i = start; i < newArgs.length; i++) {

                try {

                    doubleArgs[index] = Double.parseDouble(newArgs[i]);
                    index++;

                } catch (Exception exception) {

                    exception.printStackTrace();

                }

                if (i == stop) {
                    break;
                }
            }
        }

        return doubleArgs;
    }

}
