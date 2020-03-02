package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class D4_1233_2 {
    static boolean a;
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N;
        String[][] arr;
        for (int i = 0; i < 10; i++) {
            N = Integer.parseInt(bf.readLine().trim());
            arr = new String[N+1][4];
            for (int j = 1; j <= N; j++) {
                StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
                for (int k = 0, size = st.countTokens(); k < size; k++) {
                    arr[j][k] = st.nextToken();
                }
            }
            //탐색 시작
            solve(arr);
            System.out.print("#"+(i+1)+" ");
            if(a) {
                System.out.println("1");
            }
            else System.out.println("0");
        }//for end
    }
    private static void solve(String[][] map) {
        a = true;
        solve(map,1);
         
    }
    private static void solve(String[][] map, int i) {
        //1. 숫자면 자식 노드에 가서 연산자면 error
        if(!a) return;
        if(47< (int)map[i][1].charAt(0) && 58>(int)map[i][1].charAt(0)) {
            if(map[i][2]==null && map[i][3]==null) {
                a = true;
            }
            else {
                a = false;
                return;
            }
        }
        //2. 연산자면 자식노드에 가서 다시 검색
        else {
            if(map[i][2]==null || map[i][3]==null) {
                a = false;
                return;
            }
            int a = Integer.parseInt(map[i][2]);
            int b = Integer.parseInt(map[i][3]);
            solve(map,a);
            solve(map,b);
        }
    }
 
}