package net.agnina.customerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "customers.params")
public record CustomerConfigParams(int x , int y) {
}