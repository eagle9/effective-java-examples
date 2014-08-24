
package org.effectivejava.examples.chapter03.item08.composition;

/*
 * While there is no satisfactory way to extend an instantiable class and add a
value component, there is a fine workaround. Follow the advice of Item 16, “Favor
composition over inheritance.” Instead of having ColorPoint extend Point, give
ColorPoint a private Point field and a public view method (Item 5) that returns
the point at the same position as this color point:
 */

//Adds a value component without violating the equals contract - Page 40
public class ColorPoint {
	private final Point point;
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		if (color == null)
			throw new NullPointerException();
		point = new Point(x, y);
		this.color = color;
	}

	/**
	 * Returns the point-view of this color point.
	 */
	public Point asPoint() {
		return point;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ColorPoint))
			return false;
		ColorPoint cp = (ColorPoint) o;
		return cp.point.equals(point) && cp.color.equals(color);
	}

	@Override
	public int hashCode() {
		return point.hashCode() * 33 + color.hashCode();
	}
}