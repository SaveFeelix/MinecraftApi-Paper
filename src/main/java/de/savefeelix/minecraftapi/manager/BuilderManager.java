package de.savefeelix.minecraftapi.manager;

import de.savefeelix.minecraftapi.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class BuilderManager {
    public BuilderManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static ItemBuilder createItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material) {
        return new ItemBuilder(plugin, material);
    }

    public static ItemBuilder createItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material, @NotNull Integer amount) {
        return new ItemBuilder(plugin, material, amount);
    }

    public static ItemBuilder createItemBuilder(@NotNull JavaPlugin plugin, @NotNull ItemStack itemStack) {
        return new ItemBuilder(plugin, itemStack);
    }
}
