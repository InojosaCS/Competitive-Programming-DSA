const ll MX = 200123;
vector<ll> primes;
vector<bool> isPrime(MX, true);

void sieve(){
	for (int i = 4; i < MX; i+=2) isPrime[i] = false;
	
	for (int i = 3; i < MX; i+=2)
		if(isPrime[i])
			for (int j = i+i; j < MX; j+=i)
				isPrime[j] = false;	
	
	for (int i = 2; i < MX; i++)
		if(isPrime[i]) primes.push_back(i);
}
