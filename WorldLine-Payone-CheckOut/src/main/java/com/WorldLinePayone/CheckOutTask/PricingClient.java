package com.WorldLinePayone.CheckOutTask;

@FeignClient(name = "pricing-service", url = "http://localhost:8081")
public interface PricingClient {
    @GetMapping("/price")
    int getPrice(@RequestParam String item, @RequestParam int quantity);
}
