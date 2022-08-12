package de.savefeelix.minecraftapi.defaults.registry;

import de.savefeelix.minecraftapi.exceptions.RegisterCommandException;
import de.savefeelix.minecraftapi.interfaces.IPluginRegistry;
import de.savefeelix.minecraftapi.records.CommandInformation;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public class DefaultPluginRegistry implements IPluginRegistry {
    private final JavaPlugin plugin;

    public DefaultPluginRegistry(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerCommand(CommandInformation command) throws RegisterCommandException {
        try {
            if (plugin.getCommand(command.name()) != null) {
                Objects.requireNonNull(plugin.getCommand(command.name())).setExecutor(command.command());

                if (command.completer() != null)
                    Objects.requireNonNull(plugin.getCommand(command.name())).setTabCompleter(command.completer());

                if (command.aliases().length > 0)
                    Objects.requireNonNull(plugin.getCommand(command.name())).setAliases(Arrays.asList(command.aliases()));
            }
        } catch (Exception e) {
            throw new RegisterCommandException(plugin, command);
        }
    }

    @Override
    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }
}
