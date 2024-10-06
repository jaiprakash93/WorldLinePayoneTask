package com.WorldLinePayone.CheckOutTask;

@Service
public class CheckoutService {

    private Map<String, Integer> items = new HashMap<>();

    @Autowired
    private PricingClient pricingClient;

    public void scan(String item) {
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    @CircuitBreaker(name = "pricingService", fallbackMethod = "fallbackGetPrice")
    public int total() {
        return items.entrySet().stream()
                .mapToInt(entry -> pricingClient.getPrice(entry.getKey(), entry.getValue()))
                .sum();
    }

    // Fallback method in case the PricingService is down
    public int fallbackGetPrice(String item, int quantity, Throwable t) {
        return 0; // Fallback logic
    }
}
