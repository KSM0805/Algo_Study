package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {
	private static Country[] countries;
	private static boolean check;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		countries = new Country[6];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(bf.readLine().trim()," ");
			for (int j = 0; j < 6; j++) {
				countries[j] = new Country(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			check = false;
			dfs(0, 1); // [0]: 나라 인덱스, [1]: 대결 나라 인덱스
			System.out.print(check ? "1 " : "0 ");
		}
		System.out.println();
	}

	private static void dfs(int idx, int versidx) {
		if(check) return;
		if(idx > 5) {
			for (int i = 0; i < 6; i++) {
				if(countries[i].win != 0 || countries[i].draw != 0 || countries[i].lose != 0) {
					return;
				}
			}
			check = true;
			return;
		}
		if(versidx > 5) dfs(idx + 1, idx + 2);
		else {
			// 1. 내가 versidx에게 이기는 경우
			if(countries[idx].win > 0 && countries[versidx].lose > 0) {
				countries[idx].win--;
				countries[versidx].lose--;
				dfs(idx, versidx + 1);
				countries[idx].win++;
				countries[versidx].lose++;
			}
			// 2. 무승부인 경우
			if(countries[idx].draw > 0 && countries[versidx].draw > 0 ) {
				countries[idx].draw--;
				countries[versidx].draw--;
				dfs(idx, versidx + 1);
				countries[idx].draw++;
				countries[versidx].draw++;
			}
			// 3. 지는 경우
			if(countries[idx].lose > 0 && countries[versidx].win > 0) {
				countries[idx].lose--;
				countries[versidx].win--;
				dfs(idx, versidx + 1);
				countries[idx].lose++;
				countries[versidx].win++;
			}
		}
		
	}
}

class Country{
	int win;
	int draw;
	int lose;

	public Country(int win, int draw, int lose) {
		super();
		this.win = win;
		this.draw = draw;
		this.lose = lose;
	}
}