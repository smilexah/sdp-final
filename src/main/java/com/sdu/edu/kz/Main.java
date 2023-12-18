//package com.sdu.edu.kz;
//
//import com.sdu.edu.kz.adapter.CreditCardPayment;
//import com.sdu.edu.kz.adapter.KaspiPayment;
//import com.sdu.edu.kz.adapter.PaymentSystem;
//import com.sdu.edu.kz.booking.Booking;
//import com.sdu.edu.kz.db.DatabaseManager;
//import com.sdu.edu.kz.db.User;
//import com.sdu.edu.kz.decorator.BreakfastDecorator;
//import com.sdu.edu.kz.decorator.ExcursionDecorator;
//import com.sdu.edu.kz.decorator.TransferDecorator;
//import com.sdu.edu.kz.factory.*;
//import com.sdu.edu.kz.singleton.HotelManager;
//import com.sdu.edu.kz.strategy.DiscountPricingStrategy;
//import com.sdu.edu.kz.strategy.LastMinurePricingStrategy;
//import com.sdu.edu.kz.strategy.PricingStrategy;
//import com.sdu.edu.kz.strategy.StandardPricingStrategy;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//
//import java.util.Scanner;
//
//@WebServlet("/main")
//public class Main extends HttpServlet {
//    private static HotelManager hotelManager = HotelManager.getInstance();
//    private static DatabaseManager db = DatabaseManager.getInstance();
//
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("Welcome to our hotel");
//            System.out.println("Please choose your role:");
//            System.out.println("1. User");
//            System.out.println("2. Admin");
//            System.out.println("3. Exit");
//
//            int roleChoice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (roleChoice) {
//                case 1 -> handleUser(scanner, hotelManager, db);
//                case 2 -> handleAdmin(db);
//                case 3 -> {
//                    System.out.println("Exiting the program. Thank you!");
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Please enter '1', '2', or '3'.");
//            }
//        }
//    }
//
//    private static void handleUser(Scanner scanner, HotelManager hotelManager, DatabaseManager db) {
//        while (true) {
//            System.out.println("Please choose which room you want?");
//            System.out.println("Select a room type:");
//            System.out.println("1. Family Room-35000KZT");
//            System.out.println("2. Luxury Room-50000KZT");
//            System.out.println("3. Standard Room-20000KZT");
//            System.out.println("4. None (to exit)");
//
//            int roomChoice = scanner.nextInt();
//            scanner.nextLine();
//
//            if (roomChoice == 4) {
//                System.out.println("Exiting user session. Returning to the main menu.");
//                break;
//            }
//
//            RoomFactory roomFactory;
//
//            switch (roomChoice) {
//                case 1 -> roomFactory = new FamilyRoomFactory();
//                case 2 -> roomFactory = new LuxuryRoomFactory();
//                case 3 -> roomFactory = new StandardRoomFactory();
//                default -> {
//                    System.out.println("Invalid room choice. Please choose again.");
//                    continue;
//                }
//            }
//
//            Room room = roomFactory.createRoom();
//
//            while (true) {
//                System.out.println("Select some pluses for your room:");
//                System.out.println("1. Breakfast-5000KZT");
//                System.out.println("2. Excursion-10000KZT");
//                System.out.println("3. Transfer-10000KZT");
//                System.out.println("4. None (to finish booking)");
//
//                int addChoice = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (addChoice) {
//                    case 1:
//                        room = new BreakfastDecorator(room);
//                        break;
//                    case 2:
//                        room = new ExcursionDecorator(room);
//                        break;
//                    case 3:
//                        room = new TransferDecorator(room);
//                        break;
//                    case 4:
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please choose again.");
//                        continue;
//                }
//                break;
//            }
//
//            System.out.println("Enter the number of days:");
//            int numberOfDays = scanner.nextInt();
//            scanner.nextLine();
//
//            System.out.println("Do you have an existing account?");
//            System.out.println("1. Yes");
//            System.out.println("2. No");
//
//            int response = scanner.nextInt();
//            scanner.nextLine();
//
//            String userName;
//
//            if (response == 1) {
//                System.out.println("Enter your user name:");
//                userName = scanner.nextLine();
//
//                User existingUser = db.getUserByName(userName);
//
//                if (existingUser != null) {
//                    System.out.println("User found: " + existingUser);
//                    db.addBooking(room.getCost(), db.addUser(userName, db.getPasswordByUsername(userName)), "Booking for " + userName);
//                } else {
//                    System.out.println("User not found. Please create a new account.");
//                    System.out.println("Enter user name:");
//                    userName = scanner.nextLine();
//                    System.out.println("Enter user password:");
//                    String password = scanner.nextLine();
//                    db.addBooking(room.getCost(), db.addUser(userName, password), "Booking for " + userName);
//                }
//            } else if (response == 2) {
//                System.out.println("Enter user name:");
//                userName = scanner.nextLine();
//                System.out.println("Enter user password:");
//                String password = scanner.nextLine();
//                db.addBooking(room.getCost(), db.addUser(userName, password), "Booking for " + userName);
//            } else {
//                System.out.println("Invalid response. Please enter '1' or '2'.");
//                continue;
//            }
//
//            System.out.println("Enter your name:");
//            String clientName = scanner.nextLine();
//
//            PricingStrategy pricingStrategy;
//
//            while (true) {
//                System.out.println("Pricing:");
//                System.out.println("1. Standard Pricing");
//                System.out.println("2. Discount Pricing");
//                System.out.println("3. Last Minute Pricing");
//
//                int strategyChoice = scanner.nextInt();
//
//                switch (strategyChoice) {
//                    case 1 -> pricingStrategy = new StandardPricingStrategy();
//                    case 2 -> pricingStrategy = new DiscountPricingStrategy();
//                    case 3 -> pricingStrategy = new LastMinurePricingStrategy();
//                    default -> {
//                        System.out.println("Invalid pricing strategy choice. Please choose again.");
//                        continue;
//                    }
//                }
//                break;
//            }
//
//            PaymentSystem paymentSystem;
//
//            while (true) {
//                System.out.println("Payment type:");
//                System.out.println("1. Kaspi");
//                System.out.println("2. CreditCard");
//
//                int paymentChoice = scanner.nextInt();
//
//                switch (paymentChoice) {
//                    case 1 -> paymentSystem = new KaspiPayment();
//                    case 2 -> paymentSystem = new CreditCardPayment();
//                    default -> {
//                        System.out.println("Invalid pricing strategy choice. Please choose again.");
//                        continue;
//                    }
//                }
//                break;
//            }
//
//            Booking booking = new Booking(room, pricingStrategy, clientName, paymentSystem, numberOfDays);
//            hotelManager.BookRoom(booking);
//
//            System.out.println("Booking Information:");
//            System.out.println("--------------------");
//            booking.update("aa");
//            System.out.println();
//            booking.check();
//            db.showBookingsForUserName(userName);
//
//            System.out.println("Do you want to make another booking?");
//            System.out.println("1. Yes");
//            System.out.println("2. No");
//
//            int continueChoice = scanner.nextInt();
//
//            if (continueChoice != 1) {
//                System.out.println("Exiting user session. Returning to the main menu.");
//                break;
//            }
//        }
//    }
//
//    private static void handleAdmin(DatabaseManager db) {
//        while (true) {
//            System.out.println("Admin access - Options:");
//            System.out.println("1. View Database");
//            System.out.println("2. Back to Main Menu");
//            System.out.println("3. Trigger fire alarm");
//
//
//            Scanner adminScanner = new Scanner(System.in);
//            int adminChoice = adminScanner.nextInt();
//            adminScanner.nextLine();
//
//            switch (adminChoice) {
//                case 1 -> db.showAllBookings();
//                case 2 -> {
//                    System.out.println("Exiting admin session. Returning to the main menu.");
//                    return;
//                }
//                case 3 -> hotelManager.FireAlarm();
//                default -> System.out.println("Invalid choice. Please enter '1' or '2' or '3'.");
//            }
//        }
//    }
//}
//
//
