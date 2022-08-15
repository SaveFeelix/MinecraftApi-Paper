package de.savefeelix.minecraftapi.exceptions;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Exception class thrown in a ItemBuilder
 */
public class ItemBuilderException extends Exception {

    /**
     * Default Constructor
     *
     * @param plugin  the Plugin
     * @param message the Message
     * @param action  the Action
     * @see JavaPlugin
     * @see LastEditAction
     */
    public ItemBuilderException(JavaPlugin plugin, String message, LastEditAction action) {
        super(String.format("Failed to create/edit Item: %s (Plugin: '%s', Last Action: '%s')", message, plugin.getName(), action.toString()));
    }

    /**
     * Enum where it is specified where the exception was thrown
     */
    public enum LastEditAction {
        /**
         * Thrown during add/remove an Enchantment
         */
        EDIT_ENCHANTMENT,

        /**
         * Thrown during editing Text (Lore, Display Name)
         */
        EDIT_TEXT
    }
}
