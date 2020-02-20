package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941 {
	static int cnt;
	private static String str;
	private static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		str = bf.readLine().trim();
		n = str.length();
		int pos = 0;
		while(pos<n) {
			char c = str.charAt(pos);
			if(c=='c' || c=='d' || c=='l' || c=='n' || c== 's' || c=='z') {
				pos = find(c, pos);
			} else pos++;
			cnt++;
		}
		System.out.println(cnt);
	}

	static int find(char c, int pos) {
		if(pos+1>n-1) return pos+1;
		switch (c) {
			case 'c':
				if(str.charAt(pos+1)=='=' || str.charAt(pos+1)=='-') {
					pos += 1;
				}
				break;
	
			case 'd':
				if(pos+2<n && str.charAt(pos+1)=='z' && str.charAt(pos+2)=='=') {
					pos += 2;
				}else if(str.charAt(pos+1)=='-') {
					pos += 1;
				}
				break;
				
			case 'l':
				if(str.charAt(pos+1)=='j') {
					pos += 1;
				}
				break;
				
			case 'n':
				if(str.charAt(pos+1)=='j') {
					pos += 1;
				}
				break;
			case 's':
				if(str.charAt(pos+1)=='=') {
					pos += 1;
				}
				break;
			case 'z':
				if(str.charAt(pos+1)=='=') {
					pos += 1;
				}
				break;
		}
		return pos+1;
	}
}
