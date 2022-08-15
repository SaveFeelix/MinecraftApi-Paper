package de.savefeelix.minecraftapi;

import de.savefeelix.minecraftapi.defaults.registry.DefaultPluginRegistry;
import de.savefeelix.minecraftapi.exceptions.RegisterCommandException;
import de.savefeelix.minecraftapi.interfaces.IPluginRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

/**
 * Base Class to create a Plugin with this API
 */
public abstract class MinecraftPlugin extends JavaPlugin {

    /**
     * Instance of the PluginRegistry
     *
     * @see IPluginRegistry
     */
    private IPluginRegistry pluginRegistry;

    /**
     * Instance of the Console
     */
    protected static final ConsoleCommandSender console = Bukkit.getConsoleSender();

    /**
     * Default Constructor
     */
    public MinecraftPlugin() {
    }

    /**
     * API Constructor
     *
     * @param pluginRegistry instance of the Plugin Registry
     * @see IPluginRegistry
     */
    protected MinecraftPlugin(@Nullable IPluginRegistry pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }


    @Override
    public void onLoad() {
        pluginRegistry = pluginRegistry != null ? pluginRegistry : new DefaultPluginRegistry(this);
        onApiLoad();
    }

    @Override
    public void onEnable() {
        try {
            registerCommands(pluginRegistry);
        } catch (RegisterCommandException e) {
            this.getLogger().log(Level.WARNING, "Cannot load Commands!");
        }
        registerListeners(pluginRegistry);
        onApiEnable();
    }

    @Override
    public void onDisable() {
        onApiDisable();
    }

    /**
     * API Method called in {@link JavaPlugin#onLoad()}
     */
    public abstract void onApiLoad();

    /**
     * API Method called in {@link JavaPlugin#onEnable()}
     */
    public abstract void onApiEnable();

    /**
     * API Method called in {@link JavaPlugin#onDisable()}
     */
    public abstract void onApiDisable();

    /**
     * Method to register Commands
     *
     * @param registry the PluginRegistry
     * @throws RegisterCommandException throw if the Command isn't registered in the plugin.yml
     * @see IPluginRegistry
     */
    public abstract void registerCommands(IPluginRegistry registry) throws RegisterCommandException;

    /**
     * Method to register Listeners
     *
     * @param pluginRegistry the PluginRegistry
     * @see IPluginRegistry
     */
    public abstract void registerListeners(IPluginRegistry pluginRegistry);

    /**
     * Getter for the Console
     *
     * @return the Console
     * @see ConsoleCommandSender
     */
    public static ConsoleCommandSender getConsole() {
        return console;
    }
}
