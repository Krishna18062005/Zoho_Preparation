#include<iostream>
#include<bits/stdc++.h>
#include<fstream>
#include "Customer.h"
using namespace std;
bool authenticate=false;
static int custId=56;
static int AccountNo=55056;
Customer* selected;
class Bank{
    public:
    vector<Customer>customers;

    string encrypt(string s){
        string result;
        for(auto i:s){
            if(i=='Z'){
                result+='A';
            }else if(i=='z'){
                result+='a';
            }else{
                result+=i+1;
            }
           
        }
         return result;
    }

    bool decrypt(string s, string pwd){
        string result;
        for(auto i:s){
            if(i=='Z'){
                result+='A';
            }else if(i=='z'){
                result+='a';
            }else{
                result+=(i-1);
            }
        }
        return pwd==result;
    }
    void AddCustomer(){
        string name,pwd,retype_pwd;
        cout<<"Enter the customer name:"<<endl;
        cin>>name;
        cout<<"Enter the password"<<endl;
        cin>>pwd;
        cout<<"Enter the retyped password:"<<endl;
        cin>>retype_pwd;
        int balance=10000;
        custId=custId+1;
        AccountNo+=1;
        if(pwd==retype_pwd){
            string e_pwd=encrypt(pwd);
            Customer C =  Customer(custId,AccountNo,name,balance,e_pwd);
            C.Transactions.push_back(Transaction(1,custId,AccountNo,"Opening",balance,balance));
            customers.push_back(C);
            
            ofstream file("bank_db.txt",ios::app);
            if(file.is_open()){
                file<<"\n"<<custId<<"     "<<AccountNo<<"     "<<name<<"      "<<balance<<"       "<<e_pwd<<endl<<" \n";
                file.close();
            }
        cout<<"Data written successfully"<<endl;
        }else{
            cout<<" Passwords do not match"<<endl;
        }

       
        
    }

    void login(){
        int cust_id;
        string pwd;
        cout<<"Enter customer id:"<<endl;
        cin>>cust_id;
        cout<<"Enter password:"<<endl;
        cin>>pwd;
        for(auto& i:customers){
            if(i.CustId==cust_id&&decrypt(i.EncryptedPassword,pwd)){
                authenticate=true;
                selected =&i;
                cout<<" User logged in successfully "<<endl;
                return ;
            }
        }
        cout<<" Invalid Customer ID or password"<<endl;
    }
    void withdraw(){
        if(!authenticate){
            cout<<" User should be authenticated "<<endl;
            return;
        }
        int amount;
        cout<<" Enter the amount to Withdraw "<<endl;
        cin>>amount;
        if(amount>selected->Balance){
            cout<<" Insufficient Balance "<<endl;
            return ;
        }
        if(selected->Balance - amount < 1000){
            cout<<" Minimum Balance of 1000 is required."<<endl;
            return ;
        }
        selected->Balance-=amount;
        cout<<" Amount withdrawn successfully "<<endl;
    }

    void deposit(){
        if(!authenticate){
            cout<<" User should be authenticated "<<endl;
            return;
        }
        int amount;
        cout<<"Enter the amount to deposit: "<<endl;
        cin>>amount;
        selected->Balance+=amount;
        int transID=selected->Transactions.size()+1;
        selected->Transactions.push_back(Transaction(transID,selected->AccountNo,selected->CustId,"Deposit",amount,selected->Balance));
        cout<<" Amount Deposited successfully "<<endl;
    }

    void transfer(){
         if(!authenticate){
            cout<<" User should be authenticated "<<endl;
            return;
        }
        int from,to;
        cout<<" Enter FROM account no:"<<endl;
        cin>>from;
        cout<<" Enter To account no: "<<endl;
        cin>>to;
        Customer * selected_from=nullptr;
        Customer * selected_to = nullptr; 
        for(auto&i:customers){
            if(i.CustId==from){
                selected_from = &i;
            }
            else if(i.CustId==to){
                selected_to =&i;
            }
        }
        if(selected_from==nullptr){
            cout<<" sender ID is incorrect "<<endl;
            return;
        }
        if(selected_to==nullptr){
            cout<<" Receiver ID is not found "<<endl;
            return ;
        }
        int amount;
        cout<<" Enter the amount to transfer "<<endl;
        cin>>amount;
        if(selected_from->Balance-amount<1000){
            cout<<" Insufficient Balance "<<endl;
            return;
        }
        selected_from->Balance-=amount;
        selected_to->Balance+=amount;
        cout<<" Transaction successful "<<endl;

    }
    void DisplayTransaction(){
        int custId;
        cout<<" Enter customer ID:"<<endl;
        cin>>custId;
        for(auto i:customers){
            if(i.CustId==custId){
                cout<<"-------------------------------"<<endl;
                cout<<"Name: "<<i.Name<<endl;
                cout<<"Account No: "<<i.AccountNo<<endl;
                cout<<"Customer Id:"<<i.CustId<<endl;
                cout<<"TransID\t"<<"Transtype\t"<<"Amount\t"<<"Balance\t"<<endl;
                for(auto t:i.Transactions){
                    cout<<t.transID<<"\t"<<t.transType<<"\t\t"<<t.amount<<"\t"<<t.balance<<endl;
                }
            }
        }
        
        
    }
    void sort(){
        int n;
        cout<<" Enter N "<<endl;
        cin>>n;
        if(n>customers.size()){
            cout<<" N is greater than the total count "<<endl;
            return ;
        }
        for(int i=0;i<customers.size()-1;i++){
            for(int j=0;j<customers.size()-i-1;j++){
                if(customers[j].Balance<customers[j+1].Balance){
                    Customer temp = customers[j];
                    customers[j]=customers[j+1];
                    customers[j+1]=temp;
                
                }
            }
        }
        cout<<" CustID\tBalance "<<endl;
        for(int i=0;i<n;i++){
            cout<<customers[i].CustId<<"\t"<<customers[i].Balance<<endl;
        }
    }
};