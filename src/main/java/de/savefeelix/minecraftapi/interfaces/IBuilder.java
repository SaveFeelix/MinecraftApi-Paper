package de.savefeelix.minecraftapi.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Interface to create a Builder.
 *
 * @param <TResult> Type of final result.
 */
public interface IBuilder<TResult> {

    /**
     * Method that creates the final object.
     *
     * @return Instance of the given Type
     */
    @NotNull TResult build();
}
