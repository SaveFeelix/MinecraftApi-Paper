package de.savefeelix.minecraftapi.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

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
     * @param commandSender Instance of the player
     */
    void display(@NotNull String message, @NotNull CommandSender commandSender);

    /**
     * Default method to display the timer to all Player
     */
    default void broadcast(@NotNull String message) {
        for (Player player : Bukkit.getOnlinePlayers())
            this.display(message, player);
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
    @NotNull CountdownTime getTime();

    /**
     * Getter for the TimerId
     *
     * @return the ID
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
        /**
         * Set the Timer going up
         */
        Up,

        /**
         * Set the Timer going down
         */
        Down
    }

    class CountdownTime {
        private final long seconds, minutes, hours, days;

        public CountdownTime(long seconds, long minutes, long hours, long days) {
            this.seconds = seconds;
            this.minutes = minutes;
            this.hours = hours;
            this.days = days;
        }

        public CountdownTime(long seconds, long minutes, long hours) {
            this(seconds, minutes, hours, 0);
        }

        public CountdownTime(long seconds, long minutes) {
            this(seconds, minutes, 0, 0);
        }

        public CountdownTime(long seconds) {
            this(seconds, 0, 0, 0);
        }

        public long getSeconds() {
            return seconds;
        }

        public long getMinutes() {
            return minutes;
        }

        public long getHours() {
            return hours;
        }

        public long getDays() {
            return days;
        }

        public static @NotNull CountdownTime parse(@NotNull Long countdownTime, @NotNull TimeUnit unit) {
            long seconds = 0, minutes = 0, hours = 0, days = 0;
            switch (unit) {
                case SECONDS -> {
                    seconds = countdownTime;
                    while (seconds >= 60) {
                        seconds -= 60;
                        minutes++;
                    }
                    CountdownTime generated = CountdownTime.parse(minutes, TimeUnit.MINUTES);
                    minutes = generated.getMinutes();
                    hours = generated.getHours();
                    days = generated.getDays();
                }
                case MINUTES -> {
                    minutes = countdownTime;
                    while (minutes >= 60) {
                        minutes -= 60;
                        hours++;
                    }
                    CountdownTime generated = CountdownTime.parse(hours, TimeUnit.HOURS);
                    hours = generated.getHours();
                    days = generated.getDays();
                }
                case HOURS -> {
                    hours = countdownTime;
                    while (hours >= 24) {
                        hours -= 24;
                        days++;
                    }
                }
                default -> throw new IllegalArgumentException("");
            }
            return new CountdownTime(seconds, minutes, hours, days);
        }

    }

}
