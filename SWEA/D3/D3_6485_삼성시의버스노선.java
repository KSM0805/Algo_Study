package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6485 {
	private static int N;
    private static int[][] A;
    private static int P;
    private static int[] no;
 
    public static void main(String[] args) throws Exception{
         
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
             
            N = Integer.parseInt(bf.readLine().trim());
            A = new int[N][2];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine().trim());
                A[i][0] = Integer.parseInt(st.nextToken());
                A[i][1] = Integer.parseInt(st.nextToken());
            }
            P = Integer.parseInt(bf.readLine().trim());
            no = new int[5000];
            for (int i = 0; i < N; i++) {
                int start = A[i][0] - 1;
                int end = A[i][1] - 1;
                for (int j = start; j <= end; j++) {
                    no[j]++;
                }
            }
            System.out.print("#"+test_case+" ");
            for (int i = 0; i < P; i++) {
                System.out.print(no[Integer.parseInt(bf.readLine().trim())-1]+" ");
            }
            System.out.println();
        }
    }
}