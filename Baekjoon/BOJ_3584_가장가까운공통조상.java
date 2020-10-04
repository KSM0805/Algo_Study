package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3584_가장가까운공통조상 {

	private static Node[] nodes;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		StringTokenizer st;
		LinkedList<int[]> q = new LinkedList<>();
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(bf.readLine().trim());
			nodes = new Node[N+1];
			for (int j = 1; j <= N; j++) {
				nodes[j] = new Node();
			}
			for (int j = 1; j < N; j++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				// A B => A가 B의 부모
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				nodes[A].children.add(B);
				nodes[B].parent = A;
			}
			for (int j = 1; j <= N; j++) {
				if(nodes[j].parent == -1) {
					nodes[j].degree = 0;
					for (int child : nodes[j].children) {
						q.add(new int[] {child, 1});
					}
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						nodes[cur[0]].degree = Math.min(cur[1], nodes[cur[0]].degree);
						for (int next : nodes[cur[0]].children) {
							q.add(new int[] {next, cur[1] + 1});
						}
					}
					break;
				}
			}
			// 찾아야할노드
			st = new StringTokenizer(bf.readLine().trim()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			while(start != end) {
				if(nodes[start].degree >= nodes[end].degree) {
					start = nodes[start].parent;
				} else {
					end = nodes[end].parent;
				}
			}
			System.out.println(start);
		}
	}
}
class Node{
	int parent = -1;
	ArrayList<Integer> children = new ArrayList<>();
	int degree = Integer.MAX_VALUE;
	public Node(){}
}