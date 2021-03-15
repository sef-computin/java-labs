package p1;

import java.lang.Math.*;

public class Point3d {
	private double xCoord;
	private double yCoord;
	private double zCoord;
	public Point3d(double x, double y, double z) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
}
public Point3d() {
	this(0, 0, 0);
}
public double getX() {
	return xCoord;
}
public double getY() {
	return yCoord;
}
public double getZ() {
	return zCoord;
}
public void setX(double val) {
	xCoord = val;
}
public void setY(double val) {
	yCoord = val;
}
public void setZ(double val) {
	zCoord = val;
}
public static double getDistance(Point3d a, Point3d b){
	return Math.sqrt(Math.pow(a.getX()-b.getX(), 2)+Math.pow(a.getY()-b.getY(), 2)+Math.pow(a.getZ()-b.getZ(), 2));
}

public boolean equals(Point3d obj){
	if((obj.xCoord == this.xCoord)&&(obj.yCoord == this.yCoord)&&(obj.zCoord == this.zCoord)) return true;
	else return false;
}
}