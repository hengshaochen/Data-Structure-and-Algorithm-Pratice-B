class CashRegister
{
public:
    CashRegister();
    ~CashRegister();

    // the customer has paid money,
    // that money is already in the till
    // Now, dispense change
    void MakeChange( double amountPaid, double amountOwed );
    bool dfsHelper(double startIndex, double currentAmount, double amountOwned);
private:

    // how much money is in the cash register?
    double TotalInRegister() const;

    // remove coins/bills from the till and give them to the customer
    void Dispense( denomination d, int count );

    // there is a problem!
    void ReportError( char *text ) const;

private:

    // the cash register holds zero or more of these
    // bills and coins in the Till.
    // The value of the enum is its worth in cents
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
    std::map< denomination, int > mTill;
    // store the valid answer of makeChange
    std:map< denomination, int > returnCoins;
    // build a mapping relationship for denomination
    int coin[] = {1, 5, 10, 25, 50, 100, 500, 1000, 2000, 5000, 10000};
    // total number of denomination
    const int numsDenomination = 11;
    
    // This is the LCD display on the cash register
    std::ostream mDisplay;
};

// -------------------------------------------------------
// Function:    CashRegister::TotalInRegister
// Purpose:     how much money is in the cash register?

double CashRegister::TotalInRegister() const
{
    int total(0);
    std::map< denomination, int >::iterator it = till.begin();
    for ( ; it != till.end(); it++ )
        total += ((int) it->first) * it->second;
    return total / 100.0;
}

// -------------------------------------------------------
// Function:    CashRegister::Dispense
// Purpose:     remove coins/bills from the till and give them to the customer

void CashRegister::Dispense( denomination d, int count )
{
    mTill[ d ] -= count;
}

// -------------------------------------------------------
// Function:    CashRegister::ReportError
// Purpose:     there is a problem!

void CashRegister::ReportError( char *text ) const
{
    // show the problem on the display 
    mDisplay << text;
}


// ******************************
//     <insert your code here>
// ******************************
// -------------------------------------------------------
// Function:    CashRegister::MakeChange
// Purpose:     the customer has paid money, that money is already in the till
//              Now, dispense change.

// Method:      Use returnCoins<denomination, int> to store one valid answer that is able to dispense change to customer.
//              Use Depth First Search + Greedy(Prefer to use Large denomination) to find all possible solution and if the current amount is equal to amountOwed AND all the amount of denomination in till > amount of denomination in returnCoins --> it is a valid answer.
void CashRegister::MakeChange( double amountPaid, double amountOwed ) {
    bool find = dfsHelper(numsDenomination - 1, 0, amountOwned);
    
    // print out the answer if exist
    map<denomination, int>::iterator it;
    if (find == true) {
        // found a valid answer, print it out.
        std::cout << "Here is your change:" << endl;
        it = returnCoins.begin();
        while (it != returnCoins.end()) {
            std::cout << "denomination: " << it->first << ", amount:" << it->second << endl;
            it++;
        }
    } else {
        std::cout << "does not have enough coins to makeChange" << endl;
    }
    
}

bool CashRegister::dfsHelper(double startIndex, double currentAmount, double amountOwned) {
    // base case
   if (currentAmount > amountOwned) {
       return false;
   }
   if (currentAmount == amountOwned) {
    // make sure all the amount of denomination in till > amount of denomination in returnCoins
    map<denomination, int>::iterator iter;
    iter = returnCoins.begin();
    while(iter != returnCoins.end()) {
        if (iter->second > mTill[(denomination)iter->first]) {
            return false;
        }
        iter++;
    }
    
    // remove the amount of denomination from till
    iter = returnCoins.begin();
    while(iter != returnCoins.end()) {
        Dispense((denomination)iter->first, iter->second);
        iter++;
    }
    return true;
}

    // Greedy + DFS
    for (int i = startIndex; i >= 0; i--) {
       currentAmount += coin[i];
       returnCoins[(denomination)coin[i]]++;

       bool find = dfsHelper(i, currentAmount, amountOwned);
        // if ALREADY found the valid solution, then return it directly 
        // (The customer does not have any requirement on the types of coins/bills returned as long as the total amount is correct.)
        if (find == true) {
            return true;
        }

        // backtracking
        currentAmount -= coin[i];
        returnCoins[(denomination)coin[i]]--;
    }
    return false;
}