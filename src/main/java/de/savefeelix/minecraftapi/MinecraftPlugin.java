package de.savefeelix.minecraftapi;

import de.savefeelix.minecraftapi.defaults.registry.DefaultPluginRegistry;
import de.savefeelix.minecraftapi.exceptions.RegisterCommandException;
import de.savefeelix.minecraftapi.interfaces.IPluginRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

public abstract class MinecraftPlugin extends JavaPlugin {
    private IPluginRegistry pluginRegistry;
    protected static final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public MinecraftPlugin() {
    }

    protected MinecraftPlugin(@Nullable IPluginRegistry pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }


    @Override
    public void onLoad() {
        pluginRegistry = pluginRegistry != null ? pluginRegistry : new DefaultPluginRegistry(this);
    }

    @Override
    public void onEnable() {
        try {
            registerCommands(pluginRegistry);
        } catch (RegisterCommandException e) {
            this.getLogger().log(Level.WARNING, "Cannot load all Commands!");
        }
        registerListeners(pluginRegistry);
    }

    @Override
    public void onDisable() {
    }

    public abstract void registerCommands(IPluginRegistry registry) throws RegisterCommandException;

    public abstract void registerListeners(IPluginRegistry pluginRegistry);

    public static ConsoleCommandSender getConsole() {
        return console;
    }
}
