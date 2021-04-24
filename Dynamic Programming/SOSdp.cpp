/*
Sum over Subsets(SOS) using dynamic programming
Use: Given a fixed array A of 2^N integers, we need to calculate \forall x function F(x) = Sum of all A[i] such that x&i = i, i.e., i is a subset of x
Complexity: N * 2^N
Tested on: https://codeforces.com/gym/102576/problem/B
Based on: https://codeforces.com/blog/entry/45223
*/

#include<bits/stdc++.h>

using namespace std;

const int EXP = 21;

void solve(int test){
    int n;
    cin >> n;

    vector<int> dp(1 << EXP, 0);
    vector<int> a(n);

    for(int &x: a){
        cin >> x;
        dp[x]++;
    }

    for(int i = 0; i < EXP; i++){
        for(int mask = 0; mask < (1<<EXP); mask++){
            if(mask & (1<<i)){
                dp[mask] += dp[mask^(1<<i)];
            }
        }
    }

    long long ans = 0;

    for(int &x: a) ans += 1LL * dp[x];
    cout << ans << "\n";
}

int32_t main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int tests = 1;
    cin >> tests;

    for(int test = 1; test <= tests; test++){
        solve(test);
    }
}


