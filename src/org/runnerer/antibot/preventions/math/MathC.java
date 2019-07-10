package org.runnerer.antibot.preventions.math;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.runnerer.antibot.common.utils.C;
import org.runnerer.antibot.preventions.Prevention;

import java.util.ArrayList;

public class MathC extends Prevention
{
    private static ArrayList<Player> playerList = new ArrayList<>();

    public MathC()
    {
        super("Math C");
    }

    private static String getWantedEquation()
    {
        return "9 + 1";
    }

    private static String getWantedAnswer()
    {
        return "10";
    }

    public static void startPrevention(Player player)
    {
        playerList.add(player);
        player.sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] Slow down there pal! Please input the following string into chat before continuing.");
        player.sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] " + getWantedEquation());
    }

    @EventHandler
    public void listenString(AsyncPlayerChatEvent event)
    {
        if (!playerList.contains(event.getPlayer())) return;

        if (!event.getMessage().equals(getWantedAnswer()))
        {
            event.getPlayer().kickPlayer("Failed verification! :(");
        }

        playerList.remove(event.getPlayer());
        event.getPlayer().sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] You are now verified.");
    }

    @EventHandler
    public void listenMove(PlayerMoveEvent event)
    {
        if (!playerList.contains(event.getPlayer())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void listenDamage(EntityDamageByEntityEvent event)
    {
        if (!(event.getEntity()instanceof Player)) return;

        if (!(event.getDamager() instanceof Player)) return;

        if (!playerList.contains((Player)event.getEntity()) || !playerList.contains((Player)event.getDamager())) return;

        event.setCancelled(true);
    }
}
