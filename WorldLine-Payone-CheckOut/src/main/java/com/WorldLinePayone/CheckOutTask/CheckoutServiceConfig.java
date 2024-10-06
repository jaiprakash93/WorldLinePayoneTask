package com.WorldLinePayone.CheckOutTask;

@Configuration
public class CheckoutServiceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
