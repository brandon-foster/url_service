package com.joyldp.urlservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyldp.urlservice.dto.UrlDto;
import com.joyldp.urlservice.entity.UrlEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlRestControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private static final String CONTROLLER_URL_API = "/api/shorturl";
    private static final String EXAMPLE_URL = "http://example.com";

    public UrlRestControllerTest(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testCreateUrlWithOnlyOriginalUrl() throws Exception {
        final UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl(EXAMPLE_URL);
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndCustomAlias() throws Exception {
        final UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl(EXAMPLE_URL);
        urlDto.setCustomAlias("myAlias");
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndUsername() throws Exception {
        final UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl(EXAMPLE_URL);
        urlDto.setUsername("john_doe");
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndExpireDate() throws Exception {
        final UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl(EXAMPLE_URL);
        urlDto.setExpireDate("2023-08-25");
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithAllParameters() throws Exception {
        final UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl(EXAMPLE_URL);
        urlDto.setCustomAlias("myAlias");
        urlDto.setUsername("john_doe");
        urlDto.setExpireDate("2023-08-25");
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithoutOriginalUrl() throws Exception {
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testProvideUrlEntityByHashWithHashThatExists() throws Exception {
        // Given: A hash exists that exists.
        final UrlDto urlDto = new UrlDto();
        urlDto.setOriginalUrl(EXAMPLE_URL);
        final MvcResult mvcResult = mockMvc.perform(post(CONTROLLER_URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        final String responseBody = mvcResult.getResponse().getContentAsString();
        final UrlEntity urlEntity = objectMapper.readValue(responseBody, UrlEntity.class);
        final String hash = urlEntity.getHash();

        // When: GET /api/{hash}
        mockMvc.perform(get(CONTROLLER_URL_API + "/" + hash)
                        .contentType(MediaType.APPLICATION_JSON))
        // Then: OK Status.
                .andExpect(status().isOk());
    }

    @Test
    public void testProvideUrlEntityByHashWithHashThatDoesNotExist() throws Exception {
        // Given: A hash exists that does not exist.
        final String hash = "***!!!";

        // When: GET /api/{hash}
        mockMvc.perform(get(CONTROLLER_URL_API + "/" + hash)
                        .contentType(MediaType.APPLICATION_JSON))
        // Then: NOT_FOUND Status.
        .andExpect(status().isNotFound());
    }
}
