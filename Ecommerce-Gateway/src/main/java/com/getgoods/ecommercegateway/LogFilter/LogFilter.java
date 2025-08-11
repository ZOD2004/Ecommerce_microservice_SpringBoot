package com.getgoods.ecommercegateway.LogFilter;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;

public class LogFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Pre Procesing Logic "+exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            System.out.println("Post Processing Logic "+exchange.getResponse().toString());
            System.out.println("Post Processing Logic "+exchange.getResponse().getStatusCode());
        }));
    }
}
