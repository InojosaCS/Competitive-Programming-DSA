import java.io.*; 
import java.util.*;
 
public class CycleFinding{
	
	static LinkedList<Tuple> edges = new LinkedList<Tuple>();

	private static void bellmanFord(int n){
		int sz = n;
		Boolean change = true;
		Long []distance = new Long[sz+1];
		Arrays.fill(distance, 999999999999999L);
		int []parent = new int[sz+1];
		Arrays.fill(parent, -1);
		distance[0] = 0L;		

		while(sz --> 0 && change){
			change = false;
			for(Tuple edge: edges){
				int a = (int) edge.t1;
				int b = (int) edge.t2;
				Long c = (Long) edge.t3;
				if(distance[a] + c < distance[b]){
					change = true;
					distance[b] = distance[a] + c;
					parent[b] = a;
				}
			}
		}
		
		change = false;
		int last = -1;
		for(Tuple edge: edges){
			int a = (int) edge.t1;
			int b = (int) edge.t2;
			Long c = (Long) edge.t3;
			if(distance[a] + c < distance[b]){
				last = a;
				change = true;
				parent[b] = a;
				distance[b] = distance[a] + c;
				break;
			}
		}
		
		if(change){
			System.out.println("YES");
			int x = last, cnt = 0;
			Boolean visited[] = new Boolean[n+1];
			int ans[] = new int[n+1];
			Arrays.fill(visited, false);

			while(!visited[x]){
				ans[cnt] = x;
				cnt++;
				visited[x] = true;
				x = parent[x];
			}
			
			ans[cnt] = x;
			Arrays.fill(visited, false);

			for (int i = cnt; i > -1 ; i--) {
				System.out.print(ans[i] + " ");
				if(visited[ans[i]])
					break;
				visited[ans[i]] = true;
			}
			

			System.out.println();
			return;
		}
		System.out.println("NO");
	}	
 
	private static void solve(){
		FastScanner in = new FastScanner();
 
		int n = in.nextInt();
		int m = in.nextInt();
 
		int a,b;
		Long c;
		int []degree = new int[n+1];
		Arrays.fill(degree, 0);

		for (int i=0; i<m; ++i) {
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextLong();
			Tuple t = new Tuple(a, b, c);
			edges.add(new Tuple(a, b, c));
			degree[b]++;
		}
 		
 		for(int i = 1; i < n+1; ++i) 
 			if(degree[i] == 0)
 				edges.add(new Tuple(0, i, 0L));
		
		bellmanFord(n);

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

class Tuple{
	int t1;
	int t2;
	Long t3;

	Tuple(int tt1, int tt2, Long tt3){
		this.t1 = tt1;
		this.t2 = tt2;
		this.t3 = tt3;
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