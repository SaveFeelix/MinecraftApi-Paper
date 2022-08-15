package de.savefeelix.minecraftapi.defaults.timer;

import de.savefeelix.minecraftapi.interfaces.ITimer;
import de.savefeelix.minecraftapi.interfaces.ITimerAction;
import de.savefeelix.minecraftapi.interfaces.ITimerEndAction;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public abstract class BaseTimer<TTimer extends ITimer> implements ITimer {

    protected final JavaPlugin plugin;
    protected final TimerDirection direction;
    protected final ITimerAction<TTimer> onIntervalAction;
    protected final ITimerEndAction<TTimer> canEndAction;
    protected final Long delay, period;

    protected int countdownId;
    protected Long time;
    protected TimerState state;

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
