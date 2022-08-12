package de.savefeelix.minecraftapi.exceptions;

import de.savefeelix.minecraftapi.records.CommandInformation;
import org.bukkit.plugin.java.JavaPlugin;

public class RegisterCommandException extends Exception {
    public RegisterCommandException(JavaPlugin plugin, CommandInformation information) {
        super(String.format("Failed to register Command! (Command: '', Plugin: '')", information.name(), plugin.getName()));

    }
}
