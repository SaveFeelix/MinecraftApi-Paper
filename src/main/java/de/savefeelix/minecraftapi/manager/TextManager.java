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

public final class TextManager {

    public TextManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static @NotNull Collection<Component> toComponent(@NotNull String @NotNull ... texts) {
        List<Component> componentList = new ArrayList<>();
        for (String text : texts)
            componentList.add(Component.text(text));
        return componentList;
    }

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

    public enum TextType {
        Json,
        Legacy,
        Plain
    }
}
