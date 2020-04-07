package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1231_중위순회 {
	private static int N;
	private static node[] list;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(bf.readLine().trim());
			list = new node[N];
			StringTokenizer st;
			int num, r, l;
			char c;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine().trim()," ");
				num = Integer.parseInt(st.nextToken()) - 1;
				c = st.nextToken().charAt(0);
				if(st.hasMoreTokens()) {
					l = Integer.parseInt(st.nextToken()) - 1;
					if(st.hasMoreTokens()) {
						r = Integer.parseInt(st.nextToken()) - 1;
						list[num] = new node(c, l, r);
					}else {
						list[num] = new node(c, l);
					}
				}else {
					list[num] = new node(c);
				}
			}
			System.out.print("#"+test_case+" ");
			inorder(0);
			System.out.println();
		}//testcase end
	}//main end

	private static void inorder(int pos) {
		if(list[pos].childL != 0) {
			inorder(list[pos].childL);
		}
		System.out.print(list[pos].ch);
		if(list[pos].childR != 0) {
			inorder(list[pos].childR);
		}
	}
}

class node{
	char ch;
	int childR,childL;
	
	public node(char ch) {
		this.ch = ch;
	}
	public node(char ch, int childL) {
		this.ch = ch;
		this.childL = childL;
	}
	public node(char ch, int childL, int childR) {
		super();
		this.ch = ch;
		this.childR = childR;
		this.childL = childL;
	}
}