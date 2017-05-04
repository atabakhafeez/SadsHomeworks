//
//  main.cpp
//  Cipher
//
//  Created by Atabak Hafeez on 04.05.17.
//  Copyright © 2017 Atabak Hafeez. All rights reserved.
//

#include <iostream>

using namespace std;

class EncryptionScheme {
protected:
    string IV;
public:
    virtual string encrypt(string input) = 0;
    virtual string decrypt(string input) = 0;
};

class BlockCipher {
protected:
    int s_chunk;
    int p_chunk;
    string key;
public:
    virtual string encrypt(string input) = 0;
    virtual string decrypt(string input) = 0;
};


int main(int argc, const char * argv[]) {
    return 0;
}
