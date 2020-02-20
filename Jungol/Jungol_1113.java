package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Jungol_1113 {
	static int min;
    static int row,col;
    static int targetRow;
    static int targetCol;
    static int[][] map;
    static int[][] cnt;
         
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));

    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine().trim()," ");
        targetRow = Integer.parseInt(st.nextToken());
        targetCol = Integer.parseInt(st.nextToken());
         
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(bf.readLine().trim()," ");
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        emergency();
        System.out.println(min);
    }
 
    private static void emergency() {
        min = Integer.MAX_VALUE;
        cnt = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cnt[i][j] = Integer.MAX_VALUE - 1;
            }
        }
        int[][] route = new int[row*col][2];
        emergency(route,0,0,0,-1,0);
    }
    private static void emergency(int[][] route, int r, int c, int sum, int d,int idx) {
        int nr,nc;
        //4. 타겟 위치에 닿으면 횟수 비교하여 저장
        if(r==targetRow && c==targetCol) {
            if(sum<min) {
                min = sum;
            }
        }
        //2. 사방으로 탐색
        else{
            route[idx][0] = r;
            route[idx][1] = c;
            if(cnt[r][c]+1 < sum) {
                return;
            }
            else {
                cnt[r][c] = sum;
            }
            for (int i = 0; i < 4; i++) {
                nr = r + dir[i][0];
                nc = c + dir[i][1];
                //6. 경계선 밖으로 나가면 탈출
                if(nr<0 || nr>=row || nc<0 || nc>=col) {
                    continue;
                }else {
                    if(d==-1 || d==i) {
                        if(isPossible(route, nr, nc, sum, idx)) {
                            emergency(route, nr, nc, sum, i, idx+1);
                        }
                    }else { //3. 현재 방향과 다르면 횟수++
                        if(isPossible(route, nr, nc, sum+1, idx)) {
                            emergency(route, nr, nc, sum+1, i, idx+1);
                        }
                    }
                }
            }
        }
        return;
    }
 
    private static boolean isPossible(int[][] route, int r, int c, int sum, int idx) {
        if(min<sum) return false;
        else if(map[r][c] == 0) return false; //지나온 곳이면 탈출
        //5. 사방탐색하다가 벽을 만나면 탈출
        else {
            for (int i = 0; i < idx; i++) {
                if( route[i][0] == r && route[i][1] == c ) return false;
            }
        }
        return true;
    }
}
