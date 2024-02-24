package org.example.DataBase.Dao;

import jakarta.persistence.PersistenceException;
import org.example.DataBase.Models.CompanyModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.logging.Logger;

public class CompanyDao {
    private final EntityManager entityManager;

    public CompanyDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(CompanyModel company) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(company);
            transaction.commit();
        } catch (PersistenceException error) {
            Logger.getLogger(CompanyDao.class.getName()).severe(" :: " + "Failure to save a company entity\n" + error.getMessage());
        } catch (Exception error) {
            Logger.getLogger(CompanyDao.class.getName()).severe(" :: " + "Failure to save a company entity, UNKNOWN ERROR\n" + error.getMessage());
            throw new RuntimeException("Error during creating server", error);
        }
    }
}
