package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Main {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* System.out.println(""); */
		File path = new File("C:\\Udemy Java\\funcChart.csv");
		System.out.println("=== RESEARCH SALARIES ===");
		System.out.println("= Research salaries above floor =");
		System.out.print("Type floor salary: ");
		Double floor = scan.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			List<Employee> employeeList = new ArrayList<>();
			
			while (line != null) {
				String[] sliced = line.split(",");
				employeeList.add(new Employee(sliced[0], sliced[1], Double.parseDouble(sliced[2])));
				line = br.readLine();
			}
			
			Comparator<Double> versus = (sal1, sal2) -> sal1.compareTo(sal2);
			
			List<String> aboveEarners = employeeList.stream()
										.filter(emp -> emp.getSalary() > floor)
										.map(emp -> emp.getEmail()).sorted()
										.collect(Collectors.toList());
			aboveEarners.forEach(System.out::println);
			
			Double sum = employeeList.stream().filter(emp -> emp.getName().charAt(0) == 'M')
									 .map(emp -> emp.getSalary())
									 .reduce(0.0, (sal1, sal2) -> sal1 + sal2);
			System.out.print("Sum of salaries whose employee's name starts with 'M': " + sum);
		}
		catch (IOException error) {
			System.out.println("Error: " + error.getMessage());
		}

	}
}