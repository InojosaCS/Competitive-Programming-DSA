import java.io.*; 
import java.util.*;

public class Labyrinth{
	
	static int N = 1002;
	static boolean graph[][] = new boolean[N][N];
	static int ai, aj, bi, bj, answer = -1;
	static int []dx = {1, 0, -1, 0};
	static int []dy = {0, 1, 0, -1};
	static Tuple [][]parent = new Tuple [N][N];
	static char []mapKey = {'D', 'U', 'R', 'L'};

	private static void bfs(int x, int y, int ans){
		Queue <Tuple> queue = new LinkedList<Tuple>();
		queue.add(new Tuple(x, y, ans));

		while(queue.size() > 0){
			Tuple current = queue.peek(); queue.remove();
			x = current.f;
			y = current.s;
			ans = current.t;
			if(!graph[x][y]) continue;			
			graph[x][y] = false;
			ans++;
			for (int  i = 0; i < 4; i++) {
				if(graph[x + dx[i]][y + dy[i]]){
					int c = -1;
					if(x < x + dx[i]) c = 0;
					if(x > x + dx[i]) c = 1;
					if(y < y + dy[i]) c = 2;
					if(y > y + dy[i]) c = 3;
					parent[x + dx[i]][y + dy[i]] = new Tuple(x, y, c);
					queue.add(new Tuple(x + dx[i], y + dy[i], ans));
					if(bi == x + dx[i] && bj == y + dy[i]){
						answer = ans;
						return;
					}
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
			for (int j = 0; j < m; j++) {
				char temp = line.charAt(j);
				if(temp == 'A'){
					ai = i+1; aj = j+1;
				}
				if(temp == 'B'){
					bi = i+1; bj = j+1;
				}
				graph[i+1][j+1] = (temp == '#') ? false : true;
			}
		}

		bfs(ai, aj, 0);

		if(answer == -1) {
			System.out.println("NO");
		} else {
			System.out.println("YES\n" + answer);
			char []res = new char[answer];
			int index = answer - 1, x = bi, y = bj;
			while(index > -1){
				Tuple current = parent[x][y];
				x = current.f;
				y = current.s;
				res[index] = mapKey[current.t];
				index--;
			}
			
			System.out.println(res);
		}
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

class Tuple{
	int f;
	int s;
	int t;
	Tuple(int x, int y, int z){
		this.f = x;
		this.s = y;
		this.t = z;
	}
}