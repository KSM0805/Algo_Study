package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열 {
	private static int result;

	/*public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] len = new int[N];
		result = 1;
		for (int i = 0; i < N; i++) {
			len[i] = 1;
			for (int j = 0; j < i; j++) {
				if(num[i] > num[j] && len[i] < len[j] + 1) {
					len[i] = len[j] + 1;
					result = Math.max(result, len[i]);
				}
			}
		}
		System.out.println(result);
	}*/
	
	//O(nlogn)
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		int[] c = new int[N];
		c[0] = num[0];
		int pos = 1;
		
		for (int i = 1; i < N; i++) {
			if(c[pos-1] < num[i]) {	// 마지막 수 보다 큰 수라면 증가 수열이 맞으므로 저장
				c[pos] = num[i];
				pos++;
			}else { // 마지막 수 보다 작으므로 증가 수열이 아니라서 기존의 수열에 update
				int idx = Arrays.binarySearch(c, 0, pos, num[i]);
				if(idx < 0) {	// a[i]데이터가 c배열에 없을때
					idx = -(idx+1);
					c[idx] = num[i];
				}
			}
			
		}
		System.out.println(pos);
	}
}
