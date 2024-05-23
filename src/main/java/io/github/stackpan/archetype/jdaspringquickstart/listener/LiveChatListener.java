package io.github.stackpan.archetype.jdaspringquickstart.listener;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@AllArgsConstructor
public class LiveChatListener extends ListenerAdapter {

    private String targetGuildId; // Replace with your guild ID
    private String targetChannelId; // Replace with your channel ID

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        Guild guild = event.getGuild();
        TextChannel channel = (TextChannel) event.getChannel();

        if (guild.getId().equals(targetGuildId) && channel.getId().equals(targetChannelId) && !event.getAuthor().isBot() ) {
            // Message is in the specified guild and channel
            System.out.println("Message received in target channel: " + message.getContentDisplay());
            // Handle message as needed (e.g., process commands, reply, etc.)
            if(message.getAttachments().size() > 0){
                for (Message.Attachment attachment : message.getAttachments()){
                    channel.sendMessage(attachment.getUrl()).queue();
                }
            }
        }
    }
}
