#ifndef TRANSACTION_H
#define TRANSACTION_H
#include<iostream>
using namespace std;

class Transaction{
    public:
    int transID;
    string transType;
    int AccountNo;
    int cust_id;
    int amount;
    int balance;
    Transaction(int transID, int AccountNo, int cust_id,  string transType,int amount, int balance ){
        this->transID=transID;
        this->transType=transType;
        this->AccountNo=AccountNo;
        this->balance=balance;
        this->amount=amount;
        this->cust_id=cust_id;
    }
};

#endif