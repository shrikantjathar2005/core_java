package com.mmcoe.test;

// Import assertion method to compare expected and actual values
import static org.junit.jupiter.api.Assertions.assertEquals;

// Import Mockito's when() method to define mock behavior
import static org.mockito.Mockito.when;

// JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// Mockito imports
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Project classes
import com.mmcoe.pojo.CurrencyExchange;
import com.mmcoe.pojo.ExchangeService;

// Enable Mockito support in JUnit 5
@ExtendWith(MockitoExtension.class)
public class TestCurrencyExchange {

    // Create a mock (fake) object of ExchangeService
    @Mock
    private ExchangeService service;

    // Create CurrencyExchange object and inject the mock service into it
    @InjectMocks
    private CurrencyExchange ex;

    // Test case to verify currency conversion
    @Test
    public void testConvert() {

        // Define mock behavior:
        // If usdToInr(100) is called,
        // return 9100.0 instead of executing the real method.
        when(service.usdToInr(100)).thenReturn(9100.0);

        // Call the method under test
        double result = ex.convert(100);

        // Verify the returned value
        // Expected = 9100.0
        // Actual = result
        assertEquals(9100.0, result);
    }
}