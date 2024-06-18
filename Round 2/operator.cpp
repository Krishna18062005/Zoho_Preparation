#include<bits/stdc++.h>
using namespace std;

int main(){
    string s;
    int n;
    cin >> s;
    n = s.size();
    string numbers = "", symbols = "";
    for(int i=0;i<n;i++){
        if(isdigit(s[i])) numbers += s[i]; 
        else symbols += s[i];
    }

    int ans = numbers[0] - '0';
    for(int i=1;i<numbers.size();i++){
        int num = numbers[i] - '0';
        if(symbols[i-1]=='+'){
            ans += num;
        }else if(symbols[i-1]=='-'){
            ans -= num;
        }else if(symbols[i-1]=='*'){
            ans *= num;
        }else if(symbols[i-1]=='/'){
            ans /= num;
        }
    }
    cout<<ans<<endl;
    return 0;
}