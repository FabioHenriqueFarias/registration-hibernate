package org.example;

import jakarta.persistence.EntityManager;
import org.example.DataBase.Dao.CompanyDao;
import org.example.DataBase.Models.CompanyModel;
import org.example.DataBase.PersistenceManager;

public class ApplicationContext {



    public static CompanyDao getCompanyDao(){
        return new CompanyDao(getEntityManager());
    }

    public static CompanyModel getCompanyModel(){
        return new CompanyModel();
    }

    private static EntityManager getEntityManager(){
        return PersistenceManager.getEntityManager();
    }

}
