package DAO;

import Model.Manager;

import java.util.ArrayList;

public interface IManagerDAO {

    Manager findByUsername(String username);
    ArrayList<Manager> findAll();
    int addById(Manager manager,int id);
    int removeById(int id);
    int update(Manager manager);
}
