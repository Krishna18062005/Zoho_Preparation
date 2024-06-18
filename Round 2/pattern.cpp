#include<bits/stdc++.h>
using namespace std;

// XXXXXXXX
// X000000X
// X0XXXX0X
// X0X00X0X
// X0X00X0X
// X0XXXX0X
// X000000X
// XXXXXXXX

int main(){
    int n, m;
    cin >> n >> m;

    int left = 0, right = m-1, top = 0, bottom = n-1;
    bool flag = false;
    vector<vector<char>> mat(n, vector<char>(m));

    while(left < right && top < bottom){
        for(int j=left;j<=right;j++){
            if(!flag){
                mat[top][j] = 'X';
                mat[bottom][j] = 'X';
            } 
            else{
                mat[top][j] = '0';
                mat[bottom][j] = '0';
            } 
        }
        for(int i=top;i<=bottom;i++){
            if(!flag){
                mat[i][top] = 'X';
                mat[i][bottom] = 'X';
            }else{
                mat[i][top] = '0';
                mat[i][bottom] = '0';
            }
        }
        left++, right--, top++, bottom--;
        flag = !flag;
    }

    for(auto it: mat){
        for(auto i: it){
            cout<<i;
        }
        cout<<endl;
    }
}