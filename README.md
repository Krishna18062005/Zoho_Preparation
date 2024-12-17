---

# GitHub Profile Analyzer

A Python script to fetch and analyze public GitHub profile information, including user details and repository statistics. It uses the GitHub API to retrieve data such as follower counts, repositories, stars, and forks.

## Features

- Fetch basic user information (e.g., name, public repositories, followers, location).
- Analyze repository data for each user.
- Print details about stars, forks, and repositories in a clean tabular format.
- Process multiple GitHub usernames in sequence.

## Prerequisites

Before running the script, ensure you have:

1. **Python** installed (version 3.6+ recommended).
2. Required libraries:
   - `requests` for making HTTP API requests.
   - `pandas` for handling and displaying tabular data.
   - `matplotlib` (optional) for future visualization.

To install dependencies, use the following command:

```bash
pip install requests pandas matplotlib
```

## Usage

1. Clone or download this repository:

   ```bash
   git clone https://github.com/yourusername/github-profile-analyzer.git
   cd github-profile-analyzer
   ```

2. Add GitHub usernames you want to analyze in the `db` list within the script.

3. Run the script:

   ```bash
   python github_analyzer.py
   ```

4. The script will fetch and print the following:
   - User profile details (name, followers, public repositories, etc.)
   - Repository data (name, stars, forks).

### Example Output

```
User Information:
Username: Krishna18062005
Name: Krishna
Public Repos: 10
Followers: 5
Following: 3
Created At: 2021-06-18T00:00:00Z
Location: N/A

Repository Information:
          Repo Name  Stars  Forks
0     Project-One      10      3
1     Repo-Two         8       2
2     Analysis-App     5       1
```

## Code Overview

### `get_github_data(username)`

- Fetches user details and repositories using the GitHub API.
- Handles API errors and missing data gracefully.
- Returns user information as a dictionary and repository data as a Pandas DataFrame.

### `main()`

- Loops through a list of GitHub usernames.
- Calls the `get_github_data` function.
- Displays user and repository information for each username.

## Notes

- If a username doesn't exist or has no public repositories, the script handles it gracefully.
- The GitHub API has a rate limit (60 requests per hour for unauthenticated users). For higher limits, you can [authenticate with a token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token).

## Future Enhancements

- Add visualizations for stars, forks, and language distribution using `matplotlib`.
- Support authentication for higher API rate limits.
- Export repository statistics to a CSV file for further analysis.

---
