package de.savefeelix.minecraftapi.defaults.timer;

import de.savefeelix.minecraftapi.interfaces.ITimerAction;
import de.savefeelix.minecraftapi.interfaces.ITimerEndAction;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Class to create a ChatTimer
 */
public class ChatTimer extends BaseTimer<ChatTimer> {

    /**
     * Default Constructor
     * @param plugin the Plugin
     * @param direction the Timer-Direction
     * @param onIntervalAction Action on each Interval
     * @param canEndAction Action to get the End
     * @param time the Time
     * @param delay the Delay
     * @param period the Period
     */
    public ChatTimer(JavaPlugin plugin, TimerDirection direction, ITimerAction<ChatTimer> onIntervalAction, ITimerEndAction<ChatTimer> canEndAction, Long time, Long delay, Long period) {
        super(plugin, direction, onIntervalAction, canEndAction, time, delay, period);
    }

    @Override
    public void start() {
        countdownId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            onIntervalAction.execute(this);
            if (canEndAction.execute(this))
                stop();

            switch (direction) {
                case Down -> time--;
                case Up -> time++;
            }
        }, delay, period);
        state = TimerState.Running;
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(countdownId);
        state = TimerState.Finished;
    }

    @Override
    public void pause() {
        Bukkit.getScheduler().cancelTask(countdownId);
        state = TimerState.Paused;
    }

    @Override
    public void display(@NotNull String message, @NotNull CommandSender commandSender) {
        commandSender.sendMessage(message);
    }
}
