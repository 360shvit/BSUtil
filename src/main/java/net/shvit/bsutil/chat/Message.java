package net.shvit.bsutil.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.shvit.bsutil.BSUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Message {

    private final TextComponent.Builder message;

    public Message() {
        this.message = Component.text("").toBuilder();
    }

    public Message(String text) {
        this.message = Component.text(text).toBuilder();
    }

    public Message(String message, TextColor color) {
        this.message = Component.text("").content(message).color(color).toBuilder();
    }

    public Message(@NotNull Message message) {
        this.message = message.build().toBuilder();
    }

    public Message addPrefix() {

        this.message.append(BSUtil.getPrefix());
        return this;

    }

    public Message newLine() {

        TextComponent newLine = Component.newline();

        this.message.append(newLine);

        return this;

    }

    public Message addText(String text) {
        TextComponent addText = Component.text().content(text).build();
        message.append(addText);
        return this;
    }

    public Message addText(String text, TextColor color) {
        TextComponent addText = Component.text().content(text).color(color).build();
        this.message.append(addText);
        return this;
    }

    public Message addText(String text, TextColor color, TextDecoration deco) {
        TextComponent addText = Component.text().content(text).color(color).decorate(deco).build();
        this.message.append(addText);
        return this;
    }

    public Message addClickText(String text, ClickEvent event, TextColor color) {

        TextComponent addText = Component.text().content(text).color(color).clickEvent(event).build();
        this.message.append(addText);
        return this;
    }

    public Message addHoverText(String text, String displayText, TextColor color) {

        TextComponent displaySection = Component.text().content(displayText).color(color).build();
        TextComponent addTextSection = Component
                .text()
                .content(text)
                .color(color)
                .hoverEvent(HoverEvent.showText(displaySection))
                .build();

        this.message.append(addTextSection);

        return this;
    }

    public Message addHoverText(String text, @NotNull Message displayText, TextColor color) {

        TextComponent displaySection = Component.text().append(displayText.build()).color(color).build();
        TextComponent addTextSection = Component
                .text()
                .content(text)
                .color(color)
                .hoverEvent(HoverEvent.showText(displaySection))
                .build();

        this.message.append(addTextSection);

        return this;
    }

    public Message addHoverText(String text, String displayText, TextColor color, TextColor displayColor) {

        TextComponent displaySection = Component.text().content(displayText).color(displayColor).build();
        TextComponent addTextSection = Component
                .text()
                .content(text)
                .color(color)
                .hoverEvent(HoverEvent.showText(displaySection))
                .build();

        this.message.append(addTextSection);

        return this;
    }

    public Message addClickHoverText(String text, String displayText, TextColor color, ClickEvent event) {

        TextComponent displaySection = Component.text().content(displayText).color(color).build();
        TextComponent addTextSection = Component
                .text()
                .content(text)
                .color(color)
                .hoverEvent(HoverEvent.showText(displaySection))
                .clickEvent(event)
                .build();

        this.message.append(addTextSection);

        return this;
    }

    public Message addClickHoverText(String text, String displayText, TextColor color, TextColor displayColor, ClickEvent event) {

        TextComponent displaySection = Component.text().content(displayText).color(displayColor).build();
        TextComponent addTextSection = Component
                .text()
                .content(text)
                .color(color)
                .hoverEvent(HoverEvent.showText(displaySection))
                .clickEvent(event)
                .build();

        this.message.append(addTextSection);

        return this;
    }

    public Message addList(@NotNull ArrayList<String> list, String separator, TextColor color, TextColor separatorColor) {
        Message message = new Message();

        for (String string : list) {
            message.addText(string, color).addText(separator, separatorColor);
        }

        this.message.append(message.build());

        return this;
    }

    public Message addHoverList(@NotNull ArrayList<String> list, String separator, TextColor color, TextColor separatorColor) {
        Message addHoverList = new Message();

        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i);
            addHoverList.addHoverText(text, text, color).addText(separator, separatorColor);

            if ((i + 1) % 5 == 0) {
                addHoverList.newLine();
            }
        }

        this.message.append(addHoverList.build());
        return this;
    }

    public TextComponent build() {

        return this.message.build();

    }

    public void send(@NotNull Player player) {

        player.sendMessage(this.message.build());

    }

}

