/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

/**
 *
 * @author student
 */
import java.util.*;

class Ship {
    int start, end;
    public Ship(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Lab3_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            ships.add(new Ship(start, end));
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            int light = sc.nextInt();
            for (Ship ship : ships) {
                if (light > ship.start && light < ship.end) {
                    count++;
          
                }
            }
        }

        System.out.println(count);
        sc.close();
    }
}