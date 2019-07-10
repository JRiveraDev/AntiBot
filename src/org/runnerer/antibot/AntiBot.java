package org.runnerer.antibot;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.runnerer.antibot.detection.Detection;
import org.runnerer.antibot.detection.ip.IPDetectionA;
import org.runnerer.antibot.preventions.Prevention;
import org.runnerer.antibot.preventions.chat.ChatA;
import org.runnerer.antibot.preventions.chat.ChatB;
import org.runnerer.antibot.preventions.chat.ChatC;
import org.runnerer.antibot.preventions.math.MathA;
import org.runnerer.antibot.preventions.math.MathB;
import org.runnerer.antibot.preventions.math.MathC;
import org.runnerer.antibot.preventions.sneak.SneakA;

import java.util.ArrayList;

public class AntiBot extends JavaPlugin
{
    private ArrayList<Detection> detections = new ArrayList<>();
    private ArrayList<Prevention> preventions = new ArrayList<>();

    @Override
    public void onEnable()
    {
        detections.add(new IPDetectionA());
        preventions.add(new ChatA());
        preventions.add(new ChatB());
        preventions.add(new ChatC());

        preventions.add(new SneakA());

        preventions.add(new MathA());
        preventions.add(new MathB());
        preventions.add(new MathC());

        for (Detection detection : this.detections)
        {
            registerListener(detection);
        }

        for (Prevention prevention : this.preventions)
        {
            registerListener(prevention);
        }
    }

    public void registerListener(Listener listener)
    {
        this.getServer().getPluginManager().registerEvents(listener, (Plugin) this);
    }
}
