package org.runnerer.antibot.preventions.sneak;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.runnerer.antibot.common.utils.C;
import org.runnerer.antibot.preventions.Prevention;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SneakA extends Prevention
{
    private static ArrayList<Player> playerList = new ArrayList<>();
    private static HashMap<String, Integer> sneakCountList = new HashMap<>();

    public SneakA()
    {
        super("Sneak A");
    }

    public static void startPrevention(Player player)
    {
        playerList.add(player);
        sneakCountList.put(player.getName(), 0);

        player.sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] Slow down there pal! Please input the following string into chat before continuing.");
    }

    @EventHandler
    public void listenString(AsyncPlayerChatEvent event)
    {
        if (!playerList.contains(event.getPlayer())) return;

        event.getPlayer().sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] You are not verified.");
    }

    @EventHandler
    public void listenSneak(PlayerToggleSneakEvent event)
    {
        if (!playerList.contains(event.getPlayer())) return;

        Random rand = new Random();
        int sneakCount = rand.nextInt(7);

        if (sneakCountList.get(event.getPlayer().getName()) == sneakCount)
        {
            sneakCountList.remove(event.getPlayer().getName());
            playerList.remove(event.getPlayer());
            event.getPlayer().sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] You are now verified.");
        } else
        {
            event.getPlayer().sendMessage(C.Gray + "[" + C.Blue + "AntiBot" + C.Gray + "] " + String.valueOf(sneakCountList.get(event.getPlayer().getName()) - sneakCount) + " more sneaks to go!");
        }
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
