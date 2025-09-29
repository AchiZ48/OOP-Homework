/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.util.ArrayList;
import java.util.*;

class SortMergeList {
    public static ArrayList<Integer> intersect(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            int a = list1.get(i);
            int b = list2.get(j);

            if (a == b) {
                result.add(a); 
                i++;
                j++;
            } else if (a < b) {
                i++;  
            } else {
                j++; 
            }
        }

        return result;
    }
}

public class Lab5No3 {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        while (true) {
            int num = sc.nextInt();
            if (num == 0){
                break;
            }
            list1.add(num);
        }

        while (true) {
            int num = sc.nextInt();
            if (num == 0){
                break;
            }
            list2.add(num);
        }

        ArrayList<Integer> result = SortMergeList.intersect(list1, list2);

        for (int n : result) {
            System.out.print(n + " ");
        }

        sc.close();
    }
}
