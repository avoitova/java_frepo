package ru.stqa.frepo.sandbox;

public class MyFirstProgram {

	public static void main(String[] args){
		hello("world");
		hello("Alex");
		hello("Aelita");

		Square s = new Square(5);
		System.out.println("Area of the square with the side " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("Area of the rectangle  with the sides " + r.a + " and " + r.b + " = " + r.area());

		/*Point p1 = new Point();
		p1.x = 2;
		p1.y = 2;
		Point p2 = new Point();
		p2.x = 4;
		p2.y = 6;
		System.out.println("Distance between two points " + "p1(" + p1.x + ";"+ p1.y +")" + " and "+ "p2(" + p2.x + ";"+ p2.y + ") is "+ distance(p1,p2));*/

		Point p1 = new Point(2,2);
		Point p2 = new Point (4,6);

		System.out.println("Distance between two points " + "p1(" + p1.x + ";"+ p1.y +")" + " and "+ "p2(" + p2.x + ";"+ p2.y + ") is "+ p1.distance(p2));

	}

	private static void hello(String somebody) {
		System.out.println("Hello, " + somebody);
	}
	/*public static double distance (Point p1, Point p2){
		return Math.sqrt((p2.x- p1.x)*(p2.x- p1.x)+(p2.y- p1.y)* (p2.y- p1.y));
	}*/


}