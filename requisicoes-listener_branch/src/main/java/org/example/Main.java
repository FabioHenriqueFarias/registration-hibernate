package org.example;

<<<<<<< HEAD
import jakarta.persistence.EntityManager;
=======
>>>>>>> 27c5454 (update)
import org.example.DataBase.PersistenceManager;
import org.example.Server.ServerManager;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        try (EntityManager entityManager = PersistenceManager.getEntityManager()) {
=======
        try {
>>>>>>> 27c5454 (update)
            ServerManager.start();
        }
        catch (Exception error){
            Logger.getLogger(Main.class.getName()).severe(" :: " + "Error in application: " + error.getMessage());
<<<<<<< HEAD
        }finally {
=======
            ServerManager.stop();
>>>>>>> 27c5454 (update)
            PersistenceManager.closeEntityManager();
        }
    }
}