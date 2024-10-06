package com.WorldLinePayone.CheckOutTask;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorldLinePayoneCheckOutApplicationTests {

	 @InjectMocks
	    private CheckoutService checkoutService;

	    @Mock
	    private PricingClient pricingClient;

	    public CheckoutServiceTest() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testTotalPrice() {
	        when(pricingClient.getPrice("A", 3)).thenReturn(130);
	        when(pricingClient.getPrice("B", 2)).thenReturn(45);

	        checkoutService.scan("A");
	        checkoutService.scan("A");
	        checkoutService.scan("A");
	        checkoutService.scan("B");
	        checkoutService.scan("B");

	        assertEquals(175, checkoutService.total());
	    }

	    @Test
	    public void testFallbackPrice() {
	        when(pricingClient.getPrice("A", 3)).thenThrow(new RuntimeException("Service Down"));

	        checkoutService.scan("A");
	        checkoutService.scan("A");
	        checkoutService.scan("A");

	        assertEquals(0, checkoutService.total()); // Fallback to 0
	    }
	}
}
