package de.savefeelix.minecraftapi.builder;

import de.savefeelix.minecraftapi.exceptions.ItemBuilderException;
import de.savefeelix.minecraftapi.interfaces.IBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class to create/modify an Item
 *
 * @see IBuilder
 * @see ItemStack
 */
public final class ItemBuilder implements IBuilder<ItemStack> {

    /**
     * Instance of the Plugin
     *
     * @see JavaPlugin
     */
    private final JavaPlugin plugin;

    /**
     * An instance of the ItemStack
     *
     * @see ItemStack
     */
    private final ItemStack item;

    /**
     * An Instance of the ItemMeta from the ItemStack
     *
     * @see ItemStack
     * @see ItemMeta
     */
    private final ItemMeta meta;

    /**
     * Constructor to modify an existing Item
     *
     * @param plugin the Plugin
     * @param item   the Item
     * @see JavaPlugin
     * @see ItemStack
     */
    public ItemBuilder(@NotNull JavaPlugin plugin, @NotNull ItemStack item) {
        this.plugin = plugin;
        this.item = item;
        meta = this.item.getItemMeta();
    }

    /**
     * Constructor to create an custom Item
     *
     * @param plugin   the Plugin
     * @param material the Material
     */
    public ItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material) {
        this(plugin, new ItemStack(material, 1));
    }

    /**
     * Constructor to create an custom Item with an specific amount
     *
     * @param plugin   the Plugin
     * @param material the Material
     * @param amount   the Amount
     */
    public ItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material, @NotNull Integer amount) {
        this(plugin, new ItemStack(material, amount));
    }

    /**
     * Method to set the DisplayName
     *
     * @param displayName the Name
     * @return the current ItemBuilder
     */
    public ItemBuilder setDisplayName(@NotNull String displayName) {
        return this.setDisplayName(Component.text(displayName));
    }

    /**
     * Method to set the DisplayName
     *
     * @param displayName the Name
     * @return the current ItemBuilder
     */
    public ItemBuilder setDisplayName(@NotNull Component displayName) {
        meta.displayName(displayName);
        return this;
    }

    /**
     * Method to set the Amount
     *
     * @param amount the Amount
     * @return the current ItemBuilder
     */
    public ItemBuilder setAmount(@NotNull Integer amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Method to set the Lore
     *
     * @param lore the Lore
     * @return the current ItemBuilder
     */
    public ItemBuilder setLore(@NotNull String @NotNull ... lore) {
        List<Component> componentList = new ArrayList<>();
        for (String s : lore)
            componentList.add(Component.text(s));
        return this.setLore(componentList);
    }

    /**
     * Method to set the Lore
     *
     * @param components the Lore
     * @return the current ItemBuilder
     */
    public ItemBuilder setLore(@NotNull Component... components) {
        return this.setLore(Arrays.asList(components));
    }

    /**
     * Method to set the Lore
     *
     * @param componentList the Lore
     * @return the current ItemBuilder
     */
    public ItemBuilder setLore(@NotNull List<Component> componentList) {
        meta.lore(componentList);
        return this;
    }

    /**
     * Method to add Line(s) to the Lore
     *
     * @param lore the Line(s) to add
     * @return the current ItemBuilder
     */
    public ItemBuilder addToLore(@NotNull String @NotNull ... lore) {
        List<Component> componentList = new ArrayList<>();
        for (String s : lore)
            componentList.add(Component.text(s));
        return this.addToLore(componentList);
    }

    /**
     * Method to add Line(s) to the Lore
     *
     * @param lore the Line(s) to add
     * @return the current ItemBuilder
     */
    public ItemBuilder addToLore(@NotNull Component... lore) {
        return this.addToLore(Arrays.asList(lore));
    }

    /**
     * Method to add Line(s) to the Lore
     *
     * @param lore the Line(s) to add
     * @return the current ItemBuilder
     */
    public ItemBuilder addToLore(@NotNull List<Component> lore) {
        List<Component> currentLore = meta.lore() == null ? new ArrayList<>() : meta.lore();
        Objects.requireNonNull(currentLore).addAll(lore);
        meta.lore(currentLore);
        return this;
    }

    /**
     * Method to remove a Line from the Lore
     *
     * @param index the Index
     * @return the current ItemBuilder
     * @throws ItemBuilderException if the index is out of range
     */
    public ItemBuilder removeFromLore(@NotNull Integer index) throws ItemBuilderException {
        List<Component> currentLore = meta.lore() == null ? new ArrayList<>() : meta.lore();

        if (index > Objects.requireNonNull(currentLore).size() - 1)
            throw new ItemBuilderException(plugin, "Given Index is out of range.", ItemBuilderException.LastEditAction.EDIT_TEXT);
        currentLore.remove((int) index);
        meta.lore(currentLore);
        return this;
    }

    /**
     * Method to add an Enchantment
     *
     * @param enchantment the Enchantment
     * @param level       the Level
     * @return the current ItemBuilder
     * @throws ItemBuilderException throw if the enchantment cannot be applied to the item or the level is out of range
     */
    public ItemBuilder addEnchantment(@NotNull Enchantment enchantment, @NotNull Integer level) throws ItemBuilderException {
        if (!enchantment.canEnchantItem(item))
            throw new ItemBuilderException(plugin, "Cannot apply Enchantment on Item. (Please use ItemBuilder#addUnsafeEnchantment)", ItemBuilderException.LastEditAction.EDIT_ENCHANTMENT);
        if (level > enchantment.getMaxLevel())
            throw new ItemBuilderException(plugin, String.format("Given Level is out of range. (Given Level: '%d'. Max Level: '%d'. Please use ItemBuilder#addUnsafeEnchantment)", level, enchantment.getMaxLevel()), ItemBuilderException.LastEditAction.EDIT_ENCHANTMENT);
        item.addEnchantment(enchantment, level);
        return this;
    }

    /**
     * Method to add an Enchantment (Ignoring levels and compatibility)
     *
     * @param enchantment the Enchantment
     * @param level       the Level
     * @return the current ItemBuilder
     */
    public ItemBuilder addUnsafeEnchantment(@NotNull Enchantment enchantment, @NotNull Integer level) {
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    @Override
    public @NotNull ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
