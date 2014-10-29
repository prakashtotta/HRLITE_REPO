package com.util;

public class ProximityUtil
{
  public double lati = 0.0D;
  public double longi = 0.0D;
  public double dist = 0.0D;
  double lati_dist = 0.0D;
  double long_dist = 0.0D;
  
  public ProximityUtil(double lati, double longi, double dist)
  {
    this.lati = lati;
    this.longi = longi;
    this.dist = dist;
    this.lati_dist = convertDistToLatiDistance(dist);
    this.long_dist = convertDistToLongiDistance(dist);
  }
  
  public double convertDistToLatiDistance(double dist)
  {
    double Lati_Miles = 69.444000000000003D;
    double latidist = dist / Lati_Miles;
    return latidist;
  }
  
  public double convertDistToLongiDistance(double dist)
  {
    double Longi_Miles = 69.400000000000006D;
    double longidist = dist / Longi_Miles;
    return longidist;
  }
  
  public double MinLatitude()
  {
    double minlati = 0.0D;
    minlati = this.lati;
    minlati -= this.lati_dist;
    
    return minlati;
  }
  
  public double MinLongitude()
  {
    double minlongi = 0.0D;
    minlongi = this.longi;
    minlongi -= this.long_dist;
    
    return minlongi;
  }
  
  public double MaxLatitude()
  {
    double maxlati = 0.0D;
    maxlati = this.lati;
    maxlati += this.lati_dist;
    
    return maxlati;
  }
  
  public double MaxLongitude()
  {
    double maxlongi = 0.0D;
    maxlongi = this.longi;
    maxlongi += this.long_dist;
    
    return maxlongi;
  }
  
  public double distanceCalc(double lat1, double lon1, double lat2, double lon2, char unit)
  {
    double theta = lon1 - lon2;
    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
    dist = Math.acos(dist);
    dist = rad2deg(dist);
    dist = dist * 60.0D * 1.1515D;
    if (unit == 'K') {
      dist *= 1.609344D;
    } else if (unit == 'N') {
      dist *= 0.8684D;
    }
    return dist;
  }
  
  private double deg2rad(double deg)
  {
    return deg * 3.141592653589793D / 180.0D;
  }
  
  private double rad2deg(double rad)
  {
    return rad * 180.0D / 3.141592653589793D;
  }
  
  public ProximityUtil() {}
  
  public static void main(String[] args) {}
}
