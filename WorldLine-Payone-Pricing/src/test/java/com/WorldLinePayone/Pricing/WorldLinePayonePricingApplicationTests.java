package com.WorldLinePayone.Pricing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorldLinePayonePricingApplicationTests {

    private final PricingService pricingService = new PricingService();

    @Test
    public void testGetPrice() {
        assertEquals(130, pricingService.getPrice("A", 3));
        assertEquals(100, pricingService.getPrice("A", 2));
        assertEquals(45, pricingService.getPrice("B", 2));
        assertEquals(60, pricingService.getPrice("C", 3));
    }
}
