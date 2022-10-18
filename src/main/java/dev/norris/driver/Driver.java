package dev.norris.driver;

import io.javalin.Javalin;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hellow World")).start(7070);
        Driver driver = new Driver();
        driver.entryMenu(driver);

    }

    private void entryMenu(Driver driver) {
        Scanner userIn = new Scanner(System.in);
        boolean correctChoice = false;
        while(!correctChoice){
            System.out.println("Entry Menu");
            System.out.println("1. Login");
            System.out.println("2. Register");

            String menuChoice = userIn.next();
            if(menuChoice.equals("1")){
                driver.loginMenu(driver);
            } else if (menuChoice.equals("2")) {
                System.out.println("registering");
                correctChoice = true;
            }else{
                System.out.println("Invalid Entry!");
            }
        }
    }

    private void loginMenu(Driver driver){
        Scanner userIn = new Scanner(System.in);
        System.out.print("Username: ");
        String userNameInput = userIn.next();
        System.out.print("Password: ");
        String passwordInput = userIn.next();
        //DB poll goes here, and would set userExists, for now, assume it's correct
        boolean userExists = true;
        if(userExists){
            //Here, it should pass the Employee entry the DB
            System.out.println("Welcome, " + userNameInput + "!");
            driver.mainMenu(driver);
        }else{
            driver.entryMenu(driver);
        }
    }

    private void mainMenu(Driver driver){
        Scanner userIn = new Scanner(System.in);
        //Usage of isManager can be replaced with calling .getManager() when implemented
        boolean isManager = false;
        boolean correctChoice = false;
        while(!correctChoice){
            System.out.println("Main Menu");
            //Here, we'd give a different option if the Employee was a manager
            if(isManager){
                System.out.println("1. View Pending Requests");
                System.out.println("2. Log Out");
            }else{
                System.out.println("1. Submit Request");
                System.out.println("2. View Sent Requests");
                System.out.println("3. Log Out");
            }

            String menuChoice = userIn.next();
            if(menuChoice.equals("1")){
                if(isManager){
                    driver.approvalMenu(driver);
                }else{
                    driver.submitMenu(driver);
                }
            }else if(menuChoice.equals("2")){
                if(isManager){
                    driver.entryMenu(driver);
                }else{
                    driver.pastRequestMenu(driver);
                }
            }else if(menuChoice.equals("3") && isManager){
                driver.entryMenu(driver);
            }else{
                System.out.println("Invalid Entry!");
            }
        }
    }

    private void approvalMenu(Driver driver){
        Scanner userIn = new Scanner(System.in);
        boolean correctChoice = false;
        //Here we'd get all tickets with status "pending"
        String[] tickets = {"guy","gal","goon"};
        while(!correctChoice){
            System.out.println("Pending Requests:");
            for(int i = 0; i < tickets.length; i++){
                System.out.println((i+1) + ". " + tickets[i]);
            }
            System.out.println((tickets.length+1) + ". Back to Menu");
            String menuChoice = userIn.next();
            int intChoice = -1;
            try{
                intChoice = Integer.parseInt(menuChoice);
            }
            catch(Exception e){
                System.out.println("Invalid Entry!");
            }
            if(intChoice <= tickets.length && intChoice != -1){
                //make this more verbose and display more info
                boolean approvalChoice = false;
                while(!approvalChoice){
                    System.out.println("Ticket: " + tickets[intChoice-1]);
                    System.out.println("1. Approve");
                    System.out.println("2. Deny");
                    System.out.println("3. Back");
                    String approvalInput = userIn.next();
                    //Obviously, these would have DB functions
                    switch(approvalInput){
                        case "1":
                            System.out.println("Approved!");
                            approvalChoice = true;
                            break;
                        case "2":
                            System.out.println("Denied!");
                            approvalChoice = true;
                            break;
                        case "3":
                            approvalChoice = true;
                            break;
                        default:
                            System.out.println("Invalid Entry!");
                    }
                }
            } else if (intChoice == tickets.length+1) {
                driver.mainMenu(driver);
            }else if(intChoice == -1){
                System.out.println("Ticket does not exist!");
            }
        }
    }

    private void submitMenu(Driver driver){
        Scanner userIn = new Scanner(System.in);
        System.out.print("Amount: ");
        String amountInput = userIn.next();
        System.out.print("Description: ");
        String descriptionInput = userIn.next();
        //Send a new ticket to DB along with userID and pending status
        System.out.println("Submitted!");
        driver.mainMenu(driver);
    }

    private void pastRequestMenu(Driver driver){
        Scanner userIn = new Scanner(System.in);
        boolean correctChoice = false;
        //Here we'd get all tickets with status "pending"
        String[] tickets = {"guy","gal","goon"};
        while(!correctChoice){
            String filter = "None";
            //here, we'd put an If that makes a certain DB request based on filter
            System.out.println("Pending Requests:");
            for(int i = 0; i < tickets.length; i++){
                System.out.println((i+1) + ". " + tickets[i]);
            }
            System.out.println((tickets.length+1) + ". Filter Results");
            System.out.println((tickets.length+2) + ". Back to Menu");
            String menuChoice = userIn.next();
            int intChoice = -1;
            try{
                intChoice = Integer.parseInt(menuChoice);
            }
            catch(Exception e){
                System.out.println("Invalid Entry!");
            }
            if(intChoice <= tickets.length && intChoice != -1){
                //make this more verbose and display more info
                boolean approvalChoice = false;
                while(!approvalChoice){
                    System.out.println("Ticket: " + tickets[intChoice-1]);
                    System.out.println("1. Back");
                    String approvalInput = userIn.next();
                    //Obviously, these would have DB functions
                    if(approvalInput.equals("1")){
                        approvalChoice = true;
                    } else{
                        System.out.println("Invalid Entry!");
                    }
                }
            } else if (intChoice == tickets.length+1) {
                boolean approvalChoice = false;
                while(!approvalChoice) {
                    System.out.println("Filter:");
                    System.out.println("1. Pending");
                    System.out.println("2. Approved");
                    System.out.println("3. Denied");
                    System.out.println("4. Back");
                    String approvalInput = userIn.next();
                    //Obviously, these would have DB functions
                    switch (approvalInput) {
                        case "1":
                            filter = "Pending";
                            approvalChoice = true;
                            break;
                        case "2":
                            filter = "Approved";
                            approvalChoice = true;
                            break;
                        case "3":
                            filter = "Denied";
                            approvalChoice = true;
                            break;
                        case "4":
                            approvalChoice = true;
                            break;
                        default:
                            System.out.println("Invalid Entry!");
                    }
                }
            } else if (intChoice == tickets.length+2) {
                driver.mainMenu(driver);
            }else if(intChoice == -1){
                System.out.println("Ticket does not exist!");
            }
        }
    }

    private void registerMenu(Driver driver){
        Scanner userIn = new Scanner(System.in);
        System.out.print("Username: ");
        String userNameInput = userIn.next();
        System.out.print("Password: ");
        String passwordInput = userIn.next();
        //DB poll goes here, and would set userExists, for now, assume it's correct
        boolean userExists = true;
        if(!userExists){
            //Here, it should pass the Employee entry the DB
            System.out.println("Account Created!");
            driver.entryMenu(driver);
        }else{
            System.out.println("Account already exists!");
            driver.entryMenu(driver);
        }
    }
}
