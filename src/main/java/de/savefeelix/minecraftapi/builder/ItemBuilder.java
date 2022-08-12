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

public final class ItemBuilder implements IBuilder<ItemStack> {


    private final JavaPlugin plugin;
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(@NotNull JavaPlugin plugin, @NotNull ItemStack item) {
        this.plugin = plugin;
        this.item = item;
        meta = this.item.getItemMeta();
    }

    public ItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material) {
        this(plugin, new ItemStack(material, 1));
    }

    public ItemBuilder(@NotNull JavaPlugin plugin, @NotNull Material material, @NotNull Integer amount) {
        this(plugin, new ItemStack(material, amount));
    }

    public ItemBuilder setDisplayName(@NotNull String displayName) {
        return this.setDisplayName(Component.text(displayName));
    }

    public ItemBuilder setDisplayName(@NotNull Component displayName) {
        meta.displayName(displayName);
        return this;
    }

    public ItemBuilder setAmount(@NotNull Integer amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setLore(@NotNull String @NotNull ... lore) {
        List<Component> componentList = new ArrayList<>();
        for (String s : lore)
            componentList.add(Component.text(s));
        return this.setLore(componentList);
    }

    public ItemBuilder setLore(@NotNull Component... components) {
        return this.setLore(Arrays.asList(components));
    }

    public ItemBuilder setLore(@NotNull List<Component> componentList) {
        meta.lore(componentList);
        return this;
    }

    public ItemBuilder addToLore(@NotNull String @NotNull ... lore) {
        List<Component> componentList = new ArrayList<>();
        for (String s : lore)
            componentList.add(Component.text(s));
        return this.addToLore(componentList);
    }

    public ItemBuilder addToLore(@NotNull Component... lore) {
        return this.addToLore(Arrays.asList(lore));
    }

    public ItemBuilder addToLore(@NotNull List<Component> lore) {
        List<Component> currentLore = meta.lore() == null ? new ArrayList<>() : meta.lore();
        Objects.requireNonNull(currentLore).addAll(lore);
        meta.lore(currentLore);
        return this;
    }

    public ItemBuilder removeFromLore(@NotNull Integer index) throws ItemBuilderException {
        List<Component> currentLore = meta.lore() == null ? new ArrayList<>() : meta.lore();

        if (index > Objects.requireNonNull(currentLore).size() - 1)
            throw new ItemBuilderException(plugin, "Given Index is out of range.", ItemBuilderException.LastEditAction.EDIT_TEXT);
        currentLore.remove((int) index);
        meta.lore(currentLore);
        return this;
    }

    public ItemBuilder addEnchantment(@NotNull Enchantment enchantment, @NotNull Integer level) throws ItemBuilderException {
        if (!enchantment.canEnchantItem(item))
            throw new ItemBuilderException(plugin, "Cannot apply Enchantment on Item. (Please use ItemBuilder#addUnsafeEnchantment)", ItemBuilderException.LastEditAction.EDIT_ENCHANTMENT);
        if (level > enchantment.getMaxLevel())
            throw new ItemBuilderException(plugin, String.format("Given Level is out of range. (Given Level: '%d'. Max Level: '%d'. Please use ItemBuilder#addUnsafeEnchantment)", level, enchantment.getMaxLevel()), ItemBuilderException.LastEditAction.EDIT_ENCHANTMENT);
        item.addEnchantment(enchantment, level);
        return this;
    }

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
