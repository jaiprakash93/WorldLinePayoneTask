package com.WorldLinePayone.Pricing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorldLinePayonePricingApplicationTests {

    @InjectMocks
    private CheckoutService checkoutService;

    @Mock
    private PricingClient pricingClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        checkoutService.reset();  // Reset the cart before each test
    }

    // Helper method to scan items and assert the expected price
    private void checkPrice(String items, int expectedPrice) {
        for (char item : items.toCharArray()) {
            checkoutService.scan(String.valueOf(item));
        }
        assertEquals(expectedPrice, checkoutService.total());
    }

    @Test
    public void testAllCases() {
        // Test case: assert_equal(0, price(""))
        checkPrice("", 0);

        // Test case: assert_equal(50, price("A"))
        when(pricingClient.getPrice("A", 1)).thenReturn(50);
        checkPrice("A", 50);

        // Test case: assert_equal(80, price("AB"))
        when(pricingClient.getPrice("A", 1)).thenReturn(50);
        when(pricingClient.getPrice("B", 1)).thenReturn(30);
        checkPrice("AB", 80);

        // Test case: assert_equal(115, price("CDBA"))
        when(pricingClient.getPrice("C", 1)).thenReturn(20);
        when(pricingClient.getPrice("D", 1)).thenReturn(15);
        when(pricingClient.getPrice("B", 1)).thenReturn(30);
        when(pricingClient.getPrice("A", 1)).thenReturn(50);
        checkPrice("CDBA", 115);

        // Test case: assert_equal(100, price("AA"))
        when(pricingClient.getPrice("A", 2)).thenReturn(100);
        checkPrice("AA", 100);

        // Test case: assert_equal(130, price("AAA"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        checkPrice("AAA", 130);

        // Test case: assert_equal(180, price("AAAA"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        when(pricingClient.getPrice("A", 1)).thenReturn(50);
        checkPrice("AAAA", 180);

        // Test case: assert_equal(230, price("AAAAA"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        when(pricingClient.getPrice("A", 2)).thenReturn(100);
        checkPrice("AAAAA", 230);

        // Test case: assert_equal(260, price("AAAAAA"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        checkPrice("AAAAAA", 260);

        // Test case: assert_equal(160, price("AAAB"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        when(pricingClient.getPrice("B", 1)).thenReturn(30);
        checkPrice("AAAB", 160);

        // Test case: assert_equal(175, price("AAABB"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        when(pricingClient.getPrice("B", 2)).thenReturn(45);
        checkPrice("AAABB", 175);

        // Test case: assert_equal(190, price("AAABBD"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        when(pricingClient.getPrice("B", 2)).thenReturn(45);
        when(pricingClient.getPrice("D", 1)).thenReturn(15);
        checkPrice("AAABBD", 190);

        // Test case: assert_equal(190, price("DABABA"))
        when(pricingClient.getPrice("A", 3)).thenReturn(130);
        when(pricingClient.getPrice("B", 2)).thenReturn(45);
        when(pricingClient.getPrice("D", 1)).thenReturn(15);
        checkPrice("DABABA", 190);
    }
}
