package de.savefeelix.minecraftapi;

import de.savefeelix.minecraftapi.defaults.registry.DefaultPluginRegistry;
import de.savefeelix.minecraftapi.interfaces.IPluginRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MinecraftPlugin<TPlugin> extends JavaPlugin {
    private static JavaPlugin apiInstance;
    private final IPluginRegistry pluginRegistry;


    protected MinecraftPlugin() {
        this(new DefaultPluginRegistry(apiInstance));
    }

    protected MinecraftPlugin(IPluginRegistry pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    public static JavaPlugin getApiInstance() {
        return apiInstance;
    }

    @Override
    public void onLoad() {
        apiInstance = this;
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
}
