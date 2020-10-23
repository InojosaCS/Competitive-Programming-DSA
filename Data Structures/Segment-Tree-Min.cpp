#include <bits/stdc++.h>

using namespace std;

struct segtree {
    
    int size;
    vector<pair<int,int>> mins;
    
    void init(int n){
        size = 1;
        while(size < n) size *= 2;
        mins.assign(2*size, {INT_MAX, 0});
    }    
    
    void build(vector<int> &a, int x, int lx, int rx){
        if(rx - lx == 1) {
            if(lx < (int) a.size()) mins[x] = {a[lx], 1};
            return;
        }
        int m = (lx + rx) / 2;
        build(a, 2 * x + 1, lx, m);
        build(a, 2 * x + 2, m, rx);

        int m1 = mins[2 * x + 1].first;
        int c1 = mins[2 * x + 1].second;
        int m2 = mins[2 * x + 2].first;
        int c2 = mins[2 * x + 2].second;

        if(m1 < m2) mins[x].second = c1;
        if(m2 < m1) mins[x].second = c2;
        if(m1 == m2) mins[x].second = c1 + c2;
        mins[x].first = min(m1, m2);
    }
    
    void build(vector<int> &a){
            build(a, 0, 0, size);
    }
    
    void set(int i, int v, int x, int lx, int rx){
        if(rx - lx == 1){
            mins[x] = {v, 1};
            return;
        }
        int m = (lx + rx) / 2;
        if(i < m){
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        
        int m1 = mins[2 * x + 1].first;
        int c1 = mins[2 * x + 1].second;
        int m2 = mins[2 * x + 2].first;
        int c2 = mins[2 * x + 2].second;

        if(m1 < m2) mins[x].second = c1;
        if(m2 < m1) mins[x].second = c2;
        if(m1 == m2) mins[x].second = c1 + c2;
        mins[x].first = min(m1, m2);
    }
    
    void set(int i, int v){
        set(i, v, 0, 0, size);
    }
    
    pair<int,int> minimum(int l, int r, int x, int lx, int rx){
        if(lx >= r || rx <= l) return {INT_MAX, 0};
        if(l <= lx && rx <= r) return mins[x];
        int m = (lx + rx) / 2; 
        pair<int,int> a = minimum(l, r, 2 * x + 1, lx, m);
        pair<int,int> b = minimum(l, r, 2 * x + 2, m, rx);
        if(a.first < b.first) return a;
        if(a.first > b.first) return b;
        return {a.first, b.second + a.second};
    }
    
    
    pair<int,int> minimum(int l, int r){
        return minimum(l, r, 0, 0, size);
    }
    
};


void solve(){
    int n,m;
    cin >> n >> m;
    
    segtree st;
    st.init(n);
    vector<int> a(n);
    
    for (int i = 0; i < n; i++)
        cin >> a[i];
        
    st.build(a);
     
    for (int i = 0; i < m; i++)
    {
        int op;
        cin >> op;
        if(op == 1){
            int pos,v;
            cin >> pos >> v;
            st.set(pos, v);
        } else {
            int l,r;
            cin >> l >> r;
            pair<int,int> p = st.minimum(l, r);
            cout << p.first << " " << p.second <<  "\n";
        }
    }
    
}

int32_t main(){
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int32_t tt = 1;
	//cin >> tt;

	while(tt-->0) solve();
	
	return 0;
}
