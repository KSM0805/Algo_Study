package swea.d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class D4_1233 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 0; test_case < 10; test_case++) {
			int nodeNum = Integer.parseInt(bf.readLine().trim());
			LinkedTree lt = new LinkedTree();
			for (int i = 0; i < nodeNum; i++) {
				// [0]번째 노드에 [1]을 넣고 [2],[3]번째 노드를 자식으로 갖는다.
				StringTokenizer st = new StringTokenizer(bf.readLine().trim()," ");
				switch(st.countTokens()){
					case 4:
						lt.makeTree(Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
						break;
					case 2:
						lt.makeTree(Integer.parseInt(st.nextToken()), st.nextToken());
						break;
				}
			}
			System.out.print("#"+(test_case+1)+" ");
			if(lt.solve(lt.root, true)) System.out.println("1");
			else System.out.println("0");
			
		}//test_case 끝
	}

}


class LinkedTree{
	TreeNode root;
	public void makeTree(int myNum, String pn, int cln, int crn) {
		if(root==null) {
			root = new TreeNode(myNum,pn,cln,crn);
		}
		else {
			TreeNode tn = new TreeNode(myNum,pn,cln,crn);
			if(root.myRight == tn.myNum ) {
				root.right = tn;
			}
			else if(root.myLeft == tn.myNum) {
				root.left = tn;
			}
			else {
				makeTree(root.left,tn);
				makeTree(root.right,tn);
			}
		}
	}
	
	public void makeTree(int myNum, String pn) {
		if(root==null) {
			root = new TreeNode(myNum,pn);
		}
		else {
			TreeNode tn = new TreeNode(myNum,pn);
			if(root.myRight == tn.myNum ) {
				root.right = tn;
			}
			else if(root.myLeft == tn.myNum) {
				root.left = tn;
			}
			else {
				makeTree(root.left,tn);
				makeTree(root.right,tn);
			}
		}
	}
	public void makeTree(TreeNode root, TreeNode child) {
		if(root!=null) {
			if(root.myRight == child.myNum ) {
				root.right = child;
				return;
			}
			else if(root.myLeft == child.myNum) {
				root.left = child;
				return;
			}
			else {
				makeTree(root.left,child);
				makeTree(root.right,child);
			}
		}
	}
	
	public boolean solve(TreeNode p, boolean a) {
		if(!a) return a;
		else {
			if(p != null) {
				char ch = p.data.charAt(0);
				if((int)ch>47 && (int)ch<58) { // ch-'0' 하면 문자열이 그대로 숫자로 반영
					if(p.left == null && p.right == null) {
						return a;
					}
					else {
						a = false;
						return a;
					}
				}else {
					if(p.left == null || p.right == null) {
						a = false;
						return a;
					}
					else{
						a = solve(p.left, a);
						a = solve(p.right,a);
					}
				}
			}
			return a;
		}
	}

}

class TreeNode{
	String data;
	int myNum;
	int myLeft;
	int myRight;
	TreeNode left;
	TreeNode right;
	public TreeNode(){}
	public TreeNode(String data) {
		this.data = data;
	}
	public TreeNode(int myNum, String data, int myLeft, int myRight) {
		this.myNum = myNum;
		this.data = data;
		this.myLeft = myLeft;
		this.myRight = myRight;
	}
	public TreeNode(int myNum, String data) {
		this.myNum = myNum;
		this.data = data;
	}
}