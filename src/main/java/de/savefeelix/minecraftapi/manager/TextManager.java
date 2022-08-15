package de.savefeelix.minecraftapi.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class
 */
public final class TextManager {

    /**
     * Disable instantiation for this Class
     *
     * @throws IllegalAccessException if someone want to create an instance of this Manager
     */
    public TextManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Convert String to Collections
     *
     * @param texts the Text
     * @return a Collection of Components
     */
    public static @NotNull Collection<Component> toComponent(String @NotNull ... texts) {
        List<Component> componentList = new ArrayList<>();
        for (String text : texts)
            componentList.add(Component.text(text));
        return componentList;
    }

    /**
     * Convert Components to Text
     *
     * @param textType   specify the text type
     * @param components the Components
     * @return a Collection of Strings
     */
    public static @NotNull Collection<String> toString(@Nullable TextType textType, @NotNull Component @NotNull ... components) {
        textType = textType != null ? textType : TextType.Plain;
        List<String> list = new ArrayList<>();
        for (Component component : components) {
            switch (textType) {
                case Json -> list.add(GsonComponentSerializer.gson().serialize(component));
                case Plain -> list.add(PlainTextComponentSerializer.plainText().serialize(component));
                case Legacy -> list.add(LegacyComponentSerializer.legacyAmpersand().serialize(component));
            }
        }
        return list;
    }

    /**
     * Enum to specify the Text to convert
     */
    public enum TextType {
        /**
         * Convert the Components in Json Format
         */
        Json,
        /**
         * Convert the Components to Text with Color Codes
         */
        Legacy,
        /**
         * Convert the Components to Text
         */
        Plain
    }
}
