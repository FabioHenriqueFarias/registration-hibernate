package org.example.Server;

import com.sun.net.httpserver.HttpServer;
import org.example.Server.Endpoints.CompanyHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class ServerManager {
    static final HttpServer HTTP_SERVER;
    static final int PORT = 3000;

    static {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", PORT);
            HTTP_SERVER = HttpServer.create(address, 0);

        }catch (IOException error){
            Logger.getLogger(ServerManager.class.getName()).severe(" :: " + "Error creating server: " + error.getMessage());
            throw new RuntimeException("Error during creating server", error);
        }catch (Exception error){
            Logger.getLogger(ServerManager.class.getName()).severe(" :: " + "Error starting server: " + error.getMessage());
            throw new RuntimeException("Error when starting server", error);
        }
    }

    public static void start(){
        HTTP_SERVER.createContext("/company", new CompanyHandler());

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        HTTP_SERVER.setExecutor(threadPoolExecutor);

        HTTP_SERVER.start();
    }

    public static void stop(){
        HTTP_SERVER.stop(0);
    }
}

