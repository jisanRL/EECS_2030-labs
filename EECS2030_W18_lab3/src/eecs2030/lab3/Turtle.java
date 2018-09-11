package eecs2030.lab3;

import java.awt.Color;
import java.util.Objects;

import princeton.introcs.StdDraw;

/**
 * A class that supports turtle graphics. A turtle walks between two points in a
 * straight line drawing the line as it moves.
 * 
 * <p>
 * A turtle has-a {@code Point2} instance that represents the position of the
 * turtle, a {@code Direction2} instance that represents the direction that the
 * turtle is facing in, and a {@code Pen} instance that represents the pen that
 * the turtle draws with.
 * 
 * @author EECS2030 Winter 2017-18
 * 
 */
public class Turtle {

	/**
	 * The position of this turtle.
	 */
	private Point2 position;

	/**
	 * The direction this turtle is facing in.
	 */
	private Direction2 direction;

	/**
	 * The pen that the turtle draws with.
	 */
	private Pen pen;

	/**
	 * Create a turtle at location {@code (0.5, 0.5)} with an direction of
	 * {@code 0.0} degrees and a pen color of {@code Color.BLACK}.
	 */
	public Turtle() {
		this(0.5, 0.5, 0.0, Color.BLACK);

	}

	/**
	 * Create a turtle with the given starting position, direction, and pen. The
	 * starting position must be inside the square with corners {@code (0.0, 0.0)}
	 * and {@code (1.0, 1.0)}, otherwise an {@code IllegalArgumentException} will be
	 * thrown.
	 * 
	 * @param x
	 *            the x coordinate of the starting position of the turtle
	 * @param y
	 *            the y coordinate of the starting position of the turtle
	 * @param angle
	 *            the angle in degrees from the x axis that the turtle is facing in
	 * @param c
	 *            the color of the pen that the turtle should draw with
	 * @throws IllegalArgumentException
	 *             if the starting position is not in the square with corners (0.0,
	 *             0.0) and (1.0, 1.0)
	 */
	public Turtle(double x, double y, double angle, Color c) { 
		if (x < 0.0 || x > 1.0 || y < 0.0 || y > 1.0 || c == null) {
			throw new IllegalArgumentException("Invalid");
		}
		this.position = new Point2(x, y);
		this.direction = new Direction2(angle);
		this.pen = new Pen(c);
	}

	/**
	 * Walks the turtle forward by a given distance in the direction the turtle is
	 * currently facing. A line is drawn as the turtle moves to the new position
	 * using the current pen color.
	 * 
	 * @param distance
	 *            the distance to move
	 * @throws IllegalArgumentException
	 *             if distance is less than zero
	 */
	public void walk(double distance) {
		if (distance < 0.0) {
			throw new IllegalArgumentException("Invalid distance");
		}
		Point2 cur = new Point2(this.position);
		double newX = direction.getX() * distance;
		double newY = direction.getY() * distance;
		Point2 next = new Point2(cur.getX() + newX, cur.getY() + newY);
		pen.drawLine(cur, next);
		this.position = next;

	}

	/**
	 * Turns the turtle to the left (counter clockwise) by an amount delta degrees.
	 * 
	 * @param delta
	 *            the angle to turn counter clockwise by
	 * 
	 * 			@pre. {@code delta >= 0.0}
	 */
	public void turnLeft(double delta) {
		if (delta >= 0.0) {
			this.direction.turn(delta);
		}
	}

	/**
	 * Turns the turtle to the right (clockwise) by an amount delta degrees.
	 * 
	 * @param delta
	 *            the angle to turn clockwise by @pre. {@code delta >= 0.0}
	 */
	public void turnRight(double delta) {
		if (delta >= 0.0) {
			this.direction.turn(delta * -1);
		}
	}

	/**
	 * Turns the turtle so that its direction is equal to the given angle in
	 * degrees. Any value of delta can be used, but the turtle normalize its
	 * direction angle so that {@code 0.0 <= this.getAngle() < 360.0}.
	 * 
	 * @param angle
	 *            the new direction angle of the turtle
	 */
	public void turnTo(double angle) {
		this.direction.setAngle(angle);
	}

	/**
	 * Gets the pen belonging to this turtle.
	 * 
	 * @return the pen belonging to this turtle
	 */
	public Pen getPen() {
		return pen;

	}

	/**
	 * Gets the current position of the turtle.
	 * 
	 * @return the current position of the turtle
	 */
	public Point2 getPosition() {
		return position;

	}

	/**
	 * Gets the direction that the turtle is facing in.
	 * 
	 * @return the direction that the turtle is facing in
	 */
	public Direction2 getDirection() {
		return direction;
	}

	/**
	 * Returns a string representation of this turtle. The string representation is:
	 * 
	 * <ol>
	 * <li>the position of the turtle (as given by {@code Point2.toString}),
	 * followed by
	 * <li>a comma and a space, followed by
	 * <li>the direction of this turtle (as given by {@code Direction2.toString}),
	 * followed by
	 * <li>a space and a comma, followed by
	 * <li>the pen (as given by {@code Pen.toString})
	 * </ol>
	 * 
	 * @return a string representation of this turtle
	 */
	@Override
	public String toString() {
		String s = String.format("%s, %s degrees, %s", this.getPosition(), this.getDirection(), this.getPen());
		return s;
	}

	/**
	 * Returns a hash code for this turtle.
	 * 
	 * @return a hash code for this turtle
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.position, this.direction, this.pen);
	}

	/**
	 * Compares this turtle to the specified object. The result is true if and only
	 * if the argument is not null and is a {@code Turtle} object having a position,
	 * direction, and pen equal to this turtle's position, direction, and pen.
	 * 
	 * @param obj
	 *            the object to compare this Turtle against
	 * @return true if the given object represents a Turtle equivalent to this
	 *         object and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Turtle other = (Turtle) obj;
		if (direction == null) {
			if (other.direction != null) {
				return false;
			}
		} else if (!direction.equals(other.direction)) {
			return false;
		}
		if (pen == null) {
			if (other.pen != null) {
				return false;
			}
		} else if (!pen.equals(other.pen)) {
			return false;
		}
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}

}
