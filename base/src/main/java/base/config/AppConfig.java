package base.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class AppConfig {
    @Bean
    public WebClient configWebClient() {
        HttpClient httpClient = HttpClient.create(ConnectionProvider.create("myConnectionPool"))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 180000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(30))
                        .addHandlerLast(new WriteTimeoutHandler(30)));
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .clientConnector(connector)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
