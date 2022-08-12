package de.savefeelix.minecraftapi.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

/**
 * Interface to create a timer.
 */
public interface ITimer {

    /**
     * Method to start the timer.
     */
    void start();

    /**
     * Method to stop the timer.
     */
    void stop();

    /**
     * Method to pause the timer.
     */
    void pause();


    /**
     * Method to display the timer to the given player.
     *
     * @param players Instance of the player
     */
    void display(Player players);

    /**
     * Default method to display the timer to all Player
     */
    default void broadcast() {
        for (Player player : Bukkit.getOnlinePlayers())
            this.display(player);
    }

    /**
     * Default method to broadcast the timer to all player which has one of the given permission
     *
     * @param permissions the Permissions
     */
    default void broadcast(String... permissions) {
        this.broadcast(Arrays.asList(permissions));
    }

    /**
     * Default method to broadcast the timer to all player which has one of the given permission
     *
     * @param permissions the Permissions
     */
    default void broadcast(Collection<String> permissions) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (String permission : permissions) {
                if (player.hasPermission(permission)) {
                    this.display(player);
                    break;
                }
            }
        }
    }

    /**
     * Getter for the Plugin
     *
     * @return the Plugin
     * @see JavaPlugin
     */
    @NotNull JavaPlugin getPlugin();

    /**
     * Getter for the current Time
     *
     * @return the time
     */
    @NotNull Integer getTimeInSeconds();

    /**
     * Getter for the TimerId
     *
     * @return the Id
     */
    @NotNull Integer getTimerId();

    /**
     * Getter for the TimerState
     *
     * @return the State
     * @see TimerState
     */
    @NotNull TimerState getState();

    /**
     * Enum with all States
     *
     * @see ITimer#getState()
     */
    enum TimerState {
        /**
         * State, if the timer is created but not started or failed
         */
        Created,

        /**
         * State, if the timer is started but not failed
         */
        Running,

        /**
         * State, if the timer is paused but not failed
         */
        Paused,

        /**
         * State, if the timer is finished successfully
         */
        Finished,

        /**
         * State, if the timer failed
         */
        Failed
    }

    /**
     * Enum with all directions
     */
    enum TimerDirection {
        Up,
        Down
    }

}
