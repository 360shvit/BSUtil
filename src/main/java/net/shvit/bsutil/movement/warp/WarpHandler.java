package net.shvit.bsutil.movement.warp;

import net.shvit.bsutil.BSUtil;
import net.shvit.bsutil.chat.Messages;
import net.shvit.bsutil.movement.teleport.TeleportHandler;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class WarpHandler {

    public static void createWarp(String name, Player player) {

        Warp warp = new Warp(name).location(player.getLocation());
        warp.toConfig();
        Messages.sendWarpCreated(player, warp);

    }

    public static void removeWarp(String name, Player player) {

        Warp warp = new Warp(name);
        warp.remove();
        Messages.sendWarpRemoved(player, warp);


    }

    public static void sendToWarp(Player player, Warp warp) {

        if (!BSUtil.getWarps().contains(warp.name())) {
            Messages.sendWarpNotExists(player, warp);
        } else {
            TeleportHandler.teleport(player, warp);
        }
    }

    public static List<Warp> getWarps() {
        List<Warp> warps = new ArrayList<>();
        for (String name : BSUtil.getWarps().getKeys()) {
            try {
                Warp warp = new Warp(name);
                warps.add(warp);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return warps;
    }

    public static TreeMap<Integer, List<Warp>> getWarpListPages() {
        List<Warp> warps = getWarps();
        int pageSize;

        try {
            pageSize = BSUtil.getPlugin().getConfig().getInt("BSUtil.defaults.settings.warp.list-length");
        } catch (Exception exception) {
            pageSize = 10;
            exception.printStackTrace();
        }

        TreeMap<Integer, List<Warp>> collection = new TreeMap<>();
        int fromIndex = 0;
        int pageIndex = 1;

        /*
        System.out.println("Warps list size: " + warps.size());
        System.out.println("Page size: " + pageSize);
        */

        while (fromIndex < warps.size()) {
            int toIndex = Math.min(fromIndex + pageSize, warps.size());
            List<Warp> subList = warps.subList(fromIndex, toIndex);
            collection.put(pageIndex, subList);
            //System.out.println("Page " + pageIndex + ": " + subList.size() + " warps");
            fromIndex += pageSize;
            pageIndex++;
        }
        return collection;
    }

}
