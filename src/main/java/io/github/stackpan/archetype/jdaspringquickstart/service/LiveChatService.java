package io.github.stackpan.archetype.jdaspringquickstart.service;

import io.github.stackpan.archetype.jdaspringquickstart.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter @Setter
public class LiveChatService {

    private ResponseDto responseDto;

    public LiveChatService(){
        responseDto = new ResponseDto();
    }

    public void setImageUrl(String imageUrl){
        responseDto.setImageUrl(imageUrl);
    }

    public void setUserId(String userId){
        responseDto.setDiscordUrl("https://widgets.vendicated.dev/user?id=" + userId + "&theme=dark&banner=false&full-banner=false&rounded-corners=false&discord-icon=false&badges=false&guess-nitro=true&");
    }

    public void setId(String id){
        responseDto.setId(id);
    }
}
