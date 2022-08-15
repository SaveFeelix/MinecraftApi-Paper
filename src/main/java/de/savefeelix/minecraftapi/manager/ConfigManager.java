package de.savefeelix.minecraftapi.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Class to access the 'config.yml' of the Plugin
 */
public final class ConfigManager {

    /**
     * Disable instantiation for this Class
     *
     * @throws IllegalAccessException if someone want to create an instance of this Manager
     */
    public ConfigManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Method to check if the Config contains the given Path
     *
     * @param plugin the Plugin
     * @param path   the Path
     * @return true if the Path exists
     */
    public static @NotNull Boolean contains(@NotNull JavaPlugin plugin, @NotNull String path) {
        return plugin.getConfig().contains(path);
    }

    /**
     * Method to set a value to the given Path
     *
     * @param plugin the Plugin
     * @param path   the Path
     * @param object the Value
     */
    public static void set(@NotNull JavaPlugin plugin, @NotNull String path, @NotNull Object object) {
        plugin.getConfig().set(path, object);
    }

    /**
     * Method to get a Value from the given Path
     *
     * @param plugin the Plugin
     * @param path   the Path
     * @param <T>    the Type
     * @return Null if the path was not found or the object cannot be cast to the given type
     */
    public static <T> @Nullable T getFromConfig(@NotNull JavaPlugin plugin, @NotNull String path) {
        Object value = plugin.getConfig().get(path);
        if (value != null) {
            try {
                return (T) value;
            } catch (ClassCastException e) {
                return null;
            }
        }
        return null;
    }
}
