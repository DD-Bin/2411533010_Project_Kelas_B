package DAO;

import java.util.List;
import model.Customer;

public interface CustomerDao {
    void save(Customer customer);
    List<Customer> show();
    void delete(String id);
    void update(Customer customer);
}