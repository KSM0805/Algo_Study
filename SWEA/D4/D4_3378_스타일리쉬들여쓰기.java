package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D4_3378_스타일리쉬들여쓰기 {
	private static int p;
	private static int q;
	private static ArrayList<int[]> rcs = new ArrayList<>();
	private static int[][] pre;
	private static int[][] aft;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			p = Integer.parseInt(st.nextToken()); // 1~10 마스터의 글 줄 수 
			q = Integer.parseInt(st.nextToken()); // 1~10 나의 코드 줄 수
			//1. 마스터의 글에서 들여쓰기를 몇번하는지(RCS를 찾는다)
//			master();
			rcs.clear();
			String[] str = new String[p];
			pre = new int[p][4];
			str[0] = bf.readLine().trim();
			for (int i = 1; i < p; i++) {
				str[i] = bf.readLine().trim();
				for (int j = 0; j < str[i].length(); j++) {
					if(str[i].charAt(j) != '.') {
						pre[i][3] = j;
						break;
					}
				}
				for (int j = 0; j < str[i-1].length(); j++) {
					char ch = str[i-1].charAt(j);
					switch (ch) {
					case '(':
						pre[i][0]++;
						break;
					case '{':
						pre[i][1]++;
						break;
					case '[':
						pre[i][2]++;
						break;
					case ')':
						pre[i][0]--;
						break;
					case '}':
						pre[i][1]--;
						break;
					case ']':
						pre[i][2]--;
						break;
					default:
						break;
					}
				}
				pre[i][0] += pre[i-1][0];
				pre[i][1] += pre[i-1][1];
				pre[i][2] += pre[i-1][2];
			}
			//내 글
			String[] my = new String[q];
			my[0] = bf.readLine().trim();
			aft = new int[q][4];
			for (int i = 1; i < q; i++) {
				my[i] = bf.readLine().trim();
				for (int j = 0; j < my[i-1].length(); j++) {
					char ch = my[i-1].charAt(j);
					switch (ch) {
					case '(':
						aft[i][0]++;
						break;
					case '{':
						aft[i][1]++;
						break;
					case '[':
						aft[i][2]++;
						break;
					case ')':
						aft[i][0]--;
						break;
					case '}':
						aft[i][1]--;
						break;
					case ']':
						aft[i][2]--;
						break;
					default:
						break;
					}
				}
				aft[i][0] += aft[i-1][0];
				aft[i][1] += aft[i-1][1];
				aft[i][2] += aft[i-1][2];
			}
			//1-1.각각RCS를 찾는 코드
			//1-2. RCS를 1~20까지 대입
//			dfs(1,1,1);
			boolean check = true;
			for (int i = 1; i < 21; i++) {
				for (int j = 1; j < 21; j++) {
					for (int k = 1; k < 21; k++) {
						check = true;
						for (int l= 1; l < p; l++) {
							int a = i*pre[l][0] + j*pre[l][1] + k*pre[l][2];
							if(a!=pre[l][3]) {
								check = false;
								break;
							}
						}
						if(check) {
							rcs.add(new int[] {i,j,k});
						}
					}
				}
			}
			//2. RCS를 찾지 못하면 무조건 -1을 리턴
			System.out.print("#"+test_case+" 0 ");
			for (int i = 1; i < q; i++) {
				boolean possible = true;
				int first = -1;
				for (int j = 0; j < rcs.size(); j++) { //rcs사이즈 만큼 대입했을때 값이 같은지 확인
					int[] cur = rcs.get(j);
					int num = cur[0]*aft[i][0] + cur[1]*aft[i][1] + cur[2]*aft[i][2];
					if(first == -1) first = num;
					else if(first != num) {
						possible = false;
						break;
					}
				}
				if(possible) System.out.print(first+" ");
				else System.out.print("-1 ");
			}
			System.out.println();
		}//testcase end
	}//main end
}
