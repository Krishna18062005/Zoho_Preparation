#ifndef CUSTOMER_H
#define CUSTOMER_H

#include<iostream>
#include "Transaction.h"
#include<bits/stdc++.h>
using namespace std;

class Customer{
    public:
    int CustId;
    int AccountNo;
    string Name;
    int Balance;
    string EncryptedPassword;
    vector<Transaction>Transactions;
    Customer(int CustId, int AccountNo, string Name, int Balance, string EncryptedPassword){
        this->CustId=CustId;
        this->AccountNo=AccountNo;
        this->Name=Name;
        this->Balance=Balance;
        this->EncryptedPassword=EncryptedPassword;
    }
};

#endif