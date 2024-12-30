# Closest-Pair-of-Points-Programming-Project
Project Overview
This repository presents an in-depth analysis of two algorithms used to solve the Closest Pair of Points problem in computational geometry. The project compares a brute-force method (ALG1) with a divide-and-conquer strategy (ALG2) to determine the shortest distance between points in a 2D space. The study provides insights into the time complexities, experimental results, and scalability of these algorithms.

Features
Brute-Force Approach (ALG1):

Computes distances between all pairs of points using a quadratic time complexity 
𝑂
(
𝑛
2
)
O(n 
2
 ).
Suitable for small datasets or cases where simplicity is prioritized.
Divide-and-Conquer Approach (ALG2):

Optimized to run with a time complexity of 
𝑂
(
𝑛
log
⁡
𝑛
)
O(nlogn), leveraging techniques like merge sort.
Efficient for large datasets, with scalability and reduced runtime.
Applications:

Sensor localization
Autonomous vehicle navigation
Obstacle avoidance
Robotic motion planning
Nearest-neighbor classification
Experimental Analysis
Dataset:

Input sizes range from 10,000 to 100,000 points, incremented by 10,000.
Key Findings:

ALG2 consistently outperforms ALG1 for larger datasets.
Minor variations observed between empirical and predicted runtimes across both algorithms.
ALG1 is faster for very small datasets but lacks scalability.
Graphical Visualizations:

Empirical vs. predicted runtimes for ALG1 and ALG2.
Comparative runtime analysis showcasing the exponential growth of ALG1 and the efficiency of ALG2.
Output and Conclusion
The results validate the divide-and-conquer approach as the superior method for handling large-scale problems while highlighting the practical relevance of brute-force for small-scale scenarios. This study emphasizes the importance of choosing algorithms based on dataset size and computational constraints.

Additional Resources
Project Demo : https://www.youtube.com/watch?v=JPkZqrL0U4Q 
References:
M. I. Shamos and D. Hoey, "Closest-point problems," 1975.
J. Kleinberg and E. Tardos, "Algorithm Design," 2005.
GeeksForGeeks and other online resources.
