package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_1786_찾기 {
	private static int result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		result = 0;
		String S = bf.readLine();
		String P = bf.readLine();
		KMP(S,P);
		System.out.println(result);
		System.out.println(sb.toString());
	}

	private static void KMP(String s, String p) {
		int[] pi = getPi(p);
		int j = 0;
		
		for (int i = 0, size = s.length(); i < size; i++) {
			while(j > 0 && s.charAt(i) != p.charAt(j)) j = pi[j-1];
			if(s.charAt(i) == p.charAt(j)) {
				if(j == p.length() - 1) {
					result++;
					sb.append(i - p.length() + 2).append(" ");
					j = pi[j];
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
