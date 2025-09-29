package lab5;

import java.util.ArrayList;
import java.util.Scanner;

class Star {
    public static void addStars(ArrayList<String> list) {
        for (int i = 1; i <= list.size(); i += 2) {
            list.add(i, "*");
        }
    }

    public static void removeStars(ArrayList<String> list) {
        list.removeIf(word -> word.equals("*"));
    }
}
public class Lab5No2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String word = sc.next();
            if (word.matches("\\d+")) {
                int commandCount = Integer.parseInt(word);

                
                for (int i = 0; i < commandCount; i++) {
                    int command = sc.nextInt();
                    if (command == 1) {
                        Star.addStars(list);
                        System.out.println(list);
                    } else if (command == 2) {
                        Star.removeStars(list);
                        System.out.println(list);
                    }
                }
                break;
            }else{
                list.add(word);
            }
        }

        sc.close();
    }
}
