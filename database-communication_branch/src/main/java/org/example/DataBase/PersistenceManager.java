package org.example.DataBase;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import org.example.Server.ServerManager;

import java.util.logging.Logger;

public class PersistenceManager {

    //    EntityManagerFactory declaration
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            // Create an EntityManagerFactory using the persistence unit named "org.hibernate.tutorial.jpa".
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        } catch (PersistenceException error) {
            Logger.getLogger(ServerManager.class.getName()).severe(" :: " + "Error creating EntityManagerFactory: " + error.getMessage());
            throw new RuntimeException("Error during creating EntityManagerFactory", error);
        } catch (Exception error) {
            Logger.getLogger(ServerManager.class.getName()).severe(" :: " + "Error starting server: " + error.getMessage());
            throw new RuntimeException("Error when starting server", error);
        }
    }

    public static EntityManager getEntityManager() {

        // Retrieves an EntityManager instance from the EntityManagerFactory
        return entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManager(){

        // Closes the EntityManagerFactory
        entityManagerFactory.close();
    }

}
