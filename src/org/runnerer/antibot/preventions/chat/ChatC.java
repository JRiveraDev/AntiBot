package org.runnerer.antibot.preventions.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.runnerer.antibot.common.utils.C;
import org.runnerer.antibot.preventions.Prevention;

import java.util.ArrayList;

public class ChatC extends Prevention
{
    private static ArrayList<Player> playerList = new ArrayList<>();

    public ChatC()
    {
        super("Chat C");
    }

    private static String getWantedString()
    {
        return "cmon barbie violet";
    }

    public static void startPrevention(Player player)
    {
        playerList.add(player);
        player.sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] Slow down there pal! Please input the following string into chat before continuing.");
        player.sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] " + getWantedString());
    }

    @EventHandler
    public void listenString(AsyncPlayerChatEvent event)
    {
        if (!playerList.contains(event.getPlayer())) return;

        if (!event.getMessage().equals(getWantedString()))
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
