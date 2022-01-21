package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj4256 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void printPostOrder (int [] preOrder, int [] inOrder) throws IOException{
		int N = preOrder.length;
		
		if(preOrder.length == 0) {
			return;
		}
		
		int root = preOrder[0];
		int i;
		for(i=0; i <N; i++) {
			if(inOrder[i] == root) {
				break;
			}
		}
		
		int L = i;
		int R = N - L - 1;
	
		printPostOrder(Arrays.copyOfRange(preOrder, 1, L + 1), Arrays.copyOfRange(inOrder, 0, L));
		printPostOrder(Arrays.copyOfRange(preOrder, L + 1, N), Arrays.copyOfRange(preOrder, L + 1, N));
		
		bw.write(String.valueOf(root) + " ");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		int nodeNum;
		int[] preOrder;
		int[] inOrder;

		for (int i = 1; i <= tc; i++) {
			nodeNum = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			preOrder = new int[nodeNum];

			for (int j = 1; j <= nodeNum; j++) {
				preOrder[j - 1] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			inOrder = new int[nodeNum];

			for (int j = 1; j <= nodeNum; j++) {
				inOrder[j - 1] = Integer.parseInt(st.nextToken());
			}
			
			printPostOrder(preOrder, inOrder);
			bw.newLine();
			
		}
		br.close();
		bw.close();
	}
}
