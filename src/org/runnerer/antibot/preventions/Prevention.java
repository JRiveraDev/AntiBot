package org.runnerer.antibot.preventions;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class Prevention implements Listener
{
    private String preventionName;

    public Prevention(String name)
    {
        preventionName = name;
    }

    public String getName()
    {
        return preventionName;
    }
}
