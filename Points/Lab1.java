import java.lang.Math;
import java.util.*;
import p1.Point3d;



public class Lab1{
	public static void main(String[] args){
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);

		Point3d a = new Point3d(scan.nextDouble(),scan.nextDouble(),scan.nextDouble());
		Point3d b = new Point3d(scan.nextDouble(),scan.nextDouble(),scan.nextDouble());
		Point3d c = new Point3d(scan.nextDouble(),scan.nextDouble(),scan.nextDouble());

		double area = computeArea(a, b, c);
		System.out.println("Area = "+area);

	}

	private static double computeArea(Point3d a, Point3d b, Point3d c){
		if(a.equals(b)||b.equals(c)||c.equals(a)){
			System.out.println("Wrong data: Got identical points");
			return 0;
		}
		double p = (Point3d.getDistance(a, b)+Point3d.getDistance(b, c)+Point3d.getDistance(a, c))/2;
		double res = Math.sqrt(p*(p-Point3d.getDistance(a, b))*(p-Point3d.getDistance(c, b))*(p-Point3d.getDistance(a, c)));
		return res;

	}
}