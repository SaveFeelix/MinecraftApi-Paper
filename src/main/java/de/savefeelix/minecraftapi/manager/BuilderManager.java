package de.savefeelix.minecraftapi.manager;

import de.savefeelix.minecraftapi.builder.ChatTimerBuilder;
import de.savefeelix.minecraftapi.builder.ItemBuilder;
import de.savefeelix.minecraftapi.defaults.timer.ChatTimer;
import de.savefeelix.minecraftapi.interfaces.ITimerAction;
import de.savefeelix.minecraftapi.interfaces.ITimerEndAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Manager to create Builder for the specific Type
 */
public final class BuilderManager {
    /**
     * Disable instantiation for this Class
     *
     * @throws IllegalAccessException if someone want to create an instance of this Manager
     */
    public BuilderManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Create an instance of a ItemBuilder
     *
     * @param plugin   the Plugin
     * @param material the Material
     * @return an instance of the ItemBuilder
     * @see ItemBuilder
     */
    public static @NotNull ItemBuilder createItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material) {
        return new ItemBuilder(plugin, material);
    }

    /**
     * Create an instance of a ItemBuilder
     *
     * @param plugin   the Plugin
     * @param material the Material
     * @param amount   the amount
     * @return an instance of the ItemBuilder
     * @see ItemBuilder
     */
    public static @NotNull ItemBuilder createItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material, @NotNull Integer amount) {
        return new ItemBuilder(plugin, material, amount);
    }

    /**
     * Create an instance of a ItemBuilder
     *
     * @param plugin    the Plugin
     * @param itemStack an existing ItemStack to modify
     * @return an instance of the ItemBuilder
     * @see ItemBuilder
     */
    public static @NotNull ItemBuilder createItemBuilder(@NotNull JavaPlugin plugin, @NotNull ItemStack itemStack) {
        return new ItemBuilder(plugin, itemStack);
    }

    public static @NotNull ChatTimerBuilder createChatTimerBuilder(@NotNull JavaPlugin plugin, @NotNull ITimerAction<ChatTimer> onInterval, @NotNull ITimerEndAction<ChatTimer> onEnd) {
        return new ChatTimerBuilder(plugin, onInterval, onEnd);
    }
}
