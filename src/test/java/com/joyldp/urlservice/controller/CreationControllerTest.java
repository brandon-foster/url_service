package com.joyldp.urlservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreationControllerTest {

    private final MockMvc mockMvc;
    private static final String CONTROLLER_URL_API_CREATE = "/api/create";
    private static final String EXAMPLE_URL = "http://example.com";
    private static final String PARAM_KEY_ORIGINAL_URL = "originalUrl";
    private static final String PARAM_KEY_CUSTOM_ALIAS = "customAlias";
    private static final String PARAM_KEY_USERNAME = "username";
    private static final String PARAM_KEY_EXPIRE_DATE = "expireDate";

    public CreationControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testCreateUrlWithOnlyOriginalUrl() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL_API_CREATE)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndCustomAlias() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL_API_CREATE)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_CUSTOM_ALIAS, "myAlias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndUsername() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL_API_CREATE)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_USERNAME, "john_doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithOriginalUrlAndExpireDate() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL_API_CREATE)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_EXPIRE_DATE, "2023-08-25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithAllParameters() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL_API_CREATE)
                        .param(PARAM_KEY_ORIGINAL_URL, EXAMPLE_URL)
                        .param(PARAM_KEY_CUSTOM_ALIAS, "myAlias")
                        .param(PARAM_KEY_USERNAME, "john_doe")
                        .param(PARAM_KEY_EXPIRE_DATE, "2023-08-25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUrlWithoutOriginalUrl() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL_API_CREATE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
