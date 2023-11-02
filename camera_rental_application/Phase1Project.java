package com.java.phaseone;
import java.util.ArrayList;
import java.util.Scanner;

public class Phase1Project {
    private static ArrayList<Camera> cameraList = new ArrayList<>();
    private static double walletBalance = 1000;
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login(scanner);
        scanner.close();
    }
    
    private static void login(Scanner scanner) {
    	System.out.println("**************************************************");
        System.out.println("* Welcome to the Camera Rental Application       *");
        System.out.println("* Developer: Nithish                             *");
        System.out.println("**************************************************");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (username.equals("admin") && password.equals("admin@123")) {
            System.out.println("Login Successful");
            initializeCameraList();
            displayOptions(scanner);
        } else {
            System.out.println("Authentication Failed");
        }
        System.out.println("Thank you for using the Camera Rental Application");
    }
    
    private static void initializeCameraList() {
        cameraList.add(new Camera(1, "Canon", "DSLR", 1000, true));
        cameraList.add(new Camera(2, "Nikon", "Coolpix", 800, true));
        cameraList.add(new Camera(3, "Sony", "Alpha", 1200, true));
        cameraList.add(new Camera(4, "Fujifilm", "X-T4", 1500, true));
        cameraList.add(new Camera(5, "Panasonic", "Lumix", 900, true));
        cameraList.add(new Camera(6, "Leica", "M10", 3000, true));
        cameraList.add(new Camera(7, "Olympus", "OM-D E-M1", 1300, true));
        cameraList.add(new Camera(8, "Pentax", "K-1 Mark II", 1800, true));
        }
    
        

        
    
    private static void displayOptions(Scanner scanner) {
        int choice;
        do {
            System.out.println("1. Manage My Cameras");
            System.out.println("2. Rent a Camera");
            System.out.println("3. View All Cameras");
            System.out.println("4. Manage My Wallet");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    manageMyCameras(scanner);
                    break;
                case 2:
                    rentCamera(scanner);
                    break;
                case 3:
                    viewAllCameras();
                    break;
                case 4:
                    manageWallet(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (true);
    }
    private static void manageMyCameras(Scanner scanner) {
        int choice;
        do {
            System.out.println("1. Add Camera");
            System.out.println("2. Remove Camera");
            System.out.println("3. View My Cameras");
            System.out.println("4. Go to Previous Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Camera ID: ");
                    int cameraId = scanner.nextInt();
                    System.out.print("Enter Camera Brand: ");
                    String brand = scanner.next();
                    System.out.print("Enter Camera Model: ");
                    String model = scanner.next();
                    System.out.print("Enter Camera Price per day: ");
                    double price = scanner.nextDouble();
                    boolean isAvailable = true;
                    cameraList.add(new Camera(cameraId, brand, model, price, isAvailable));
                    System.out.println("Camera successfully added.");
                    break;
                case 2:
                    System.out.print("Enter Camera ID to remove: ");
                    int removeId = scanner.nextInt();
                    boolean isRemoved = false;
                    for (Camera camera : cameraList) {
                        if (camera.getId() == removeId) {
                            cameraList.remove(camera);
                            isRemoved = true;
                            break;
                        }
                    }
                    if (isRemoved) {
                        System.out.println("Camera successfully removed.");
                    } else {
                        System.out.println("Camera not found with given ID.");
                    }
                    break;
                case 3:
                	System.out.println("My Cameras:");
                    System.out.println("-------------------------------------------------------------");
                    System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s |%n",
                            "Camera ID", "Brand", "Model", "Price", "Availability");
                    System.out.println("-------------------------------------------------------------");
                    for (Camera camera : cameraList) {
                        if (!camera.isAvailable()) {
                            System.out.format("| %-10d | %-15s | %-15s | %-10.2f | %-10s |%n",
                                    camera.getId(), camera.getBrand(), camera.getModel(), camera.getPrice(),
                                    camera.isAvailable() ? "Available" : "Rented");
                        }
                    }
                    System.out.println("-------------------------------------------------------------");
          
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (true);
    }
    
    private static void rentCamera(Scanner scanner) {
        System.out.println("Available Cameras for Rent:");
        for (Camera camera : cameraList) {
            if (camera.isAvailable()) {
                System.out.println("Camera ID: " + camera.getId() + ", Brand: " + camera.getBrand() +
                        ", Model: " + camera.getModel() + ", Price: " + camera.getPrice());
            }
        }
        System.out.print("Enter the Camera ID you want to rent: ");
        int rentId = scanner.nextInt();
        boolean isFound = false;
        for (Camera camera : cameraList) {
            if (camera.getId() == rentId && camera.isAvailable()) {
                if (walletBalance >= camera.getPrice()) {
                    camera.setAvailable(false);
                    walletBalance -= camera.getPrice();
                    System.out.println("Camera rented successfully. Your current wallet balance: " + walletBalance);
                } else {
                    System.out.println("You don't have sufficient balance in your wallet to rent this camera.");
                }
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Camera not available for rent with the given ID.");
        }
    }
    
    private static void viewAllCameras() {
    	System.out.println("List of all available cameras:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s |%n",
                "Camera ID", "Brand", "Model", "Price", "Availability");
        System.out.println("-------------------------------------------------------------");
        for (Camera camera : cameraList) {
            System.out.format("| %-10d | %-15s | %-15s | %-10.2f | %-10s |%n",
                    camera.getId(), camera.getBrand(), camera.getModel(), camera.getPrice(),
                    camera.isAvailable() ? "Available" : "Rented");
        }
        System.out.println("-------------------------------------------------------------");
    }
    
    private static void manageWallet(Scanner scanner) {
        System.out.println("Your current wallet balance is: " + walletBalance);
        System.out.print("Do you want to deposit more amount to your wallet? (1. Yes 2. No): ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("Enter deposit amount: ");
            double addAmount = scanner.nextDouble();
            walletBalance += addAmount;
            System.out.println("Your wallet balance has been updated successfully. Current balance: " + walletBalance);
        }
    }
}

    
    





