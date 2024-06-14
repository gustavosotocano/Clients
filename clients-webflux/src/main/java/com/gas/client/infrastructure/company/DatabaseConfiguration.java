package com.gas.client.infrastructure.company;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.TransactionAwareConnectionFactoryProxy;

@Configuration
public class DatabaseConfiguration {


    public TransactionAwareConnectionFactoryProxy connectionFactory(ConnectionFactory connectionFactory) {
        return new TransactionAwareConnectionFactoryProxy(connectionFactory);
    }
}