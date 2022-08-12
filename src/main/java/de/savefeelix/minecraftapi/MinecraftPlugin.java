package de.savefeelix.minecraftapi;

import de.savefeelix.minecraftapi.defaults.registry.DefaultPluginRegistry;
import de.savefeelix.minecraftapi.interfaces.IPluginRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public abstract class MinecraftPlugin extends JavaPlugin {
    private IPluginRegistry pluginRegistry;
    protected static final ConsoleCommandSender console = Bukkit.getConsoleSender();

    protected MinecraftPlugin(@Nullable IPluginRegistry pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }


    @Override
    public void onLoad() {
        pluginRegistry = pluginRegistry != null ? pluginRegistry : new DefaultPluginRegistry(this);
    }

    @Override
    public void onEnable() {
        registerCommands(pluginRegistry);
        registerListeners(pluginRegistry);
    }

    @Override
    public void onDisable() {
    }

    public abstract void registerCommands(IPluginRegistry registry);

    public abstract void registerListeners(IPluginRegistry pluginRegistry);

    public static ConsoleCommandSender getConsole() {
        return console;
    }
}
