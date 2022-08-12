package de.savefeelix.minecraftapi.exceptions;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemBuilderException extends Exception {
    public ItemBuilderException(JavaPlugin plugin, String message, LastEditAction action) {
        super(String.format("Failed to create/edit Item: %s (Plugin: '%s', Last Action: '%s')", message, plugin.getName(), action.toString()));
    }

    public enum LastEditAction {
        EDIT_ENCHANTMENT,
        EDIT_AMOUNT,
        EDIT_TEXT
    }
}
