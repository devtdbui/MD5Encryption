
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author devtd
 */
public class ProgramManagement {

    ArrayList<Account> list = new ArrayList<>();

    public static void main(String[] args) {

        ProgramManagement pm = new ProgramManagement();
        Scanner sc = new Scanner(System.in);
        Validation v = new Validation();
        while (true) {
            System.out.print("============ Login Program =========\n"
                    + "1. Add User\n"
                    + "2. Login\n"
                    + "3) Exit\n");
            int choice = v.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("---------- Add User --------");
                    pm.addAccount(pm.list);
                    break;
                case 2:
                    System.out.println("------------- Login ----------------");
                    pm.login(pm.list);
                    break;
                case 3:
                    System.exit(0);
            }
        }

    }

    public void addAccount(ArrayList<Account> list) {
        Validation v = new Validation();
        System.out.print("Account: ");
        String username = v.checkString();
        System.out.print("Password: ");
        String password = v.checkString();
        System.out.print("Name: ");
        String name = v.checkString();
        System.out.print("Phone: ");
        String phone = v.getPhone();
        System.out.print("Email: ");
        String email = v.getEmail();
        System.out.print("Address: ");
        String address = v.checkString();
        System.out.print("DOB: ");
        String dateOfBirth = v.getDate();
        list.add(new Account(username, v.MD5Encryption(password), name, phone, email, address, dateOfBirth));
        System.out.println("Add success!!!");
    }

    public void login(ArrayList<Account> list) {
        Validation v = new Validation();
        if (list.isEmpty()) {
            System.err.println("Accout empty.");
            return;
        }
        System.out.print("Enter username: ");
        String username = v.checkString();
        System.out.print("Enter Password: ");
        String password = v.checkString();
        Account accoutLogin = findAccount(list, username, password);
        if (accoutLogin != null) {
            System.out.println("Wellcome");
            System.out.print("Hi " + accoutLogin.getUsername()
                    + ", do you want chage password now? Y/N: ");
            changePassword(accoutLogin);
        } else {
            System.err.println("Invalid username or password.");
        }
    }

    public Account findAccount(ArrayList<Account> la, String username, String password) {
        Validation v = new Validation();
        for (int i = 0; i < la.size(); i++) {
            if (username.equalsIgnoreCase(la.get(i).getUsername())) {
                if (v.MD5Encryption(password).equalsIgnoreCase(la.get(i).getPassword())) {
                    return la.get(i);
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void changePassword(Account accoutLogin) {
        Scanner sc = new Scanner(System.in);
        Validation v = new Validation();
        String choice;
        while (true) {
            choice = sc.nextLine().trim();
            if (choice.length() == 0) {
                System.err.println("Not empty!!!");
            } else if (choice.length() == 1 && choice.equalsIgnoreCase("Y")
                    || choice.equalsIgnoreCase("N")) {
                break;
            } else {
                System.err.println("Re-input");
            }
        }
        if (choice.equalsIgnoreCase("Y")) {
            System.out.print("Old password: ");
            String oldPassword = v.checkString();
            System.out.print("New password: ");
            String newPassword = v.checkString();
            System.out.print("Renew password: ");
            String renewPassword = v.checkString();
            if (v.MD5Encryption(oldPassword).equalsIgnoreCase(accoutLogin.getPassword()) == false) {
                System.err.println("Old password incorrect.");
            }
            if (newPassword.equalsIgnoreCase(renewPassword) == false) {
                System.err.println("New password and Renew password not the same.");
            }
            if (v.MD5Encryption(oldPassword).equalsIgnoreCase(accoutLogin.getPassword()) == true
                    && newPassword.equalsIgnoreCase(renewPassword) == true) {
                accoutLogin.setPassword(v.MD5Encryption(newPassword));
                System.out.println("Change password success");
            }
        }
    }

}
