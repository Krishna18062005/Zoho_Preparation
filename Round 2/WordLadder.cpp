// C++ program to find length 
// of the shortest chain
// transformation from source
// to target
#include <bits/stdc++.h>
using namespace std;

// Returns length of shortest chain 
// to reach 'target' from 'start'
// using minimum number of adjacent 
// moves. D is dictionary
int shortestChainLen( string start, string target, set<string>& D){

    if(start == target)
        return 0;

    // Map of intermediate words and 
    // the list of original words
    map<string, vector<string>> umap;

    // Find all the intermediate 
    // words for the start word
    for(int i = 0; i < start.size(); i++){
        string str = start.substr(0,i) + "*" + start.substr(i+1);
        umap[str].push_back(start);
    }

    // Find all the intermediate words for 
    // the words in the given Set
    for(auto it = D.begin(); it != D.end(); it++)
    {
        string word = *it;
        for(int j = 0; j < word.size(); j++){
            string str = word.substr(0,j) + "*" + word.substr(j+1);
            umap[str].push_back(word);
        }
    }

    // Perform BFS and push (word, distance)
    queue<pair<string, int>> q;

    map<string, int> visited;

    q.push(make_pair(start,1));
    visited[start] = 1;

    // Traverse until queue is empty
    while(!q.empty())
    {
        pair<string, int> p = q.front();
        q.pop();

        string word = p.first;
        int dist = p.second;

        // If target word is found
        if(word == target){
            return dist;
        }

        // Finding intermediate words for 
        // the word in front of queue
        for(int i = 0; i < word.size(); i++){
            string str = word.substr(0,i) + "*" + 
                                word.substr(i+1);

            vector<string> vect = umap[str];
            for(int j = 0; j < vect.size(); j++)
            {
                // If the word is not visited
                if(visited[vect[j]] == 0){
                    visited[vect[j]] = 1;
                    q.push(make_pair(vect[j], dist + 1));
                }
            }
        }

    }

        return 0;
}

// Driver code
int main()
{
	// Make dictionary
	set<string> D;
	D.insert("poon");
	D.insert("plee");
	D.insert("same");
	D.insert("poie");
	D.insert("plie");
	D.insert("poin");
	D.insert("plea");
	string start = "toon";
	string target = "plea";
	cout << "Length of shortest chain is: "
		<< shortestChainLen(start, target, D);
	return 0;
}
