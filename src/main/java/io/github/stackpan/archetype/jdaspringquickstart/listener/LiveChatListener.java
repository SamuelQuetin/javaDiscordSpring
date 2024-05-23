package io.github.stackpan.archetype.jdaspringquickstart.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class LiveChatListener extends ListenerAdapter {

    private final String targetGuildId; // Replace with your guild ID
    private final String targetChannelId; // Replace with your channel ID
    private String websocketUrl;

    public LiveChatListener(String targetGuildId, String targetChannelId) {
        this.targetGuildId = targetGuildId;
        this.targetChannelId = targetChannelId;
        this.websocketUrl = "http://localhost:8080/imageUpdates";
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
                        channel.sendMessage("Image envoyé").queue();
                        sendImageUrlToWebServer(attachment.getUrl());
                    }
                }
            }
        }
    }

    private void sendImageUrlToWebServer(String imageUrl) {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(websocketUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(imageUrl))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Image URL sent to WebSocket endpoint: " + imageUrl);
            } else {
                System.err.println("Error sending image URL: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending image URL: " + e.getMessage());
        }
    }
}
