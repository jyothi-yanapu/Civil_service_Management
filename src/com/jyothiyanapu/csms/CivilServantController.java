package com.jyothiyanapu.csms;

import java.util.Scanner;
import java.util.List;

public class CivilServantController {
    private final CivilServantService service;
    private final Scanner scanner;

    public CivilServantController (){
        service = new CivilServantService();
        scanner = new Scanner(System.in);

    }
    public void start() {
        int choice = -1;

        while (choice != 5) {
            showMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addCivilServant();
                    break;
                case 2:
                    viewAllCivilServants();
                    break;
                case 3:
                    findById();
                    break;
                case 4:
                    deleteById();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n===== Civil Service Management System =====");
        System.out.println("1. Add Civil Servant");
        System.out.println("2. View All Civil Servants");
        System.out.println("3. Find Civil Servant by ID");
        System.out.println("4. Delete Civil Servant by ID");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a new civil servant
    private void addCivilServant() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        CivilServant cs = new CivilServant(id, name, department, designation, salary);
        service.addCivilServant(cs);

        System.out.println("Civil Servant added successfully!");
    }

    // View all civil servants
    private void viewAllCivilServants() {
        List<CivilServant> civilServants = service.getCivilServants();
        if (civilServants.isEmpty()) {
            System.out.println("No civil servants found.");
        } else {
            System.out.println("\nList of Civil Servants:");
            for (CivilServant cs : civilServants) {
                System.out.println(cs);
            }
        }
    }

    // Find a civil servant by ID
    private void findById() {
        System.out.print("Enter ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());

        CivilServant cs = service.getCivilServantById(id);
        if (cs != null) {
            System.out.println("Civil Servant found: " + cs);
        } else {
            System.out.println("Civil Servant with ID " + id + " not found.");
        }
    }


    // Delete a civil servant by ID
    private void deleteById() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean removed = service.deleteCivilServantById(id);
        if (removed) {
            System.out.println("Civil Servant deleted successfully.");
        } else {
            System.out.println("Civil Servant with ID " + id + " not found.");
        }
    }

}
