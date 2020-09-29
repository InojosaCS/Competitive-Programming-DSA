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

void solve(){
	int n, m;
	cin >> n >> m;
	
	parent.resize(n);
	rankId.resize(n);
	
	for (int i = 0; i < n; i++)
		make_set(i);

}

int main(){
	ios_base::sync_with_stdio(false);cin.tie(0);
		
	int t = 1;
	// cin >> t;

	while(t--) solve();
	
	return 0;
}
