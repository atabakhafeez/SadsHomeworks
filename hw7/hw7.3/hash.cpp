
#include <iostream>
#include <inttypes.h>
#include <unordered_map>
#include <vector>
#include <climits>

using namespace std;


static const char alphanum[] =
"0123456789"
"!@#$%^&*"
"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
"abcdefghijklmnopqrstuvwxyz";

int stringLength = sizeof(alphanum) - 1;

string password(unsigned int length){
	string str = "";
	for(unsigned int i = 0; i < length; ++i) {
     str += alphanum[rand() % stringLength];
	}
    return str;
}

uint64_t pow_mod(uint64_t x){
	for (int i = 0; i < 1234567; i++){
		x *= x;
		x %= 9993201131;
	}
	return x;
}


uint64_t my_hash(vector<unsigned long> w){
	uint64_t h = 0;
	for (unsigned int j = 0; j < w.size(); j++){
		h = pow_mod(h + 2 + w[j]);
	}
	cout << w[0] << " => " << h << endl;
	return h;
}


int main (){
	unordered_map<uint64_t, unsigned long> my_map;
	vector<unsigned long> w;
	vector<string> passwords;
	std::string pass;
	unsigned int length = 8;
	for (unsigned int i = 0; i < 10000; i++) {
		pass = password(length);
		cout << pass << endl;
		passwords.push_back(pass);
	}

	for (unsigned int i = 0; i < passwords.size(); i++) {
		pass = passwords[i];
		for (unsigned int j = 0; j < length; j++) {
			unsigned long d = int(pass[i]);
			cout << d << endl;
		}
	}

	return 0;
}

