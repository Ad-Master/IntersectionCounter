package pl.grm.zbiory;

import java.awt.Point;

public class Line {
	public double	x1, y1;
	public double	x2, y2;
	public double	a, b;
	
	public Line(Point point1, Point point2) {
		this.x1 = point1.x;
		this.y1 = point1.y;
		this.x2 = point2.x;
		this.y2 = point2.y;
		if (x1 == x2) {
			a = 0;
			b = y1;
		} else {
			a = (y1 - y2) / (x1 - x2);
			b = y2 - (a * x2);
			a *= 1000;
			b *= 1000;
			a = Math.round(a);
			b = Math.round(b);
			a /= 1000;
			b /= 1000;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.x1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.x2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.y1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.y2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (this.x1 != other.x1)
			return false;
		if (this.x2 != other.x2)
			return false;
		if (this.y1 != other.y1)
			return false;
		if (this.y2 != other.y2)
			return false;
		return true;
	}
	
	public boolean isParallel(Line line) {
		if (this.a == line.a)
			return true;
		return false;
	}
	
	public boolean isPerpendicular(Line line) {
		if (this.a == (-1 / line.a))
			return true;
		return false;
	}
}
