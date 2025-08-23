/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lap5no1;

import java.util.ArrayList;
import java.util.Arrays;
public class Lap5No1 {

        public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(
            Arrays.asList(5, 9, 2, 9, 1, 2, 8, 9, 1, 6,
                          9, 1, 3, 9, 8, 4, 2, 1, 5, 7,
                          2, 7, 9, 3, 5, 2, 7, 6, 4, 6,
                          5, 1, 6, 2, 7, 1, 7, 9, 1, 4,
                          8, 5, 2, 3, 9, 8, 5, 6, 3, 3));


        double sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        double avg = sum / numbers.size();
        System.out.printf("Average value: %.2f\n",avg);


        int max = numbers.get(0);
        int min = numbers.get(0);
        for (int n : numbers) {
            if (n > max) max = n;
            if (n < min) min = n;
        }
        System.out.println("Maximun value: " + max);
        System.out.println("Minimum value: " + min);


        ArrayList<Integer> oddOnly = new ArrayList<>();
        for (int n : numbers) {
            if (n % 2 != 0) {
                oddOnly.add(n);
            }
        }

        System.out.println("Odd numbers only: " + min);
        for (int n : oddOnly) {
            System.out.print(n + " ");
        }
    }
    
}