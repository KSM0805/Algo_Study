package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107_리모컨 {

	private static int N;
	private static int channel;
	private static int M;
	private static int result;
	private static int target;
	private static String str;
	private static int num;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		str = bf.readLine().trim();
		N = Integer.parseInt(str);
		channel = 100;
		M = Integer.parseInt(bf.readLine().trim());
		result = 0;
		num = 0;
		if(M != 0) {
			StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
			for (int i = 0; i < M; i++) {
				num |= 1 << Integer.parseInt(st.nextToken());
			}
		}
		if(channel != N) {
//			 1. 당연히 가장 가까운 채널로 이동한다.
			target = 1000001;
			// 조합으로 뽑아보자
			dfs(0, 0, Integer.toString(N).length());
			// 9라면 10도 가능
			if(str.charAt(0)-'0' >= 5 && target < N) {
				dfs(0, 0, Integer.toString(N).length() + 1);
			}
			// 10이라면 9도 가능
			if(N > 9 && str.charAt(0)-'0' <= 5 && target > N) {
				dfs(0, 0, Integer.toString(N).length() - 1);
			}
//			System.out.println(target);
			if(target != 1000001) {
				if(Integer.toString(target).length() + Math.abs(target-N) > Math.abs(channel-N)) {
					target = channel;
				} else if(Integer.toString(target).length() > Math.abs(channel-target)) {
					result += Math.abs(channel - target);
				} else result += Integer.toString(target).length();
				channel = target;
			}
			// 2. 이동한다.
			result += Math.abs(N - channel);
		}
		System.out.println(result);
	}
	private static void dfs(int n, int idx, int cnt) {
		// 최소 가까운 정도
		if(n != 0) {
			if(target < N && n < target) return;
			if(target > N && n > target) return;
		}
		if(cnt == 0) {
			if(Math.abs(target - N)+Integer.toString(target).length() > Math.abs(N - n)+Integer.toString(n).length()) target = n;
			return;
		}
		// 조합
		// 0이 가능한 경우는 idx > 0일때만
		if(idx > 0 && (num & 1) == 0) {
			dfs(n, idx + 1, cnt - 1);
		}
		if(idx == 0 && cnt == 1 && ((num & 1) == 0)) {
			dfs(0, idx + 1, cnt - 1);
		}
		for (int i = 1; i < 10; i++) {
			//사용 가능한 숫자면
			if((num & (1 << i)) == 0) {
				dfs(n + i*(int)Math.pow(10, cnt - 1), idx + 1, cnt - 1);
			}
		}
	}
//
//	private static void dfs(int mode, int idx, int n, int cnt) {
//		System.out.println("mode : " + mode + " idx : " + idx + " n : " + n + " cnt : " + cnt);
//		if(cnt == 0) {
//			if(Math.abs(N - target)+ Integer.toString(target).length() > Math.abs(N - n) + Integer.toString(n).length()) target = n;
//			return;
//		}
//		// 자릿수마다 비교하기
//		int oneNum = str.charAt(idx) - '0'; 
//		// mode 에 따라 다르다.
//		// mode- 0: 현재까지 동일한 숫자, 1: 앞자리 수가 더 큰 숫자, -1: 앞자리 수가 더 작은 숫자
//		if(mode == 0) {
//			if((num & (1 << oneNum)) == 0) {
//				dfs(0, idx + 1, n + oneNum*(int)Math.pow(10, cnt-1), cnt - 1);
//			}
//			int p = 1;
//			boolean check = false;
//			while(oneNum - p >= 0 || oneNum + p < 10) {
//				if(oneNum - p >= 0) {
//					// 예외 맨앞자리의 0은 무조건 사용할 수 있다.
//					// 사용 가능한 숫자라면
//					if((num & (1 << oneNum-p)) == 0 || (idx == 0 && oneNum-p == 0 && cnt > 1)) {
////						System.out.println(oneNum-p);
//						check = true;
//						dfs(-1, idx + 1, n + (oneNum-p)*(int)Math.pow(10, cnt-1), cnt - 1);
//					}
//				}
//				if(oneNum + p < 10) {
//					if((num & (1 << oneNum+p)) == 0) {
//						check = true;
//						dfs(1, idx + 1, n + (oneNum+p)*(int)Math.pow(10, cnt-1), cnt - 1);
//					}
//				}
//				if(check) return;
//				p++;
//			}
//		} else if(mode == -1) {
//			for (int i = 9; i > -1; i--) {
//				if((num & (1 << i)) == 0) {
//					dfs(-1, idx + 1, n + i*(int)Math.pow(10, cnt-1), cnt - 1);
//					break;
//				}
//			}
//		} else if(mode == 1) {
//			for (int i = 0; i < 10; i++) {
//				if((num & (1 << i)) == 0) {
//					dfs(1, idx + 1, n + i*(int)Math.pow(10, cnt-1), cnt - 1);
//					break;
//				}
//			}
//		}
//	}

}
