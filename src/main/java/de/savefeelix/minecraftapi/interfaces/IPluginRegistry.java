package de.savefeelix.minecraftapi.interfaces;

import de.savefeelix.minecraftapi.exceptions.RegisterCommandException;
import de.savefeelix.minecraftapi.records.CommandInformation;
import org.bukkit.event.Listener;

import java.util.Arrays;

/**
 * Interface to register Commands/Listeners
 */
public interface IPluginRegistry {

    /**
     * Method to register Commands
     *
     * @param command the Command to register
     * @see CommandInformation
     * @see org.bukkit.command.CommandExecutor
     */
    void registerCommand(CommandInformation command) throws RegisterCommandException;

    /**
     * Method to register Listeners
     *
     * @param listener the Listener to register
     * @see Listener
     */
    void registerListener(Listener listener);


    /**
     * Default Method to register multiple commands
     *
     * @param commands the commands
     * @throws RegisterCommandException
     */
    default void registerCommands(CommandInformation... commands) throws RegisterCommandException {
        for (CommandInformation commandInformation : commands)
            registerCommand(commandInformation);
    }

    /**
     * Default Method to register multiple listeners
     *
     * @param listeners the listeners
     */
    default void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).toList().forEach(this::registerListener);
    }
}
