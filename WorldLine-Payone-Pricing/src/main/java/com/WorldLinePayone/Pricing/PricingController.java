package com.WorldLinePayone.Pricing;

@RestController
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @GetMapping("/price")
    public int getPrice(@RequestParam String item, @RequestParam int quantity) {
        return pricingService.getPrice(item, quantity);
    }
}
