package io.github.stackpan.archetype.jdaspringquickstart.configuration;

import io.github.stackpan.archetype.jdaspringquickstart.service.LiveChatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiveChatServiceConfig {
    @Bean
    public LiveChatService liveChatService() {
        return new LiveChatService(); // Create and configure your LiveChatService instance
    }
}
