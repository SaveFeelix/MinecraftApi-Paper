package de.savefeelix.minecraftapi.builder;

import de.savefeelix.minecraftapi.defaults.timer.ChatTimer;
import de.savefeelix.minecraftapi.interfaces.IBuilder;
import de.savefeelix.minecraftapi.interfaces.ITimer;
import de.savefeelix.minecraftapi.interfaces.ITimerAction;
import de.savefeelix.minecraftapi.interfaces.ITimerEndAction;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ChatTimerBuilder implements IBuilder<ChatTimer> {

    private final JavaPlugin plugin;
    private final ITimerAction<ChatTimer> onInterval;
    private final ITimerEndAction<ChatTimer> onEnd;

    private ITimer.TimerDirection direction;

    private Long time, delay, period;


    public ChatTimerBuilder(JavaPlugin plugin, ITimerAction<ChatTimer> onInterval, ITimerEndAction<ChatTimer> onEnd) {
        this.plugin = plugin;
        this.onInterval = onInterval;
        this.onEnd = onEnd;
    }

    public ChatTimerBuilder setDirection(ITimer.TimerDirection direction) {
        this.direction = direction;
        return this;
    }
    public ChatTimerBuilder setStartTime(Long time) {
        this.time = time;
        return this;
    }
    public ChatTimerBuilder setDelay(Long delay) {
        this.delay = delay;
        return this;
    }
    public ChatTimerBuilder setPeriod(Long period) {
        this.period = period;
        return this;
    }


    @Override
    public @NotNull ChatTimer build() {
        return new ChatTimer(plugin, direction, onInterval, onEnd, time, delay, period);
    }
}
