import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.io.*; 
import java.util.*;

public class countingRooms{
	
	static int N = 1007;
	static boolean graph[][] = new boolean[N][N];
	static boolean visited[][] = new boolean[N][N];
	static int answer = 0;
	static int []dx = {1, 0, -1, 0};
	static int []dy = {0, 1, 0, -1};

	private static void dfs(int x, int y){
		Stack <Integer> stackX = new Stack<Integer>();
		Stack <Integer> stackY = new Stack<Integer>();
		stackX.push(x);
		stackY.push(y);

		while(!stackX.empty()){
			x = stackX.peek();
			stackX.pop();
			y = stackY.peek();
			stackY.pop();
			visited[x][y] = true;
			
			for (int  i = 0; i < 4; i++) {
				if(!visited[x + dx[i]][y + dy[i]] && graph[x + dx[i]][y + dy[i]]){
					stackX.push(x + dx[i]);
					stackY.push(y + dy[i]);
				}
			}
		}
		return;
	}

	private static void solve(){
		FastScanner in = new FastScanner();

		int n = in.nextInt();
		int m = in.nextInt();

		for (int i = 0; i < n; i++) {
			String line = in.next();
			//System.out.println(line + " " + line.length());
			for (int j = 0; j < m; j++) {
				char temp = line.charAt(j);
				//char temp = '.';
				graph[i+1][j+1] = (temp == '.') ? true : false;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(!visited[i+1][j+1] && graph[i+1][j+1]){
					dfs(i + 1, j + 1);
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) {
		solve();
	}

	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
}