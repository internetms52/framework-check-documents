package io.github.internetms52.connection_factory;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Value;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.PostgresqlConnectionFactoryProvider;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

//@Factory
public class R2dbcPoolConfig {
    @Value("${r2dbc.datasources.default.database}")
    String database;
    @Value("${r2dbc.datasources.default.port}")
    String port;
    @Value("${r2dbc.datasources.default.host}")
    String host;
    @Value("${r2dbc.datasources.default.protocol}")
    String protocol;
    @Value("${r2dbc.datasources.default.username}")
    String username;
    @Value("${r2dbc.datasources.default.password}")
    String password;


//    @Singleton
//    @Replaces(ConnectionFactory.class)
//    @Named("default")
    ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(getOptions());
    }

    public ConnectionFactoryOptions getOptions() {

        return ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, 5432)
                .option(ConnectionFactoryOptions.DATABASE, database)
                .option(ConnectionFactoryOptions.USER, username)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .build();
    }
}
