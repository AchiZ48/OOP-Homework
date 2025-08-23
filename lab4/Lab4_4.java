/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author studentcs
 */


public class AscendSortFreq {
    double[] A;

    // Constructor
    public AscendSortFreq(double[] A) {
        this.A = A;
    }

    public double[] AscendSort(double[] A) {
        double[] B = A.clone();
        Arrays.sort(B);
        return B;
    }

    public int[] SortCommuFreq(double[] B) {
        double[] unique = new double[B.length];
        int[] freq = new int[B.length];
        int uniqueCount = 0;

        for (int i = 0; i < B.length; i++) {
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (B[i] == unique[j]) {
                    freq[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                unique[uniqueCount] = B[i];
                freq[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        int[] cumFreq = new int[uniqueCount];
        cumFreq[0] = freq[0];
        for (int i = 1; i < uniqueCount; i++) {
            cumFreq[i] = cumFreq[i - 1] + freq[i];
        }

        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(unique[i] + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(cumFreq[i] + " ");
        }
        System.out.print("\n");
        return cumFreq;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        System.out.println("Enter the elements of the array:");
        double[] input = new double[size];
        for (int i = 0; i < size; i++) {
                input[i] = sc.nextInt();
    }
        AscendSortFreq obj = new AscendSortFreq(input);
        double[] sorted = obj.AscendSort(input);
        obj.SortCommuFreq(sorted);
    }
}



