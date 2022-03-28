package algo_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17837_���ο����2 {
	public static LinkedList<Integer>[][] horsesInChess;
	public static int[][] horses;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] chess = new int[N][N];
		
		// ü���ǿ� ���� �ִ� ����Ʈ
		horsesInChess = new LinkedList[N][N];
		
		// 0: ���, 1: ������, 2: �Ķ���
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			for (int j = 0; j < N; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
				horsesInChess[i][j] = new LinkedList<Integer>();
			}
		}
		
		// ��
		// [0]: ��, [1]: ��, [2]:�̵�����(������, ����, ��, �Ʒ�)
		horses = new int[K][3];
	
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			for (int j = 0; j < 3; j++) {
				horses[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
			// ��ġ�� �ش� ���� �д�.
			horsesInChess[horses[i][0]][horses[i][1]].add(i);
		}
		// ����
		int[][] dir = {{0,1}, {0, -1}, {-1, 0} , {1, 0}};
		int[] reverseDir = {1, 0, 3, 2};
		
		// ���� 4�� �̻� ���̴� ���, ������ ������� �ʴ� ���, ���� 1000�� �ʰ��ϴ� ��� ����
		int horseCount = 1;
		int turn = 0;
		int targetHorse = 0;
		while(horseCount < 4 && turn < 1001) {
			// ���ο� �� ����
			if (targetHorse == 0) {
				turn++;
			}
			int[] horse = Arrays.copyOf(horses[targetHorse], 3);
			
			int nextX = horse[0] + dir[horse[2]][0];
			int nextY = horse[1] + dir[horse[2]][1];
			// ���� ��ġ�� ü���� ��
			int chessColor;
			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
				chessColor = 2;
			} else {
				chessColor = chess[nextX][nextY];
			}
			
			// ü������ ���
			if (chessColor == 0) {
				horseCount = moveHorses(targetHorse, horse, nextX, nextY);
			} else if (chessColor == 1) {
				// ü������ ������
				horseCount = reverseAndMoveHorses(targetHorse, horse, nextX, nextY);
			} else {
				// ü������ �Ķ���
				horse[2] = reverseDir[horse[2]];
				nextX = horse[0] + dir[horse[2]][0];
				nextY = horse[1] + dir[horse[2]][1];
				horses[targetHorse][2] = horse[2];
				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
					// none
				} else {
					if (chess[nextX][nextY] == 0) {
						horseCount = moveHorses(targetHorse, horse, nextX, nextY);
					} else if (chess[nextX][nextY] == 1) {
						horseCount = reverseAndMoveHorses(targetHorse, horse, nextX, nextY);
					}
				}
			}
			
			// ������ ���̸� �ʱ�ȭ
			if (targetHorse == K - 1) {
				targetHorse = 0;
			} else {
				targetHorse++;
			}
		}
		
		System.out.println(turn > 1000 ? -1 : turn);
	}

	private static int reverseAndMoveHorses(int targetHorse, int[] horse, int nextX, int nextY) {
		// ���� �׿��ִ� ������ �ٲ۴�.
		boolean isReverse = true;
		while(!horsesInChess[horse[0]][horse[1]].isEmpty() && isReverse) {
			int horseNum = horsesInChess[horse[0]][horse[1]].pollLast();
			horsesInChess[nextX][nextY].add(horseNum);
			horses[horseNum][0] = nextX;
			horses[horseNum][1] = nextY;
			
			if (horseNum == targetHorse) isReverse = false;
		}                
		
		return horsesInChess[nextX][nextY].size();
	}

	private static int moveHorses(int targetHorse, int[] horse, int nextX, int nextY) {
		boolean isMove = false;
		int currentLength = horsesInChess[horse[0]][horse[1]].size();
		for (int i = 0, target = 0; i < currentLength; i++) {
			if (targetHorse == horsesInChess[horse[0]][horse[1]].get(target)) {
				isMove = true;
			}
			
			if (isMove) {
				int horseNum = horsesInChess[horse[0]][horse[1]].get(target);
				horsesInChess[nextX][nextY].add(horseNum);
				horses[horseNum][0] = nextX;
				horses[horseNum][1] = nextY;
				horsesInChess[horse[0]][horse[1]].remove(target);
				continue;
			}
			
			target++;
		}
		
		return horsesInChess[nextX][nextY].size();
	}

}
