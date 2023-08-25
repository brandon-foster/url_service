package com.joyldp.urlservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UrlDto {
    @JsonProperty("originalUrl")
    private String originalUrl;
    @JsonProperty("customAlias")
    private String customAlias;
    @JsonProperty("username")
    private String username;
    @JsonProperty("expireDate")
    private String expireDate;
}
