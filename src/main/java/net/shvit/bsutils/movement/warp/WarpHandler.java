package net.shvit.bsutils.movement.warp;

import net.shvit.bsutils.BSUtils;
import net.shvit.bsutils.chat.Messages;
import net.shvit.bsutils.movement.teleport.TeleportHandler;
import net.shvit.bsutils.util.PageCollection;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class WarpHandler {

    public static void createWarp(String name, Player player) {

        Warp warp = new Warp(name).location(player.getLocation());
        warp.toConfig();
        Messages.sendWarpCreated(player, warp);

    }

    public static void removeWarp(String name, Player player) {

        Warp warp = new Warp(name).fromConfig();
        warp.remove();
        Messages.sendWarpRemoved(player, warp);


    }
    public static void sendToWarp(Player player, Warp warp) {

        if(!BSUtils.getWarps().contains(warp.name())) {
            Messages.sendWarpNotExists(player, warp);
            BSUtils.getPlugin().getLogger().info("WarpHandler; 36; " + warp.name());
        }else {
            TeleportHandler.teleport(player, warp);
        }
    }

    public static ArrayList<Warp> getWarps() {

        ArrayList<Warp> warps = new ArrayList<>();

        for(String name : BSUtils.getWarps().getKeys()) {

            try{

                Warp warp = new Warp(name).fromConfig();
                warps.add(warp);

            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return warps;

    }

    /*public static SortedMap<Integer, Set<String>> getWarpListPages() {

        SortedMap<Integer, Set<String>> warpPages = new TreeMap<>();
        Set<String> warps = new HashSet<>();
        int page = 1;
        int count = 0;

        for(Warp warp : Objects.requireNonNull(getWarps())) {

            warps.add(warp.name());
            count++;

            if (count == 10)  {
                warpPages.put(page, warps);
                count = 0;
                page++;
                warps = new HashSet<>();
            } else if(count == getWarps().size() % 10 && count != 0 && page > getWarps().size() / 10) {
                warpPages.put(page, warps);
                break;
            }
        }
        return warpPages;
    }*/

    public static PageCollection<Integer, Warp> getWarpListPages() {

        PageCollection<Integer, Warp> collection = new PageCollection<>();
        int count = 0;
        int page = 1;

        for(Warp warp : getWarps()) {

            collection.add(page, warp);
            count++;

            if(count == 10) {
                count = 0;
                page++;
            }

        }

        return collection;
    }
}
