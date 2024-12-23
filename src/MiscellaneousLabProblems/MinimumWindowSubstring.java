package MiscellaneousLabProblems;

import java.util.HashMap;
import java.util.Map;

/*
Problem Description
Given two strings A and B of lengths m and n respectively,
return the minimum window substring of A such that every character in B
(including duplicates) is included in the window. If there is no such substring, return "-1".
When there are multiple answers, return the one which appears first in A.

Problem Constraints
m == A.length
n == B.length
1 <= m, n <= 10^5
A and B consist of uppercase and lowercase English letters.

Input Format
First argument is a string A and second argument is a string B.

Output Format
Return a string.

Example Input
Input 1:
A = "ADOBECODEBANC"
B = "ABC"

Input 2:
A = "abaca"
B = "aa"

Input 3:
A = "a"
B = "aa"

Example Output:
Output 1: "BANC"
Output 2: "aba"
Output 3: "-1"

Example Explanation:
Explanation 1:
 The minimum window substring "BANC" includes 'A', 'B', and 'C' from string B.

Explanation 2:
Both "aba" and "aca" are the shortest substrings of A that contain all characters of B.
However, since "aba" appears before "aca" in A, we return "aba".

Explanation 3:
Both 'a's from B must be included in the window.
Since the largest window of A only has one 'a', return "-1".

 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String A = "ADOBECODEBANC";
        String B = "ABC";

        String res = find(A, A.length(), B, B.length());
        System.out.println(res);
        // Time O(N);
        // Space O(N);
    }

    public static String find(String A, int aN, String B, int bN) {
        HashMap<Character, Integer> hmA = new HashMap<>();
        HashMap<Character, Integer> hmB = new HashMap<>();

        for (int i = 0; i < bN; i++) {
            char el = B.charAt(i);
            hmB.put(el, hmB.getOrDefault(el, 0) + 1);
        }

        int l = 0;
        int r = 0;
        int finalL = -1;
        int finalR = -1;
        int min = Integer.MAX_VALUE;

        while (r < aN) {
            while (r < aN && !isMatch(hmA, hmB)) {
                char ch = A.charAt(r);
                hmA.put(ch, hmA.getOrDefault(ch, 0) + 1);
                r++;
            }

            while (isMatch(hmA, hmB)) {
                if (r - l < min) {
                    min = r - l;
                    finalL = l;
                    finalR = r;
                }
                char ch = A.charAt(l);
                hmA.put(ch, hmA.get(ch) - 1);
                if (hmA.get(ch) == 0) hmA.remove(ch);
                l++;
            }
        }

        if (finalL == -1 && finalR == -1) return "-1";
        else return A.substring(finalL, finalR);
    }

    public static boolean isMatch(HashMap<Character, Integer> a, HashMap<Character, Integer> b) {
        if (a.size() < b.size()) return false;
        for (Map.Entry<Character, Integer> entry : b.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if (a.getOrDefault(key, 0) < val) return false;
        }
        return true;
    }
}
