package de.savefeelix.minecraftapi.interfaces;

/**
 * Interface to create an action that is executed by the timer at each interval
 *
 * @see ITimer
 */
public interface ITimerAction {

    /**
     * Method executed by the timer
     *
     * @param timer The instance of the timer
     */
    void execute(ITimer timer);
}
