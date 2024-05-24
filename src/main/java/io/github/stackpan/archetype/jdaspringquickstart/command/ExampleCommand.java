package io.github.stackpan.archetype.jdaspringquickstart.command;

import com.freya02.botcommands.api.annotations.CommandMarker;
import com.freya02.botcommands.api.application.ApplicationCommand;
import com.freya02.botcommands.api.application.CommandScope;
import com.freya02.botcommands.api.application.slash.GlobalSlashEvent;
import com.freya02.botcommands.api.application.slash.annotations.JDASlashCommand;
import io.github.stackpan.archetype.jdaspringquickstart.service.LiveChatService;
import lombok.RequiredArgsConstructor;

@CommandMarker
@RequiredArgsConstructor
public class ExampleCommand extends ApplicationCommand {

    private final LiveChatService exampleService;

    @JDASlashCommand(name = "ping", scope = CommandScope.GLOBAL)
    public void ping(GlobalSlashEvent event) {
        var message = exampleService.getImageUrl();
        event.reply(message).queue();
    }
}
