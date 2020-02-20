package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * BFS 시뮬레이션 문제
 *
 */
public class Jungol_1113_BFS {

    private static int M;
	private static int N;
	private static int m;
	private static int n;
	private static int[][] map;
	private static int[][] visited;
	public static final int DOWN = 2;
	public static final int UP = 1;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
    	//Exception Handling 에 대한 차이로 SC보다 BFR이 더 빠르다. SC는 모든 입력에 대해 예외를 검사함
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	String str = bf.readLine();
//    	String[] srr = str.split(" ");
//    	StringTokenizer st2 = new StringTokenizer(str); //띄어쓰기, 공백, 탭등으로 나눠줌
    	StringTokenizer st = new StringTokenizer(str," "); //받은 문자열, 나누는 기준 문자열
    	
    	M = Integer.parseInt(st.nextToken()); // 행, 1 <= M <= 100
    	N = Integer.parseInt(st.nextToken()); // 열 //parseInt 대신 valueof를 사용하면 한번 더 콜하지만 객체 지향임 본업에서 쓰는 것
    	
    	st = new StringTokenizer(bf.readLine()," ");
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	
    	map = new int[M][N]; //단축키 ctrl+1
    	
    	for (int i = 0; i < M; i++) {
    		str = bf.readLine(); //안쪼개고 쓰기 charAt
    		for (int j = 0, index = 0; j < N; j++, index += 2) {
//    			map[i][j] = str.charAt(j<<1) - '0'; //유니코드 값으로 읽어옴 숫자의 유니코드 - 0의 유니코드 하면 결국 답은 숫자와 같다
//    												//*2 보다 shift가 더 빠르다.
    			map[i][j] = str.charAt(index) - '0';
				
			}
		}
    	bfs();
    	System.out.println(visited[m][n]);//최종 결과 출력
    }// main end


	public static void bfs() {
		//queue : 무조건 선형 큐, 원형 큐는 나머지 연산해야해서 시간이 올라감		*API 활용
		Queue<Local> q = new LinkedList<Local>(); //행, 열, 전방향, 턴횟수
		visited = new int[M][N]; //턴의 개수로 방문 여부를 체크
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		// 시작 지점 지정
		visited[0][0] = 0;
		// 큐에 넣기
		q.offer(new Local(0,0,0,DOWN));
		q.offer(new Local(0,0,0,RIGHT));
		// 반복
		while(!q.isEmpty()) {
			Local lo = q.poll();
			int r = lo.r;
			int c = lo.c;
			int dir = lo.dir;
			int turn = lo.turn;
			int tempTurn; // 턴의 횟수가 증가하는지 체크해서 기록
			
			// 상
			tempTurn = turn + (dir==UP ? 0 : 1); 
			if (r-1 >= 0&& visited[r-1][c] >= tempTurn && map[r-1][c] == 1) { //이미 저장된 턴의 횟수보다 작을 때만 진입, 영역 범위 체크
				visited[r-1][c] = tempTurn;
				q.offer(new Local(r-1,c,tempTurn,UP));
			}
			//하
			tempTurn = turn + (dir==DOWN ? 0 : 1); 
			if (r+1 < M && visited[r+1][c] >= tempTurn && map[r+1][c] == 1) {
				visited[r+1][c] = tempTurn;
				q.offer(new Local(r+1,c,tempTurn,DOWN));
			}
			//좌
			tempTurn = turn + (dir==LEFT ? 0 : 1); 
			if (c-1 >= 0&& visited[r][c-1] >= tempTurn && map[r][c-1] == 1) {
				visited[r][c-1] = tempTurn;
				q.offer(new Local(r,c-1,tempTurn,LEFT));
			}
			//우
			tempTurn = turn + (dir==RIGHT ? 0 : 1); 
			if (c+1 < M && visited[r][c+1] >= tempTurn && map[r][c+1] == 1) {
				visited[r][c+1] = tempTurn;
				q.offer(new Local(r,c+1,tempTurn,RIGHT));
			} //dx,dy를 쓰면 어느 방향으로 가도 r이랑 c 모두 덧셈 체크하고 경계선 검사도 모든 방향을 다해야함. 노가다가 가장 좋다!

		}
	}
	public static class Local {
		int r;
		int c;
		int turn;
		int dir;
		
		public Local(int r, int c, int turn, int dir) {
			this.r = r;
			this.c = c;
			this.turn = turn;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Local [r=" + r + ", c=" + c + ", turn=" + turn + ", dir=" + dir + "]";
		}
		
	}
}