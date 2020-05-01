package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_16916_부분문자열 {
	private static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		String S = bf.readLine().trim();
		String P = bf.readLine().trim();
		KMP(S,P);
		System.out.println(result);
	}

	private static void KMP(String s, String p) {
		int[] pi = getPi(p);
		int j = 0;
		
		for (int i = 0, size = s.length(); i < size; i++) {
			while(j > 0 && s.charAt(i) != p.charAt(j)) j = pi[j-1];
			if(s.charAt(i) == p.charAt(j)) {
				if(j == p.length() - 1) {
					result = 1;
					break;
				}
				else j++;
			}
		}
	}

	private static int[] getPi(String p) {
		int[] pi = new int[p.length()];
		int j = 0;
		
		for (int i = 1, size = p.length(); i < size; i++) {
			while(j > 0 && p.charAt(i) != p.charAt(j)) j = pi[j-1];
			if(p.charAt(i) == p.charAt(j)) pi[i] = ++j;
		}
		return pi;
	}
}
