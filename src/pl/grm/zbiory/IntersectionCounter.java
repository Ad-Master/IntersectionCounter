package pl.grm.zbiory;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class IntersectionCounter {
	public static final int		N_MIN				= 100;
	public static final int		N_MAX				= 1000;
	private ArrayList<Point>	pointList			= new ArrayList<Point>();
	private ArrayList<Point>	intersectionPoints	= new ArrayList<Point>();
	public ArrayList<Line>		lines				= new ArrayList<Line>();
	public int					pointAmount;
	public int					intersectionCount	= 0;
	
	public IntersectionCounter() {
		genPoints();
		genLines();
		countIntersectionsOfSegments();
	}
	
	public void genPoints() {
		Random r = new Random();
		pointAmount = r.nextInt(900) + 100;
		if (pointAmount % 2 == 1) {
			pointAmount++;
		}
		for (int i = 0; i <= pointAmount; i++) {
			int x = r.nextInt(100) * 10;
			int y = r.nextInt(100) * 10;
			Point point = new Point(x, y);
			pointList.add(point);
		}
	}
	
	private void genLines() {
		int lineNumber = 1;
		while (lineNumber < pointAmount) {
			Point point1 = pointList.get(lineNumber - 1);
			Point point2 = pointList.get(lineNumber);
			Line line = new Line(point1, point2);
			lines.add(line);
			lineNumber = lineNumber + 2;
		}
	}
	
	private void countIntersectionsOfSegments() {
		for (Line line1 : lines) {
			for (Line line2 : lines) {
				if (line1.equals(line2)) {
					continue;
				}
				double x1 = line1.x1;
				double y1 = line1.y1;
				double x2 = line1.x2;
				double y2 = line1.y2;
				double x3 = line2.x1;
				double y3 = line2.y1;
				double x4 = line2.x2;
				double y4 = line2.y2;
				double det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
				if (det == 0)
					continue;
				double x0 = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2)
						* (x3 * y4 - y3 * x4))
						/ det;
				double y0 = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2)
						* (x3 * y4 - y3 * x4))
						/ det;
				if (x0 < Math.min(x1, x2) || x0 > Math.max(x1, x2))
					continue;
				if (x0 < Math.min(x3, x4) || x0 > Math.max(x3, x4))
					continue;
				Point point = new Point((int) x0, (int) y0);
				if (alreadyExists(point)) {
					continue;
				}
				intersectionCount++;
			}
		}
		intersectionCount /= 2;
	}
	
	private boolean alreadyExists(Point point) {
		for (Point iPoint : intersectionPoints) {
			if (point.equals(iPoint)) { return true; }
		}
		return false;
	}
	
	public static void main(String[] args) {
		IntersectionCounter iCounter = new IntersectionCounter();
		String linesNumber = String.valueOf(iCounter.lines.size());
		String intersectionCountT = String.valueOf(iCounter.intersectionCount);
		for (Line line : iCounter.lines) {
			if (line.b < 0) {
				System.out.println("y = " + line.a + "x - " + line.b * -1);
			} else if (line.b == 0) {
				System.out.println("y = " + line.a);
			} else {
				System.out.println("y = " + line.a + "x + " + line.b);
			}
		}
		System.out.println("\nSegments: " + linesNumber);
		System.out.println("Intersections: " + intersectionCountT);
	}
}
