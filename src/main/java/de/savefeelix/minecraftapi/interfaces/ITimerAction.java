package de.savefeelix.minecraftapi.interfaces;

/**
 * Interface to create an action that is executed by the timer at each interval
 *
 * @param <TTimer> Type of the Timer
 * @see ITimer
 */
public interface ITimerAction<TTimer extends ITimer> {

    /**
     * Method executed by the timer
     *
     * @param timer the instance of the timer
     * @see ITimer
     */
    void execute(TTimer timer);
}
