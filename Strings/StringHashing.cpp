const int N = 1000123;
const ll p = 31;
const ll m = 122749283;
const ll m2 = 459059071;

vector<long long> p_pow(N);
vector<long long> inv_pow(N);
vector<long long> h(N + 1, 0LL);
vector<long long> p_pow2(N);
vector<long long> inv_pow2(N);
vector<long long> h2(N + 1, 0LL);

ll ppow(ll a, ll b, ll x){
	if(b == 0) return 1LL;
	if(b == 1) return a;
	return ppow(a*a%x, b/2, x) * (b%2 ? a : 1LL) % x;
}

ll inv(ll var, ll x){
	return ppow(var, x-2, x) % x;
}

void stringHashing(string const& s){
    int n = s.size();

    inv_pow[0] = p_pow[0] = 1LL;
    inv_pow2[0] = p_pow2[0] = 1LL;
	  ll pinv = inv(p,m);
	  ll pinv2 = inv(p,m2);
	
    for (int i = 1; i < n; i++){
        p_pow[i] = (p_pow[i-1] * p) % m;
        inv_pow[i] = (inv_pow[i-1] * pinv) % m;
        p_pow2[i] = (p_pow2[i-1] * p) % m2;
        inv_pow2[i] = (inv_pow2[i-1] * pinv2) % m2;
	  }
	
    for (int i = 0; i < n; i++){
        h[i+1] = (h[i] + (s[i] - 'a' + 1) * p_pow[i]) % m;
        h2[i+1] = (h2[i] + (s[i] - 'a' + 1) * p_pow2[i]) % m2;
	  }
}
