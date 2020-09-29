#include <bits/stdc++.h>
 
using namespace std;

vector<int> parent, rankId;

void make_set(int v) {
    parent[v] = v;
    rankId[v] = 0;
}

int find_set(int v) {
    if (v == parent[v])
        return v;
    return parent[v] = find_set(parent[v]);
}

void union_sets(int a, int b) {
    a = find_set(a);
    b = find_set(b);
    if (a != b) {
        if (rankId[a] < rankId[b])
            swap(a, b);
        parent[b] = a;
        if (rankId[a] == rankId[b])
            rankId[a]++;
    }
}

struct Edge {
    int u, v;
    int64_t weight;
    bool operator<(Edge const& other) {
        return weight < other.weight;
    }
};

void solve(){
	int n, m;
	cin >> n >> m;
	
	vector<Edge> edges(m);
	
	for (int i = 0; i < m; i++){
		int64_t a,b,c;
		cin >> a >> b >> c;
		a--; b--;
		edges[i].u = a;
		edges[i].v = b;
		edges[i].weight = c;
	}

	int64_t cost = 0;
	vector<Edge> result;
	parent.resize(n);
	rankId.resize(n);
	for (int i = 0; i < n; i++)
		make_set(i);

	sort(edges.begin(), edges.end());

	for (Edge e : edges) {
		if (find_set(e.u) != find_set(e.v)) {
			cost += e.weight;
			result.push_back(e);
			union_sets(e.u, e.v);
		}
	}
	
	int x = find_set(0);
	
	for (int i = 0; i < n; i++)
	{
		if (x != find_set(i))
		{
			cout << "IMPOSSIBLE\n";
			return;
		}
	}
	
	cout << cost << "\n";
}

int main(){
	ios_base::sync_with_stdio(false);cin.tie(0);
		
	int t = 1;
	// cin >> t;

	while(t--) solve();
	
	return 0;
}
