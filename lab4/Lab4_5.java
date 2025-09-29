/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.Scanner;

/**
 *
 * @author studentcs
 */
public class Lab4_5 {
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int l = A[0].length;
        int m = B[0].length;

        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                for (int j = 0; j < l; j++) {
                    C[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // อ่านขนาดเมทริกซ์ A (3x1)
        int rowA = sc.nextInt();
        int colA = sc.nextInt();
        int[][] A = new int[rowA][colA];

        // อ่านค่าเมทริกซ์ A
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // อ่านขนาดเมทริกซ์ B (1x3)
        int rowB = sc.nextInt();
        int colB = sc.nextInt();
        int[][] B = new int[rowB][colB];

        // อ่านค่าเมทริกซ์ B
        for (int i = 0; i < rowB; i++) {
            for (int j = 0; j < colB; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        // ตรวจสอบว่าจำนวนคอลัมน์ของ A == จำนวนแถวของ B
        if (colA != rowB) {
            System.out.println("ไม่สามารถคูณเมทริกซ์ได้: ขนาดไม่ถูกต้อง");
            return;
        }

        // คำนวณ C = A x B → ขนาด rowA x colB
        int[][] C = new int[rowA][colB];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                C[i][j] = 0;
                for (int k = 0; k < colA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // แสดงผลลัพธ์
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}

