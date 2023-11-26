package org.example.lesson_3.hw_3;

import org.example.lesson_3.hw_3.empoyee.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class EmployeeManagement implements Iterable<Employee>{

    private List<Employee> employeeList;

    public EmployeeManagement() {
        this.employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void addEmployees(Employee... employees) {
        Collections.addAll(employeeList, employees);
    }


    @Override
    public Iterator<Employee> iterator() {
        return new EmployeeIterator();
    }
    private class EmployeeIterator implements Iterator<Employee> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < employeeList.size();
        }

        @Override
        public Employee next() {
            return employeeList.get(currentIndex++);
        }
    }
}
