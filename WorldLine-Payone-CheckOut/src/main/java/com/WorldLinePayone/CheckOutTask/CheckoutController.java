package com.WorldLinePayone.CheckOutTask;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/scan")
    public void scanItem(@RequestBody String item) {
        checkoutService.scan(item);
    }

    @GetMapping("/total")
    public int getTotalPrice() {
        return checkoutService.total();
    }
}
