package de.savefeelix.minecraftapi.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Interface to create the End-Action
 * @param <TTimer> Generic Timer-Type
 */
public interface ITimerEndAction<TTimer extends ITimer> {

    /**
     * Execute Method
     * @param timer the Timer (of the given Type)
     * @return true, if the timer should stop.
     */
    @NotNull Boolean execute(TTimer timer);
}
