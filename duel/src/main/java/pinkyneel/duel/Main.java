package pinkyneel.duel;

import org.bukkit.plugin.java.JavaPlugin;

import pinkyneel.duel.commands.commands;
import pinkyneel.duel.misc.duel;
import pinkyneel.duel.misc.events;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new commands(this);
        new events(this);
        new duel(this);
    }
}
