package org.example.Server.Endpoints;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
<<<<<<< HEAD

import java.io.IOException;

public class CompanyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())){

        }
        if("POST".equals(exchange.getRequestMethod())){

        }
    }
=======
import org.example.ApplicationContext;
import org.example.DataBase.Models.CompanyModel;
import org.example.Main;


import javax.json.*;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CompanyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {

        // Get the request body
        String requestBody = readRequestBody(exchange);

        // Extract the request method (GET or POST)
        String requestMethod = exchange.getRequestMethod();



        // Check the request method and call appropriate method
        if ("GET".equals(requestMethod)) {
            methodGet(exchange);
        }
        if ("POST".equals(requestMethod)) {
            methodPost(requestBody, exchange);
        }

    }


    public static void methodGet(HttpExchange exchange){

        try {
            List<CompanyModel> companies = ApplicationContext.getCompanyDao().getAll();

            JsonArray jsonArray = companies.stream()
                    .map(CompanyHandler::toJson)
                    .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::addAll)
                    .build();

            sendResponse(exchange, 200, jsonArray.toString());
        }catch (Exception error){
            Logger.getLogger(Main.class.getName()).severe(" :: " + "Error in get request: " + error.getMessage());
            sendResponse(exchange, 500, "Error!");
        }

    }

    public static void methodPost(String requestBody,HttpExchange exchange) {
        try {

            JsonReader jsonReader = Json.createReader(new StringReader(requestBody));
            JsonObject json = jsonReader.readObject();

            if (!json.containsKey("ComapanyName") || !json.containsKey("Email")) {
                sendResponse(exchange, 400, "Invalid company name or email");
                return;
            }

            String companyName = json.getString("ComapanyName");
            String email = json.getString("Email");

            if(companyName == null || email == null) {
                sendResponse(exchange, 400, "Invalid company name or email");
                return;
            }

            CompanyModel companyModel = ApplicationContext.getCompanyModel();

            companyModel.setComapanyName(companyName);
            companyModel.setEmail(email);

            ApplicationContext.getCompanyDao().save(companyModel);

            sendResponse(exchange, 200, "Success!");

        }catch (Exception error){
            Logger.getLogger(Main.class.getName()).severe(" :: " + "Error in read request and Post in Database: " + error.getMessage());
            sendResponse(exchange, 400, "Error!");
        }
    }

    private static JsonObject toJson(CompanyModel companyModel) {

        // Converts a CompanyModel object to a JSON object
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        if (companyModel.getComapanyName() != null) {
            jsonBuilder.add("CompanyName", companyModel.getComapanyName());
        } else {
            jsonBuilder.addNull("CompanyName");
        }

        if (companyModel.getEmail() != null) {
            jsonBuilder.add("Email", companyModel.getEmail());
        } else {
            jsonBuilder.addNull("Email");
        }

        return jsonBuilder.build();
    }

    private static String readRequestBody(HttpExchange exchange){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            // Log the exception for tracking
            Logger.getLogger(CompanyHandler.class.getName()).severe("Erro ao ler o corpo da requisição: " + e.getMessage());

            // Throw a  exception
            throw new RuntimeException("Erro ao ler o corpo da requisição", e);
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) {

        try {
            // Configure response headers
            exchange.sendResponseHeaders(statusCode, response.length());
            OutputStream os = exchange.getResponseBody();

            // Write the response body
            os.write(response.getBytes());

            // Close the output stream and complete the response
            os.close();
        } catch (IOException error){
            Logger.getLogger(CompanyHandler.class.getName()).severe(" :: " + "Error sending request: " + error.getMessage());
        }

    }
>>>>>>> 27c5454 (update)
}
