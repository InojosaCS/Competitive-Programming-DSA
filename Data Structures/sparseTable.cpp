/*
Sparse  Table
Use: Answerig queries on immutable arrays for idempotent functions
Complexity: N*LogN + Q
Tested on: https://www.spoj.com/problems/RMQSQ/
Tested on: https://cses.fi/problemset/task/1647
Based on: https://www.youtube.com/watch?v=0jWeUdxrGm4&ab_channel=Errichto
*/

#include<bits/stdc++.h>

using namespace std;

const int MAXN = 200'007;
const int LOG = 18;
int m[MAXN][LOG];
int a[MAXN];

int fun(int x, int y){
    // Change this as needed, you can only use idempotent functions
    return min(x, y);
    return max(x, y);
    return gcd(x, y);
}

void buildST(int n){
    for(int i = 0; i < n; i++) m[i][0] = a[i]; // base case, min(i,i) = a[i]

    // Preprocessing O(N * Log N)
    for(int k = 1; k < LOG; k++){
        for(int i = 0; i + (1 << k) - 1 < n; i++){
            // take half and half of what you want
            m[i][k] = fun(m[i][k-1], m[i + (1 << (k-1))][k-1]);
        }
    }
}

int query(int l, int r){
    int lenght = r-l+1;
    int k = 31 - __builtin_clz(lenght); // Log(lenght) in O(1)
    return fun(m[l][k], m[r - (1<<k) + 1][k]);
}

void solve(int test){
    int n, q;
    cin >> n >> q;

    for(int i = 0; i < n; i++) cin >> a[i];
    buildST(n);

    for(int i = 0; i < q; i++){
        int l, r;
        cin >> l >> r;
        l--; r--;
        cout << query(l, r) << "\n";
    }
}

int32_t main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int tests = 1;
    //cin >> tests;

    for(int test = 1; test <= tests; test++){
        solve(tests);
    }
}

