package swea.d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_2806 {
	private static int N;
    private static int[] map;
    private static int cnt;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine().trim());
         
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(bf.readLine().trim());
            map = new int[N];
            cnt = 0;
            dfs(0);
            System.out.println("#"+test_case+" "+cnt);
        }//for end
    }
 
    private static void dfs(int row) {
        if(row==N) {
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if(isPossible(row,i)) {
                map[row] = i;
                dfs(row+1);
            }
             
        }
    }
 
    private static boolean isPossible(int row, int num) {
        for (int i = 0; i < row; i++) {
            if(map[i] == num) return false;
            else if((row-i) == Math.abs(map[i]-num)) return false;
        }
        return true;
    }
}