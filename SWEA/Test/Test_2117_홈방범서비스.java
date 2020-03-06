package swTest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class Test_2117_홈방범서비스 {
    private static int result;
    private static int M;
    private static int N;
    private static int[][] map;
    private static int home;
    private static boolean[][][] visit;
 
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine().trim());
         
        int[] cost = new int[22];
        cost[0] = 0;
        for (int i = 1; i < 22; i++) {
            cost[i] = i*i + (i-1)*(i-1);
        }
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
            N = Integer.parseInt(st.nextToken()); //5~20 도시의 크기
            M = Integer.parseInt(st.nextToken()); //1~10 집이 지불하는 비용
            map = new int[N][N];
            home = 0;
            String str;
            for (int i = 0; i < N; i++) {
                str = bf.readLine().trim();
                for (int j = 0; j < N; j++) {
                    if(str.charAt(j<<1) == '1') {
                        map[i][j] = M;
                        home+=M;
                    }
                }
            }
            result = 0;
            //bfs
            visit = new boolean[N][N][41];
            LinkedList<int[]> q = new LinkedList<>();
            //크고 가운데부터
            for (int i = 21; i > 0; i--) {
                if(home >= cost[i]) {
                    q.offer(new int[] {N/2,N/2,i}); //서비스 중심 위치, 서비스 영역
                    visit[N/2][N/2][i] = true;
                }
            }
             
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int range = cur[2]-1;
                int cnt = 0;
                if(cost[cur[2]]*M < result) continue;
                for (int i = cur[0]-range,col = 0; i <= cur[0] + range; i++) {
                    for (int j = cur[1]-col; j <= cur[1]+col; j++) {
//                      System.out.println(i+" "+j);
                        if(i<0 || j<0 || i>=N || j>=N) continue;
                        if(map[i][j] > 0) {
                            cnt += map[i][j];
                        }
                    }
                    if(i<cur[0]) col++;
                    else col--;
                }
                if(cnt >= cost[cur[2]]) {
                    result = Math.max(result, cnt);
                    if(home == cnt) break;
                }
                for (int i = cur[0]-1; i > -1; i--) {
                    for (int j = 0; j < N; j++) {
                        if(!visit[i][j][cur[2]]) {
                            visit[i][j][cur[2]] = true;
                            q.offer(new int[] {i,j,cur[2]});
                        }
                    }
                }
                for (int i = cur[1]+1; i < N; i++) {
                    if(!visit[cur[0]][i][cur[2]]) {
                        visit[cur[0]][i][cur[2]] = true;
                        q.offer(new int[] {cur[0],i,cur[2]});
                    }
                }
                for (int i = cur[0]+1; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(!visit[i][j][cur[2]]) {
                            visit[i][j][cur[2]] = true;
                            q.offer(new int[] {i,j,cur[2]});
                        }
                    }
                }
            }
            System.out.println("#"+test_case+" "+(result/M));
        }//testcase end
    }//main end
}