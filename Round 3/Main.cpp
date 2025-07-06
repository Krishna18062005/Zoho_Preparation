#include<iostream>
#include<fstream>
#include<sstream>
#include "Customer.h"
#include "Bank.cpp"
using namespace std;
Bank B;
void Initialize(){
    ifstream file("bank_db.txt");
    string line;
    int count=0;
    while(getline(file,line)){
         if (line.empty()) continue;
        
        int CustId,AccountNo,Balance;
        string Name, EncryptedPassword;
        istringstream iss(line);
        if (!(iss >> CustId >> AccountNo >> Name >> Balance >> EncryptedPassword)) {
        continue;
    }
        iss>>CustId>>AccountNo>>Name>>Balance>>EncryptedPassword;
        count++;
        Customer c = Customer(CustId,AccountNo,Name,Balance,EncryptedPassword);
        B.customers.push_back(c);
    }
    file.close();
    cout<<"Successfully Added "<<count<<" data "<<endl;
}

void Add(){
    B.AddCustomer();
}

void Login(){
    B.login();
}
void Withdraw(){
    B.withdraw();
}
void Deposit(){
    B.deposit();
}
void Transfer(){
    B.transfer();
}
void Display(){
    cout<<"CustID   AccountNo   Name    Balance    Password"<<endl;
    for(auto i:B.customers){
        cout<<i.CustId<<"\t"<<i.AccountNo<<"\t"<<i.Name<<"\t"<<i.Balance<<"\t"<<i.EncryptedPassword<<endl;
    }
}
void DisplayTransaction(){
    B.DisplayTransaction();
}

void Sort(){
    B.sort();
}
int main(){
    
    int choice=1;
    while(choice!=5){
        int n;
        cout<<"Enter \n1.Initialize \n2.Display \n3.Add customer \n4.Login \n5.ATM Withdrawal \n6.Cash Deposit \n7.Cash Transfer \n8.Transaction Details \n9.Exit"<<endl;
        cin>>n;
        switch (n)
        {
        case 1:
            Initialize();
            break;
        case 2:
            Display();
            break;
        case 3:
            Add();
            break;
        case 4:
            Login();
            break;
        case 5:
            Withdraw();
            break;
        case 6:
            Deposit();
            break;
        case 7:
            Transfer();
            break;
        case 8:
            DisplayTransaction();
            break;
        case 9:
            Sort();
            break;
        default:
            break;
        }
    }
}