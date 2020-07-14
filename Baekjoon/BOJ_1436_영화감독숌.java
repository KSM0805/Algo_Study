package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1436 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//문자열 384ms
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.parseInt(bf.readLine().trim()); // 1<= target<=10000
		//0~ 숫자를 증가 시키면서 666이 들어간 숫자를 검색, CNT++해서 출력
		int x = 665;
		String y = "666";
		int cnt = 0;
		int pos;

		while(cnt<target) {
			int num = x;
			for (; num>0;num = num/10) {
				if(num%1000 == 666) {
					cnt++;
					break;
				}
			}
			x++;
			/*String a = Integer.toString(x);
			pos = 0;
			if(a.contains("666")) {
				cnt++;
			}
			x++;*/
			/*int cnt6 = 0;
			int num = x;
			pos = 0;
			for (; num>5;num = num/10) {
				if(num%10 == 6) {
					cnt6++;
					if(cnt6==3) {
						cnt++;
						break;
					}
				}
				else {
					cnt6 = 0;
				}
			}
			x++;*/
			/*for (int i = 0, size= a.length(); i < size; i++) {
				if(a.charAt(i) == y.charAt(pos)) {
					pos++;
					if(pos==3) {cnt++;break;}}}
			if(pos==3) {x++;continue;}x++; 문제 유의할것 6이 연속으로 나오는 숫자임*/
		}
		System.out.println(x-1);
	}

}
