package com.joyldp.urlservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.joyldp.urlservice.component.HashGenerator;
import com.joyldp.urlservice.repository.UrlRepository;

public class HashServiceTest {

    @Mock private UrlRepository urlRepository;
    @Mock private HashGenerator hashGenerator;
    @InjectMocks private HashService hashService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenGeneratedHashDoesNotExist_WhenProvideHash_ThenReturnGeneratedHash() {
        // Given
        final String expectedHash = "a8_2Jp";
        when(hashGenerator.generateHash()).thenReturn(expectedHash);
        when(urlRepository.existsById(expectedHash)).thenReturn(false);

        // When
        final String actual = hashService.provideHash();

        // Then
        assertEquals(expectedHash, actual);
        verify(urlRepository, times(1)).existsById(expectedHash);
        verify(hashGenerator, times(1)).generateHash();
    }

    @Test
    public void givenGeneratedHashExists_WhenProvideHash_ThenReturnUNAVAI() {
        // Given
        final String preExistingHash = "a8_2Jp";
        final String secondHash = "peI27D";
        when(hashGenerator.generateHash()).thenReturn(preExistingHash, secondHash);
        when(urlRepository.existsById(preExistingHash)).thenReturn(true);

        // When
        final String actual = hashService.provideHash();

        // Then
        assertEquals(secondHash, actual);
        verify(urlRepository, times(1)).existsById(preExistingHash);
        verify(urlRepository, times(1)).existsById(secondHash);
        verify(hashGenerator, times(2)).generateHash();
    }

}
