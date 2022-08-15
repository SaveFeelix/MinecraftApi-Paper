package de.savefeelix.minecraftapi.records;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Method to create CommandInformation
 *
 * @param name      the Name of the Command
 * @param command   the Command (Executor class)
 * @param completer the TabCompleter
 * @param aliases   the aliases (optional)
 * @see CommandExecutor
 * @see TabCompleter
 */
public record CommandInformation(@NotNull String name, @NotNull CommandExecutor command,
                                 @Nullable TabCompleter completer, String... aliases) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CommandInformation) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.command, that.command) &&
                Objects.equals(this.completer, that.completer) &&
                Arrays.equals(this.aliases, that.aliases);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
