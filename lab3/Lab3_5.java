/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

/**
 *
 * @author student
 */
import java.util.Scanner;

class RoachPopulation {
    private int roaches;

    public RoachPopulation(int initialPopulation) {
        this.roaches = initialPopulation;
    }

    public void waitTime() {
        roaches *= 2;
    }

    public void spray() {
        roaches = (int) Math.ceil(roaches*0.9);
    }

    public int getRoaches() {
        return roaches;
    }
}

public class Lab3_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int initial = sc.nextInt();
        int rounds = sc.nextInt();

        RoachPopulation population = new RoachPopulation(initial);

        for (int i = 0; i < rounds; i++) {
            population.waitTime();
            population.spray();
        }

        System.out.println(population.getRoaches());
        sc.close();
    }
}