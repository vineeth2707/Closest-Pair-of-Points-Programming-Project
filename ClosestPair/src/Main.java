import java.util.*;

public class Main {
    public static void main(String[] args) {

        // List<Point> points = new ArrayList<>();
        // points.add(new Point(1, 1));
        // points.add(new Point(4, 5));
        // points.add(new Point(3, 2));
        // points.add(new Point(7, 8));

        // // This array will store the closest pair of points found
        // Point[] closestPairPoints = new Point[2];

        // // Now calling closestPair with both required parameters
        // double minDistance = ClosestPair.closestPair(points, closestPairPoints);
        // System.out.println("Minimum Distance: " + minDistance);
        // System.out.println("Closest Points: (" + closestPairPoints[0].getX() + ", " + closestPairPoints[0].getY() +
        //                    ") and (" + closestPairPoints[1].getX() + ", " + closestPairPoints[1].getY() + ")");




        Random random = new Random();
        List<Point> allpoints = new ArrayList<>();
        // input array size
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
        int m = 10; // Number of iterations

        for (int size : sizes) {
            double BruteForceTotalTime = 0;
            double totalTimeOptimized = 0;

            Point[] closestPairPointsBruteForce = new Point[2];
            Point[] closestPairPointsOptimized = new Point[2];

            for (int i = 0; i < m; i++) {
                allpoints.clear();
                for (int j = 0; j < size; j++) {
                    allpoints.add(new Point(random.nextDouble(50000), random.nextDouble(50000)));
                }

                long startTime = System.nanoTime();
                ClosestPair.bruteForceClosestPair(new ArrayList<>(allpoints), closestPairPointsBruteForce);
                long endTime = System.nanoTime();
                BruteForceTotalTime += (endTime - startTime) / 1e6; // Convert to milliseconds

                startTime = System.nanoTime();
                ClosestPair.closestPair(new ArrayList<>(allpoints), closestPairPointsOptimized);
                endTime = System.nanoTime();
                totalTimeOptimized += (endTime - startTime) / 1e6; // Convert to milliseconds
            }
            double avgTimeBruteForce = BruteForceTotalTime / m;
            double avgTimeOptimized = totalTimeOptimized / m;

            System.out.println("Input Size: " + size);
            System.out.println("Average Runtime - Brute Force: " + avgTimeBruteForce + " ms");
            System.out.println("Closest Points - Brute Force: (" + closestPairPointsBruteForce[0].getX() + ", " +
                     closestPairPointsBruteForce[0].getY() + ") and (" + closestPairPointsBruteForce[1].getX() + ", " +
                    closestPairPointsBruteForce[1].getY() + ")");
            System.out.println("Minimum Distance - Brute Force: " + ClosestPair.getDistance(closestPairPointsBruteForce[0], closestPairPointsBruteForce[1]));
            
            System.out.println("Average Runtime - Optimized: " + avgTimeOptimized + " ms");
            System.out.println("Closest Points - Optimized: (" + closestPairPointsOptimized[0].getX() + ", " +
                            closestPairPointsOptimized[0].getY() + ") and (" + closestPairPointsOptimized[1].getX() + ", " +
                             closestPairPointsOptimized[1].getY() + ")");
            System.out.println("Minimum Distance - Optimized: " + ClosestPair.getDistance(closestPairPointsOptimized[0], closestPairPointsOptimized[1]));
            
            System.out.println();
        }
    }

}