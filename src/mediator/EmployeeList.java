package mediator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeList {

    private List<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }

    public void populate() {
        employees.add(new Employee(239847, "John Doe", "Canada", "Alberta", "Edmonton", "1h2 h3g", "1000 This St"));
        employees.add(new Employee(348956, "Jane Doe", "Canada", "Ontario", "Cornwall", "2a3 b4c", "2000 That St"));
        employees.add(new Employee(798347, "Jannie Doe", "United Kingdom", "Doncaster", "Doncaster", "3d4 e5f", "3000 Another St"));
        employees.add(new Employee(893656, "Jean Doe", "United States", "Pennsylvania", "Lincoln", "34532", "4000 The Other St"));
    }

    public Employee find(int eid) {
        for (Employee emp: employees) {
            if (emp.getEid() == eid) {
                return emp;
            }
        }
        return null;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void modify(Employee employee) {
        Employee temp = find(employee.getEid());

        temp.setName(employee.getName());
        temp.setCountry(employee.getCountry());
        temp.setProv(employee.getProv());
        temp.setCity(employee.getCity());
        temp.setPostcode(employee.getPostcode());
        temp.setAddr(employee.getAddr());

    }

    public void remove(int eid) {
        Employee temp = find(eid);
        employees.remove(temp);
    }

    public void display() {
        for (Employee emp: employees) {
            System.out.println(emp.getEid() + "\t" + emp.getName());
            System.out.println(emp.getCountry() + "\t" + emp.getProv() + "\t" + emp.getCity());
            System.out.println(emp.getPostcode() + "\t" + emp.getAddr() + "\n\n");
        }
    }

    public List<Integer> getEidList() {
        List<Integer> eidList = new LinkedList<>();
        for (Employee employee: employees) {
            eidList.add(employee.getEid());
        }
        return eidList;
    }
}
