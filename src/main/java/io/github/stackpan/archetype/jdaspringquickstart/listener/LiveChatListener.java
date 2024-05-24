package io.github.stackpan.archetype.jdaspringquickstart.listener;

import io.github.stackpan.archetype.jdaspringquickstart.service.LiveChatService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@DependsOn
public class LiveChatListener extends ListenerAdapter {

    @Value("${discord.guild-id}")
    private String targetGuildId;

    @Value("${discord.channel-id}")
    private String targetChannelId;

    @Autowired
    private LiveChatService liveChatService;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        Guild guild = event.getGuild();
        TextChannel channel = (TextChannel) event.getChannel();
        if(guild.getId().equals(targetGuildId) && channel.getId().equals(targetChannelId) && !event.getAuthor().isBot()) {
            System.out.println("Message received in target channel: " + message.getContentDisplay());
            if(!message.getAttachments().isEmpty()) {
                for (Message.Attachment attachment : message.getAttachments()) {
                    if (attachment.isImage()) {
                        liveChatService.setImageUrl(attachment.getUrl());
                        channel.sendMessage("Image envoyé").queue();
                    }
                }
            } else if(message.getContentDisplay().contains("image.png") && message.getContentDisplay().contains("https://cdn.discordapp.com/attachments/")){
                liveChatService.setImageUrl(message.getContentDisplay());
                channel.sendMessage("Image envoyé").queue();
            }
        }
    }
}
