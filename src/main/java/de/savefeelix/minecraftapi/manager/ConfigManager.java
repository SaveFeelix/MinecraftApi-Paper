package de.savefeelix.minecraftapi.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigManager {
    public ConfigManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

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
