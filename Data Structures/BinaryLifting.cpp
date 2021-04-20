/*
Binary Lifting
Use: Find kth ancestor of a node in a tree
Complexity: N*LogN + Q*LogN
Tested on: https://cses.fi/problemset/result/2013135/
Based on: https://youtu.be/oib-XsjFa-M
*/

#include<bits/stdc++.h>

using namespace std;

const int MAXN = 200'007;
const int LOG = 20;
vector<int> parent(MAXN, 0);
vector<int> depth(MAXN, 0);
vector<vector<int>> g(MAXN);
vector<vector<int>> up(MAXN, vector<int>(LOG, -1));

void dfs(int u, int prev, int d){
    depth[u] = d++;
    up[u][0] = prev;

    for(int x: g[u]){
        if(x != prev){
            parent[x] = u;
            dfs(x, u, d);
        }
    }
}

void buildBL(int root, int n){
    // N * Log N preprocesssing
    for(int j = 1; j < LOG; j++){
        for(int v = 0; v < n; v++){
            up[v][j] = up[ up[v][j-1] ][j-1];
        }
    }
}

int query(int node, int k){
    if(depth[node] < k) return -2; 
    for(int i = LOG - 1; i >= 0; i--){
        if(k & (1 << i)){
            node = up[node][i];
            k -= 1 << i;
        }
    }
    return node;
}

void solve(int test){
    int n, q;
    cin >> n >> q;

    for(int i = 1; i <= n-1; i++){
        int v; cin >> v; v--;
        g[i].push_back(v);
        g[v].push_back(i);
    }

    dfs(0, 0, 0);
    buildBL(0, n);

    for(int i = 0; i < q; i++){
        int x, k;
        cin >> x >> k;
        x--;
        cout << query(x, k) + 1 << "\n";
    }
}

int32_t main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int tests = 1;
    //cin >> tests;

    for(int test = 1; test <= tests; test++){
        solve(test);
    }
}

