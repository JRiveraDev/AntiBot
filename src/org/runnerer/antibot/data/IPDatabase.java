package org.runnerer.antibot.data;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class IPDatabase
{

    private static HashMap<Player, String> ipDatabase = new HashMap<>();
    private static HashMap<Player, Integer> suspicions = new HashMap<>();

    public static HashMap<Player, String> getIpDatabase()
    {
        return ipDatabase;
    }

    public static void addIp(Player player, String ipAddress)
    {
        ipDatabase.put(player, ipAddress);
    }

    public static void removeIp(Player player)
    {
        ipDatabase.remove(player);
    }

    public static String getIpFromPlayer(Player player)
    {
        if (!ipDatabase.containsKey(player) || ipDatabase == null || ipDatabase.size() == 0) return null;

        return ipDatabase.get(player);
    }

    public static Collection<String> getIpAddresses()
    {
         if (ipDatabase.size() == 0 || ipDatabase == null) return null;

         return ipDatabase.values();
    }

    public static void addSuspicion(Player player, Integer suspicionCount)
    {
        if (suspicions.containsKey(player))
        {
            suspicions.replace(player, suspicionCount);
            return;
        }

        suspicions.put(player, 1);
    }

    public static Integer getSuspicions(Player player)
    {
        if (!suspicions.containsKey(player) || suspicions == null || suspicions.size() == 0) return null;

        return suspicions.get(player);
    }

    public static void removeSuspicions(Player player)
    {
        suspicions.remove(player);
    }

    @EventHandler
    public void removeFromData(PlayerJoinEvent event)
    {
        removeIp(event.getPlayer());
        removeSuspicions(event.getPlayer());
    }
}
