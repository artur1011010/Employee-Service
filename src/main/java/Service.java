import java.util.List;

public class Service {
    EmployeeDAO employeeDAO = new EmployeeDAO();

    public void checkAddEmployee() {
        Employee employee = new Employee();
        employee.setName("Jola");
        employee.setLastName("Bednarek");
        employee.setAge(55);
        employee.setEmail("jola@o2.pl");
        employeeDAO.addEmployee(employee);

    }

    public void checkFindEmployeeMethod() {
        System.out.println(employeeDAO.findEmployee(1).toString());
    }

    public void checkUpdateEmployeeMethod() {
        employeeDAO.updateEmployee(1, "Testowy Marek");
    }

    public void checkAddDepartmentMethod() {
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setDepartmentLocation("Lodz");
        employeeDAO.addDepartment(department);
    }

    public void checkFindDepartment() {
        System.out.println(employeeDAO.findDepartment(2).toString());
    }

    public void checkSetDepartmentToExistingEmployee() {
        employeeDAO.setDepartmentToExistingEmployee(3, 4);
    }

    public void checkEmployeeListByDepartment() {
        List<Employee> list = employeeDAO.getEmployeeListByDepartment(2);
        list.forEach(a -> System.out.println(a.toString()));
    }

    public void chcekAllEmployeeList() {
        List<Employee> list = employeeDAO.getEmployeeList();
        list.forEach(a -> System.out.println(a.toString()));
    }

    public void checkEmployeeAfterDate() {
        List<Employee> list = employeeDAO.getEmployeeListWithDate("2019-01-17");
        list.forEach(a -> System.out.println(a.toString()));
    }

}
