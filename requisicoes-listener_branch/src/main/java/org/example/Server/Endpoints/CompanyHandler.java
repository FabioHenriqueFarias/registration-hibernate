package org.example.Server.Endpoints;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class CompanyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())){

        }
        if("POST".equals(exchange.getRequestMethod())){

        }
    }
}
