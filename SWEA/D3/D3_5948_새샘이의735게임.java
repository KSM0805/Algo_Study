package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_5948_새샘이의735게임 {

	private static int[] num;
	private static HashSet<Integer> hash;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			num = new int[7];
			for (int i = 0; i < 7; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			hash = new HashSet<>();
			bigNumber();
			Object[] numbers = hash.toArray();
			Arrays.sort(numbers);
			System.out.println("#"+test_case+" "+numbers[numbers.length-5]);
		}//for end
	}

	private static void bigNumber() {
		LinkedList<int[]> q = new LinkedList<>();
		for (int i = 0; i < 5; i++) {
			q.offer(new int[] {i, num[i], 1}); //0: 현재위치 , 1: 현재까지의 합, 2: 숫자를 선택한 갯수
		}
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int idx = arr[0];
			int sum = arr[1];
			int cnt = arr[2];
			if(cnt==3) {
				hash.add(sum);
				continue;
			}
			for (int i = idx+1; i < 7; i++) {
				q.offer(new int[] {i, sum+num[i], cnt+1});
			}
		}
	}
}
