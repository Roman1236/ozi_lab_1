import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class User {
    String name;
    String password;
    static String tempName = "";
    static ArrayList<String> users = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();
    static ArrayList<String> blocked = new ArrayList<>();

    public User(){
        readFiles();
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        if(userExist(name) == false) {
            readFiles();
            addUser(name, password);
        }
        else {
            System.out.println("This username already taken");
        }
    }

    public static void changePassword(String username) {
        boolean stop = false;
        int count = 0;

        for (int i = 0; i < User.users.size(); i++) {
            if(username.equals(User.users.get(i))) {
                User.tempName = User.users.get(i);
                count = i;
                break;
            }
        }

        int counter3 = 0;
        Scanner scanner = new Scanner(System.in);

        while (stop == false) {

            System.out.println("Enter your old password");

            String oldPassword = "";

                oldPassword = scanner.next();

            if(oldPassword.equals(User.passwords.get(count))) {
                stop = true;

                System.out.println("Enter your new password");
                String tempPass1 = "";

                    tempPass1 = scanner.next();
                    System.out.println("Confirm your new password");

                    if(tempPass1.equals(scanner.next())) {
                        User.passwords.set(count,tempPass1);
                        stop = true;
                        System.out.println("Password changed");
                    }
                }
            else {
                counter3++;
                if(counter3 > 2) {scanner.close();exit(0);}
                System.out.println("wrong password, to try again press 1, to quit press 2");

                int choosedNumber;

                    choosedNumber = scanner.nextByte();

                switch (choosedNumber) {
                    case 1: stop = false; break;
                    case 2: stop = true; break;
                    default: stop = true;
                }
            }
        }

    }

    public void addUser(String user, String password) {
        users.add(user);
        passwords.add(password);

        File usersFile = new File("users.txt");
        try{
            if(usersFile.exists()==false){
                usersFile.createNewFile();
            }
            PrintWriter out1 = new PrintWriter(new FileWriter(usersFile, false));//
            out1.append("");
            PrintWriter out2 = new PrintWriter(new FileWriter(usersFile, true));
            for (int i = 0; i < users.size(); i++) {
                out2.append( users.get(i) + "\n" );
            }
            out1.close();
            out2.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }

        File passwordsFile = new File("passwords.txt");
        try{
            if(passwordsFile.exists()==false){
                passwordsFile.createNewFile();
            }
            PrintWriter out3 = new PrintWriter(new FileWriter(passwordsFile, false));
            out3.append("");
            PrintWriter out4 = new PrintWriter(new FileWriter(passwordsFile, true));
            for (int i = 0; i < users.size(); i++) {
                out4.append( passwords.get(i) + "\n" );
            }
            out3.close();
            out4.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }
    }

    public void readFiles() {
        File usersFile = new File("users.txt");
        File passwordsFile = new File("passwords.txt");

        BufferedReader reader1;
        try {
            reader1 = new BufferedReader(new FileReader(usersFile.getAbsolutePath()));
            String line = reader1.readLine();
            while (line!=null) {
                users.add(line);
                line = reader1.readLine();
            }
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader2;
        try {
            reader2 = new BufferedReader(new FileReader(passwordsFile.getAbsolutePath()));
            String line = reader2.readLine();
            while (line != null) {
                passwords.add(line);
                line = reader2.readLine();
            }
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean userExist(String user) {
        File usersFile = new File("users.txt");
        BufferedReader reader1;
        try {
            reader1 = new BufferedReader(new FileReader(usersFile.getAbsolutePath()));
            String line = reader1.readLine();
            while (line != null) {
                if(line.equals(user)) {
                    return true;
                }
                line = reader1.readLine();
            }
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    static  public void login() {
        System.out.println("enter your username");
        String username = null;
        int count = 0;
        Scanner scanner = new Scanner(System.in);

            username = scanner.next();
            for (int i = 0; i < User.users.size(); i++) {
                if(username.equals(User.users.get(i))) {
                    User.tempName = User.users.get(i);
                    count = i; break;
                }
            }
System.out.println();


        int counter3 = 0;
        boolean stop = false;
        while (stop == false) {
            System.out.println("enter your password");
            String password = null;


                password = scanner.next();

                if (password.equals(User.passwords.get(count))) {
                    System.out.println("succesfully logged in");
                    stop = true;
                }
                else {
                    counter3++;
                    if (counter3 > 2) {
                        exit(0);
                    }
                    System.out.println("Wrong password");
                }

        }

                if(User.tempName.equals("ADMIN")) {
                    System.out.println("1) change password\n2) watch users list\n3) ban users \n4) exit");
                    int input = 0;

                    input = scanner.nextByte();

                    switch (input) {
                        case 1:
                            changePassword("ADMIN");
                        case 2:

                            for (int i = 0; i < User.users.size(); i++) {
                                System.out.println(User.users.get(i));
                            }

                        case 3: exit(0);
                        default: exit(0);
                    }
                }
                else {
                    System.out.println("1) change password\n2) exit");
                    int input;

                        input = scanner.nextByte();


                    switch (input) {
                        case 1: changePassword(User.tempName);
                        case 2: exit(0);
                        default: exit(0);
                    }
                }
        System.out.println();

    }

}

