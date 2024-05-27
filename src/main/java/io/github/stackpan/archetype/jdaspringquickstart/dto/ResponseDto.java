package io.github.stackpan.archetype.jdaspringquickstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ResponseDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("discordUrl")
    private String discordUrl;
}
