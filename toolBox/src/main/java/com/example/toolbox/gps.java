package com.example.toolbox;

public class gps {
    public double getDistanceBetweenTwoCoordinate(double long1, double lat1, double long2, double lat2){
        int REarth = 6371;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);
        double deltaLat = lat2 - lat1;
        double deltaLong = long2 - long1;
        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLong/2) * Math.sin(deltaLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // Return Distance
        return REarth * c;
    }
}
