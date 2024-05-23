package io.github.stackpan.archetype.jdaspringquickstart.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LiveChatListener extends ListenerAdapter {

    private final String targetGuildId; // Replace with your guild ID
    private final String targetChannelId; // Replace with your channel ID

    public LiveChatListener(String targetGuildId, String targetChannelId) {
        this.targetGuildId = targetGuildId;
        this.targetChannelId = targetChannelId;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        Guild guild = event.getGuild();
        TextChannel channel = (TextChannel) event.getChannel();

        if (guild.getId().equals(targetGuildId) && channel.getId().equals(targetChannelId) && !event.getAuthor().isBot()) {
            // Message is in the specified guild and channel
            System.out.println("Message received in target channel: " + message.getContentDisplay());
            // Handle message as needed (e.g., process commands, reply, etc.)
            if (message.getAttachments().size() > 0) {
                for (Message.Attachment attachment : message.getAttachments()) {
                    if (attachment.isImage()) {
                        setUrlImage(attachment.getUrl());
                        channel.sendMessage("Image envoyé").queue();
                    }
                }
            }
        }
    }

    private void setUrlImage(String url) {
    }

}
