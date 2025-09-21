package DAO;

import java.util.List;
import model.Service;

public interface ServiceDao {
    void save(Service service);
    List<Service> show();
    void delete(String id);
    void update(Service service);
	List<Service> show1();
}