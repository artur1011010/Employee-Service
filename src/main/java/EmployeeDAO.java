import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("unit1");

    public void updateEmployee(Integer employeeId, String newName) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Employee employee = findEmployee(employeeId);
        employee.setName(newName);
        entityManager.getTransaction().begin();
        entityManager.merge(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void addEmployee(Employee employee) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        employee.setAddedDate(Date.valueOf(LocalDate.now()));
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employee findEmployee(Integer employeeId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Long longId = Long.parseLong(employeeId + "");
        Employee employee = entityManager.find(Employee.class, longId);
        entityManager.close();
        return employee;
    }

    public void addDepartment(Department department) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Department findDepartment(Integer departmentId) {
        Long longDepartmentId = Long.parseLong(departmentId + "");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Department department = entityManager.find(Department.class, longDepartmentId);
        return department;
    }

    public void setDepartmentToExistingEmployee(Integer employeeId, Integer departmentId) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Employee employee = findEmployee(employeeId);
        Department department = findDepartment(departmentId);
        employee.setDepartment(department);
        entityManager.getTransaction().begin();
        entityManager.merge(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Employee> getEmployeeListByDepartment(Integer departmentId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Department department = findDepartment(departmentId);
        List<Employee> employeeList = department.getEmployeeList();
        return employeeList;
    }

    public List<Employee> getEmployeeList() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String jpql = "SELECT e FROM Employee e";
        List<Employee> list = entityManager.createQuery(jpql, Employee.class).getResultList();
        return list;
    }

    public List<Employee> getEmployeeListWithDate(String date) {
        java.util.Date date1 = Date.valueOf(date);
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String jpql = "SELECT e FROM Employee e WHERE e.addedDate > :date";
        List<Employee> employeeList = entityManager.createQuery(jpql, Employee.class).setParameter("date", date1).getResultList();
        return employeeList;
    }
}
