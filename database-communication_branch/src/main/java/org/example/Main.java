package org.example;

import jakarta.persistence.EntityManager;
import org.example.DataBase.PersistenceManager;
import org.example.Server.ServerManager;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try (EntityManager entityManager = PersistenceManager.getEntityManager()) {
            ServerManager.start();
        }
        catch (Exception error){
            Logger.getLogger(Main.class.getName()).severe(" :: " + "Error in application: " + error.getMessage());
        }finally {
            PersistenceManager.closeEntityManager();
        }
    }
}