#include <iostream>
using namespace std;

    enum denomination
    {
        kPenny = 1,
        kNickel = 5,
        kDime = 10,
        kQuarter = 25,
        kHalf = 50,
        kDollar = 100,
        kFive = 500,
        kTen = 1000,
        kTwenty = 2000,
        kFifty = 5000,
        kHundred = 10000
    };

    // This is the till.  All bills and coins are stored here
    map< denomination, int > mTill;
    map< denomination, int > amountCoin;
    // enum相當於把kPenny的值定義成1
    // coin[0]的值是1, 可以存取到denomination中的kPenny
    int coin[] = {1, 5, 10, 25, 50, 100, 500, 1000, 2000, 5000, 10000};
    //size_t sizeCoinArray = sizeof(coin) / sizeof(coin[0]);
    const int numsDenomination = 11;


 bool dfs(int startIndex, int currentAmount, int amountOwned) {
    // base case
     if (currentAmount > amountOwned) {
         return false;
     }
     if (currentAmount == amountOwned) {
         // 遍歷Map判斷是否Till中每種面值是否皆有足夠的數量
        map<denomination, int>::iterator iter;
        iter = amountCoin.begin();
        while(iter != amountCoin.end()) {
            if (iter->second > mTill[(denomination)iter->first]) {
                return false;
            }
            iter++;
        }
         
        // 從mTill中扣除
        iter = amountCoin.begin();
        while(iter != amountCoin.end()) {
            mTill[(denomination)iter->first] -= iter->second;
            iter++;
        }
        return true;
     }
     
     for (int i = startIndex; i < numsDenomination; i++) {
         currentAmount += coin[i];
         amountCoin[(denomination)coin[i]]++;
         
         bool find = dfs(startIndex, currentAmount, amountOwned);
         // 如果找到了一組解，直接回傳這組解就可以
         if (find == true) {
             return true;
         }
         
         currentAmount -= coin[i];
         amountCoin[(denomination)coin[i]]--;
     }
     
     return false;
}


int main() {
    
    int amountOwned = 12;
    for (int i = 0; i < numsDenomination; i++) {
        mTill[(denomination)coin[i]] = 100;
    }
        mTill[(denomination)coin[0]] = 2;
        mTill[(denomination)coin[1]] = 0;
    
    map< denomination, int > returnCoin;
    bool find = dfs(0, 0, amountOwned);
    map<denomination, int>::iterator iter2;
    if (find == true) {
        // 全部面值數量都夠 --> 印出找到的答案
        cout << "customer return coin:" << endl;
        iter2 = amountCoin.begin();
         while (iter2 != amountCoin.end()) {
            cout << iter2->first << " : " << iter2->second << endl;
            iter2++;
         }
    } else {
        cout << "don't have enough coin to makeChange" << endl;
    }
    
    cout << "mTill remaining coin:" << endl;
    iter2 = mTill.begin();
    while (iter2 != mTill.end()) {
        cout << iter2->first << " : " << iter2->second << endl;
        iter2++;
    }
}

    
