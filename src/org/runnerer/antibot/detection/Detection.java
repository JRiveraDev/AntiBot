package org.runnerer.antibot.detection;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.runnerer.antibot.preventions.chat.ChatA;
import org.runnerer.antibot.preventions.chat.ChatB;
import org.runnerer.antibot.preventions.chat.ChatC;
import org.runnerer.antibot.preventions.math.MathA;
import org.runnerer.antibot.preventions.math.MathB;
import org.runnerer.antibot.preventions.math.MathC;
import org.runnerer.antibot.preventions.sneak.SneakA;

import java.util.Random;

public abstract class Detection implements Listener
{
    private String detectionName;

    public Detection(String name)
    {
        detectionName = name;
    }

    public String getName()
    {
        return detectionName;
    }

    protected void runPrevention(Player player)
    {
        Random rand = new Random();
        int random = rand.nextInt(8);

        if (random == 1)
        {
            ChatA.startPrevention(player);
        } else if (random == 2)
        {
            ChatB.startPrevention(player);
        } else if (random == 3)
        {
            ChatC.startPrevention(player);
        } else if (random == 4)
        {
            MathA.startPrevention(player);
        } else if (random == 5)
        {
            MathB.startPrevention(player);
        } else if (random == 6)
        {
            MathC.startPrevention(player);
        } else if (random == 7)
        {
            SneakA.startPrevention(player);
        }
    }
}
