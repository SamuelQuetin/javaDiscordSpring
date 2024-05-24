package io.github.stackpan.archetype.jdaspringquickstart.configuration;

import com.freya02.botcommands.api.CommandsBuilder;
import io.github.stackpan.archetype.jdaspringquickstart.configuration.properties.DiscordConfigurationProperties;
import io.github.stackpan.archetype.jdaspringquickstart.discord.ExtensionRegister;
import io.github.stackpan.archetype.jdaspringquickstart.listener.LiveChatListener;
import io.github.stackpan.archetype.jdaspringquickstart.service.LiveChatService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ComponentScan("io.github.stackpan.archetype.jdaspringquickstart.listener")
public class DiscordConfigurer {

    private final DiscordConfigurationProperties discordConfigurationProperties;

    private final ExtensionRegister extensionRegister;

    private final LiveChatListener liveChatListener;

    @Bean
    public JDA jda() throws InterruptedException {
        JDA jda =  JDABuilder
                .createDefault(discordConfigurationProperties.botToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.watching("en train de se construire"))
                .build();

        jda.awaitReady();
        CommandsBuilder.newBuilder()
                .extensionsBuilder(extensionRegister) // Don't remove this! This is necessary for registering your beans
                .build(jda, "io.github.stackpan.archetype.jdaspringquickstart.command");

        jda.addEventListener(liveChatListener);

        return jda;
    }

}
