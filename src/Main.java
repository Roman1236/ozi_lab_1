import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {

    public static void main(String []args){
        User u = new User();
        Menu();
    }

    private static void Menu() {
        int a = 0;
        while (a != 3) {
            System.out.println("1) registration\n2) " +
                    "log into your account\n3) info\n4) exit\n");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                a = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (a) {
                case 49: System.out.println("enter your username");
                String username = null;
                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        username = reader1.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }System.out.println();
                    if(username == null)
                        System.out.println("field username should not be empty");
                    else {
                        System.out.println("enter your password");
                        String password = null;
                        BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            password = reader2.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }System.out.println();
                        if(password == null)
                            System.out.println("field password should not be empty");
                        else {
                            User user = new User(username, password);

                            System.out.println("registration completed");
                        }
                        Menu();
                    }
                case 50:
                    User.login();
                        Menu();
                case 51: System.out.println("Автор програми студент групи ІС-71 Цвік Роман" +
                        "варіант №20 Наявність рядкових і прописних букв, цифр і знаків арифметичних операцій.");
                    Menu();
                case 52: exit(0);
                default: exit(0);
            }
        }
    }

}
