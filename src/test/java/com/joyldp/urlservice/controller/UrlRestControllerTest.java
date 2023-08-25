package com.joyldp.urlservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static final String CONTROLLER_URL_API = "/api";
    private static final String EXAMPLE_URL = "http://example.com";
    private static final String PARAM_KEY_ORIGINAL_URL = "originalUrl";
    private static final String PARAM_KEY_CUSTOM_ALIAS = "customAlias";
    private static final String PARAM_KEY_USERNAME = "username";
    private static final String PARAM_KEY_EXPIRE_DATE = "expireDate";

    public UrlRestControllerTest(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testCreateUrlWithOnlyOriginalUrl() throws Exception {
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndCustomAlias() throws Exception {
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_CUSTOM_ALIAS, "myAlias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndUsername() throws Exception {
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_USERNAME, "john_doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndExpireDate() throws Exception {
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_EXPIRE_DATE, "2023-08-25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithAllParameters() throws Exception {
        mockMvc.perform(post(CONTROLLER_URL_API)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_CUSTOM_ALIAS, "myAlias")
                        .param(PARAM_KEY_USERNAME, "john_doe")
                        .param(PARAM_KEY_EXPIRE_DATE, "2023-08-25")
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
        final MvcResult mvcResult = mockMvc.perform(post(CONTROLLER_URL_API)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
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
