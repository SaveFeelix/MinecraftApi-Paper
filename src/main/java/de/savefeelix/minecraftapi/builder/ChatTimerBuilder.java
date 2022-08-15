package de.savefeelix.minecraftapi.builder;

import de.savefeelix.minecraftapi.defaults.timer.ChatTimer;
import de.savefeelix.minecraftapi.interfaces.IBuilder;
import de.savefeelix.minecraftapi.interfaces.ITimer;
import de.savefeelix.minecraftapi.interfaces.ITimerAction;
import de.savefeelix.minecraftapi.interfaces.ITimerEndAction;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Class to create a ChatTimer
 */
public class ChatTimerBuilder implements IBuilder<ChatTimer> {

    /**
     * The Plugin
     */
    private final JavaPlugin plugin;

    /**
     * Action on each Interval
     */
    private final ITimerAction<ChatTimer> onInterval;

    /**
     * Action to get the End
     */
    private final ITimerEndAction<ChatTimer> onEnd;

    /**
     * The Timer-Direction
     */
    private ITimer.TimerDirection direction;

    /**
     * The Time
     */
    private Long time;

    /**
     * The Delay
     */
    private Long delay;

    /**
     * The Period
     */
    private Long period;


    /**
     * Default Constructor
     *
     * @param plugin     the Plugin
     * @param onInterval Action on each Interval
     * @param onEnd      Action to get the End
     */
    public ChatTimerBuilder(JavaPlugin plugin, ITimerAction<ChatTimer> onInterval, ITimerEndAction<ChatTimer> onEnd) {
        this.plugin = plugin;
        this.onInterval = onInterval;
        this.onEnd = onEnd;
    }

    /**
     * Setter for the Timer-Direction
     *
     * @param direction the Timer-Direction
     * @return current ChatTimerBuilder
     */
    public ChatTimerBuilder setDirection(ITimer.TimerDirection direction) {
        this.direction = direction;
        return this;
    }

    /**
     * Setter for the Time
     *
     * @param time the Time
     * @return current ChatTimerBuilder
     */
    public ChatTimerBuilder setStartTime(Long time) {
        this.time = time;
        return this;
    }

    /**
     * Setter for the Delay
     *
     * @param delay the Delay
     * @return current ChatTimerBuilder
     */
    public ChatTimerBuilder setDelay(Long delay) {
        this.delay = delay;
        return this;
    }

    /**
     * Setter for the Period
     *
     * @param period the Period
     * @return current ChatTimerBuilder
     */
    public ChatTimerBuilder setPeriod(Long period) {
        this.period = period;
        return this;
    }


    @Override
    public @NotNull ChatTimer build() {
        return new ChatTimer(plugin, direction, onInterval, onEnd, time, delay, period);
    }
}
