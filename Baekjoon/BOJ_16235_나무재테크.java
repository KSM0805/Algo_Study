package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16235_나무재테크 {

	private static int result;
	private static int N;
	private static int M;
	private static int K;
	private static int[][] add;
	private static PriorityQueue<Tree> trees;
	private static int[][] map;
	private static LinkedList<Tree> die;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,1},{1,-1}};
	private static LinkedList<Tree> reAdd;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());	// 1 ~ 10 : 배열 크기
		M = Integer.parseInt(st.nextToken());	// 1 ~ N^2 : 초기 나무의 갯수
		K = Integer.parseInt(st.nextToken());	// 1 ~ 1000 : 시간
		add = new int[N][N];	// 겨울에 추가되는 비료량 배열
		map = new int[N][N];
		trees = new PriorityQueue<Tree>(new Comparator<Tree>() {

			@Override
			public int compare(Tree o1, Tree o2) {
				return o1.age - o2.age;
			}
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;	// 초기 비료량 5로 초기화
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken())));
		}
		die = new LinkedList<Tree>();
		reAdd = new LinkedList<Tree>();
		for (int i = 0; i < K; i++) {
			// 1. 봄
			for (int j = 0, end = trees.size(); j < end; j++) {
				Tree cur = trees.poll();
				// 1-1. 어린 나무부터 자신의 나이만큼 영양분을 먹고 나이가 +1
				if(map[cur.r][cur.c] >= cur.age) {
					map[cur.r][cur.c] -= cur.age;
					reAdd.add(new Tree(cur.r, cur.c, cur.age + 1));
					// 1-2. 충분한 영양분이 없다면 죽는다.
				}else die.add(cur);
				
			}
			// 2. 여름
			while(!die.isEmpty()) {
				// 2-1. 죽은 나무의 나이/2 만큼 영양분이 된다.
				Tree cur = die.poll();
				map[cur.r][cur.c] += cur.age/2;
			}
			// 3. 가을
			while(!reAdd.isEmpty()) {
				Tree cur = reAdd.poll();
				if(cur.age % 5 == 0) {
					for (int k = 0; k < 8; k++) {
						int nr = cur.r + dir[k][0];
						int nc = cur.c + dir[k][1];
						if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
						trees.add(new Tree(nr, nc, 1));
					}
				}
				trees.add(cur);
			}
			// 4. 겨울
			// 4-1. 양분을 추가한다.
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					map[j][k] += add[j][k];
				}
			}
		}
		result = trees.size();
		System.out.println(result);
	}
}
class Tree implements Comparable<Tree>{
	int r;
	int c;
	int age;
	
	public Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
}
