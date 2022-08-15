package de.savefeelix.minecraftapi.interfaces;

import org.jetbrains.annotations.NotNull;

public interface ITimerEndAction<TTimer extends ITimer> {
    @NotNull Boolean execute(TTimer timer);
}
