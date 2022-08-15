package de.savefeelix.minecraftapi.defaults.timer;

import de.savefeelix.minecraftapi.interfaces.ITimer;
import de.savefeelix.minecraftapi.interfaces.ITimerAction;
import de.savefeelix.minecraftapi.interfaces.ITimerEndAction;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * Class to create a Timer
 *
 * @param <TTimer> Generic Type for Actions
 */
public abstract class BaseTimer<TTimer extends ITimer> implements ITimer {

    /**
     * The Plugin
     */
    protected final JavaPlugin plugin;

    /**
     * The Direction
     */
    protected final TimerDirection direction;

    /**
     * Action which will be executed on each Interval
     */
    protected final ITimerAction<TTimer> onIntervalAction;

    /**
     * Action which will be executed on each Interval. If it's true then the timer will stop!
     */
    protected final ITimerEndAction<TTimer> canEndAction;

    /**
     * The start delay
     */
    protected final Long delay;

    /**
     * The period for each interval
     */
    protected final Long period;

    /**
     * The ID of the Timer
     */
    protected int countdownId;

    /**
     * The timer
     */
    protected Long time;

    /**
     * The Timer-State
     */
    protected TimerState state;

    /**
     * Default Constructor
     *
     * @param plugin           the Plugin
     * @param direction        the Timer-Direction
     * @param onIntervalAction Action on each Interval
     * @param canEndAction     Action to get the End
     * @param time             the Time
     * @param delay            the Delay
     * @param period           the Period
     */
    public BaseTimer(JavaPlugin plugin, TimerDirection direction, ITimerAction<TTimer> onIntervalAction, ITimerEndAction<TTimer> canEndAction, Long time, Long delay, Long period) {
        this.plugin = plugin;
        this.direction = direction;
        this.onIntervalAction = onIntervalAction;
        this.canEndAction = canEndAction;
        this.time = time;
        this.state = TimerState.Created;
        this.delay = delay;
        this.period = period;
    }

    @NotNull
    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public @NotNull Integer getTimerId() {
        return countdownId;
    }

    @NotNull
    @Override
    public ITimer.TimerState getState() {
        return state;
    }

    @Override
    public @NotNull CountdownTime getTime() {
        return CountdownTime.parse(time, TimeUnit.SECONDS);
    }
}
