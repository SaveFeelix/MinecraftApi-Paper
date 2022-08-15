package de.savefeelix.minecraftapi.exceptions;

import de.savefeelix.minecraftapi.records.CommandInformation;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Exception thrown during register the Command
 */
public class RegisterCommandException extends Exception {

    /**
     * Default Constructor
     *
     * @param plugin      the Plugin
     * @param information the CommandInformation
     * @see JavaPlugin
     * @see CommandInformation
     */
    public RegisterCommandException(JavaPlugin plugin, CommandInformation information) {
        super(String.format("Failed to register Command! (Command: '%s', Plugin: '%s')", information.name(), plugin.getName()));

    }
}
