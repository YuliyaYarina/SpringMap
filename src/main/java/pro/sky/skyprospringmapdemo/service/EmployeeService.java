package pro.sky.skyprospringmapdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringmapdemo.exception.EmployeeAlreadyAddedException;
import pro.sky.skyprospringmapdemo.exception.EmployeeNotFoundException;
import pro.sky.skyprospringmapdemo.exception.EmployeeStorageIsFullException;

import java.util.*;




@Service
public class EmployeeService {

    private final Map<String, Employee> employeesBuFullName = new HashMap<>();
    private final List<Employee> employees = new ArrayList<>();
    private final int MAX_SIZE = 2;

//    public Employee getWithMaxSalary() {
//        return employees.stream()
//                .max(Comparator.comparing(Employee::getSalary))
//                .orElseThrow(() -> new EmployeeNotFoundException("Ошибка"));
//    }

    public  Employee add(String firstName, String lastName){
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmloyee = new Employee(firstName, lastName);

        String fullName = getFullName(newEmloyee);

        if (employeesBuFullName.containsKey(fullName)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + newEmloyee + " уже существует");
        }
        employeesBuFullName.put(fullName,newEmloyee);
        return newEmloyee;
    }

    public Employee find(String firstName, String lastName){
        Employee employeeForFind = new Employee(firstName,lastName);

        String fullName = getFullName(employeeForFind);

        if (!employeesBuFullName.containsKey(fullName)) {
            throw  new EmployeeNotFoundException("Такого сотрудника нет");
        }

        return employeesBuFullName.get(fullName);
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);

        if (!employees.contains(employeeForRemove)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }

        employees.remove(employeeForRemove);
        return employeeForRemove;
    }

    public Collection<Employee> getAll(){
        return employeesBuFullName.values();
    }

    private String getFullName(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
    private void chekExistence(String fullName) {
        if (!employeesBuFullName.containsKey(fullName)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }
    }
}
