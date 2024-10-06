package com.WorldLinePayone.Pricing;

@Service
public class PricingService {

    private static final Map<String, Integer> prices = new HashMap<>();
    private static final Map<String, SpecialPrice> specials = new HashMap<>();

    static {
        prices.put("A", 50);
        prices.put("B", 30);
        prices.put("C", 20);
        prices.put("D", 15);

        specials.put("A", new SpecialPrice(3, 130));
        specials.put("B", new SpecialPrice(2, 45));
    }

    public int getPrice(String item, int quantity) {
        if (specials.containsKey(item)) {
            SpecialPrice specialPrice = specials.get(item);
            int totalSpecialPrice = (quantity / specialPrice.getQuantity()) * specialPrice.getPrice();
            int remainderPrice = (quantity % specialPrice.getQuantity()) * prices.get(item);
            return totalSpecialPrice + remainderPrice;
        } else {
            return prices.get(item) * quantity;
        }
    }

    private static class SpecialPrice {
        private int quantity;
        private int price;

        public SpecialPrice(int quantity, int price) {
            this.quantity = quantity;
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPrice() {
            return price;
        }
    }
}
