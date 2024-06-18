#include<bits/stdc++.h>
using namespace std;

// Input
// 1    2    3
// 4    5    6
// 7    8    9

// Output:
// 4    1    2
// 7    5    3
// 8    9    6

int main(){
    int n, m;
    cin >> n >> m;
    vector<vector<int>> arr(n, vector<int>(m));
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> arr[i][j];
        }
    } 
    
    

}