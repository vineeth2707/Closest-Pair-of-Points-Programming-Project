import java.util.*;

public class ClosestPair {
    /**
     * Finds the closest pair of points using a divide-and-conquer approach.
     *
     * @param allpoints The list of points for which the closest pair needs to be found.
     * @param closestPairPointsOptimized Array to store the closest pair of points found.
     * @return The distance between the closest pair of points.
     */
    public static double closestPair(List<Point> allpoints, Point[] closestPairPointsOptimized) {
        if (allpoints.size() < 2) return 0.0; // Handling cases where there are fewer than two points.

        List<Point> pointsSortedByX = new ArrayList<>(allpoints);
        List<Point> pointsSortedByY = new ArrayList<>(allpoints);
        mergeSort(pointsSortedByX, 0, allpoints.size() - 1, true);  // sorting by X
        mergeSort(pointsSortedByY, 0, allpoints.size() - 1, false); // sorting by Y
        return closestPairFinder(pointsSortedByX, pointsSortedByY, allpoints.size(), closestPairPointsOptimized);
    }

    /**
     * Helper method to recursively find the closest pair of points.
     */
    private static double closestPairFinder(List<Point> pointsSortedByX, List<Point> pointsSortedByY, int n, Point[] closestPairPointsOptimized) {
        if (n <= 3) {
            return bruteForceClosestPair(pointsSortedByX, closestPairPointsOptimized);
        }

        int mid = n / 2;
        Point midPoint = pointsSortedByX.get(mid);

        List<Point> leftPointsSortedByX = new ArrayList<>(pointsSortedByX.subList(0, mid));
        List<Point> rightPointsSortedByX = new ArrayList<>(pointsSortedByX.subList(mid, n));
        List<Point> tempPointsSortedByY = new ArrayList<>(pointsSortedByY);
        List<Point> leftPointsSortedByY = new ArrayList<>();
        List<Point> rightPointsSortedByY = new ArrayList<>();

        for (Point point : tempPointsSortedByY) {
            if (point.getX() <= midPoint.getX()) {
                leftPointsSortedByY.add(point);
            } else {
                rightPointsSortedByY.add(point);
            }
        }

        double distanceLeft = closestPairFinder(leftPointsSortedByX, leftPointsSortedByY, mid, closestPairPointsOptimized);
        double distanceRight = closestPairFinder(rightPointsSortedByX, rightPointsSortedByY, n - mid, closestPairPointsOptimized);
        double minDistance = Math.min(distanceLeft, distanceRight);

        return stripClosest(pointsSortedByY, midPoint, minDistance, closestPairPointsOptimized);
    }

    /**
     * A method to find the closest distance in a strip defined by a given distance around a midpoint.
     */
    private static double stripClosest(List<Point> strip, Point midPoint, double minDistance, Point[] closestPairPointsOptimized) {
        double min = minDistance;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).getY() - strip.get(i).getY()) < min; j++) {
                double distance = getDistance(strip.get(i), strip.get(j));
                if (distance < min) {
                    min = distance;
                    closestPairPointsOptimized[0] = strip.get(i);
                    closestPairPointsOptimized[1] = strip.get(j);
                }
            }
        }
        return min;
    }

    /**
     * Brute force approach to find the minimum distance between points.
     */
    public static double bruteForceClosestPair(List<Point> points, Point[] closestPairPointsBruteForce) {
        double minDistance = Double.MAX_VALUE;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = getDistance(points.get(i), points.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPairPointsBruteForce[0] = points.get(i);
                    closestPairPointsBruteForce[1] = points.get(j);
                }
            }
        }

        return minDistance;
    }

    /**
     * A utility method to calculate the Euclidean distance between two points.
     */
    public static double getDistance(Point first, Point second) {
        return Math.sqrt((first.getX() - second.getX()) * (first.getX() - second.getX()) +
                (first.getY() - second.getY()) * (first.getY() - second.getY()));
    }

    /**
     * Generic merge sort method to sort points either by X or Y coordinates.
     */
    private static void mergeSort(List<Point> points, int l, int r, boolean sortByX) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(points, l, m, sortByX);
            mergeSort(points, m + 1, r, sortByX);
            merge(points, l, m, r, sortByX);
        }
    }

    /**
     * Helper method to merge two halves of a list sorted by either X or Y coordinates.
     */
    private static void merge(List<Point> points, int l, int m, int r, boolean sortByX) {
        int n1 = m - l + 1;
        int n2 = r - m;

        List<Point> left = new ArrayList<>(n1);
        List<Point> right = new ArrayList<>(n2);

        for (int i = 0; i < n1; i++)
            left.add(points.get(l + i));
        for (int j = 0; j < n2; j++)
            right.add(points.get(m + 1 + j));

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if ((sortByX && left.get(i).getX() <= right.get(j).getX()) ||
                (!sortByX && left.get(i).getY() <= right.get(j).getY())) {
                points.set(k, left.get(i));
                i++;
            } else {
                points.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            points.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            points.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
