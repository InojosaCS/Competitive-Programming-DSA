import java.io.*; 
import java.util.*;
 
public class GameRoutes{
	
	static LinkedList<Integer> graph[];
	static Boolean visited[] = new Boolean[100123];
	static Stack<Integer> topologicalSort = new Stack<Integer>();
	static int[] ways = new int[100123];
	static int modulo = 1000000007;
 
	private static void dfs(int x){
		visited[x] = true;
		for(int y: graph[x]){
			if(!visited[y]){
				dfs(y);
			}
		}
		topologicalSort.push(x);
	}
 
	private static void getAns(){
		while(topologicalSort.size() > 0){
			int current = topologicalSort.peek();
			topologicalSort.pop();
			for (int next : graph[current]) {
				ways[next] = (ways[next] + ways[current])%modulo;
			}
		}
	}
 
	private static void solve(){
		FastScanner in = new FastScanner();
 
		int n = in.nextInt();
		int m = in.nextInt();
		int []degree = new int[n];
		graph = new LinkedList[n];
 
		for (int i=0; i<n ; ++i) {
			graph[i] = new LinkedList<Integer>();
			ways[0] = degree[i] = 0;
			visited[i] = false;
		}
 
		int a,b;
 
		for (int i=0; i<m; ++i) {
			a = in.nextInt();
			b = in.nextInt();
			a--; b--;
			graph[a].add(b);
			degree[b]++;
		}
 
		ways[0] = 1;
		dfs(0);
		getAns();
 
		System.out.println(ways[n-1]);
	}
 
	public static void main(String[] args) {
		solve();
	}
 
	// Fast input template by SecondThread https://codeforces.com/profile/SecondThread
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
 
/*
@SuppressWarnings("unchecked")
class Graph { 
    int size;
    LinkedList<Integer> graph[];
 
    public Graph(int size) {
        // Creating graph
        this.size = size;
        graph = new LinkedList[size];
 
        for (int i = 0; i < size; i++) {
            graph[i] = new LinkedList<Integer>();
        }
    }
 
    public void addEdge(int source, int destination, boolean isUndirected){
 
        graph[source].add(destination); //add edge u -> v
 
        if(isUndirected)  //add back edge v -> u (if undirected)
            graph[destination].add(source);
    }
 
    public LinkedList<Integer> getAdj(int index){
        return graph[index];
    }
 
} */