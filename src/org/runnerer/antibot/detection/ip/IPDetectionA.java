package org.runnerer.antibot.detection.ip;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.runnerer.antibot.data.IPDatabase;
import org.runnerer.antibot.detection.Detection;

public class IPDetectionA extends Detection
{

    public IPDetectionA()
    {
        super("IP Detection A");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String ipAddress = player.getAddress().getAddress().getHostAddress();

        if (IPDatabase.getIpAddresses().contains(ipAddress))
        {
            runPrevention(player);
        } else
        {
            // Add IP to data.
            IPDatabase.addIp(player, ipAddress);
        }
    }
}
