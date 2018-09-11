package eecs2030.lab3;

import java.util.Objects;

/**
 * A class that represents a direction in 2D in a right-handed coordinate
 * system. A direction is defined by an angle in degrees measured from the
 * positive x axis. Positive angles are measured in the counter-clockwise
 * direction.
 * 
 * <p>
 * Any double value (positive or negative) can be used to construct a direction,
 * but it is a class invariant that {@code Direction2} objects will always
 * normalize the direction angle so that {@code 0.0 <= getDirection() < 360.0}.
 * 
 * @author EECS2030 Winter 2018
 * 
 */
public final class Direction2 {

	/**
	 * The angle measured in degrees of this angle. There is a class invariant that
	 * {@code 0.0 <= this.degrees < 360.0}
	 */
	private double degrees;

	/**
	 * Initialize this direction to 0 degrees.
	 */
	public Direction2() {
		this(0.0);
	}

	/**
	 * Initialize this direction to have the specified degrees. Note that the
	 * current implementation fails if the specified degrees is outside the range
	 * {@code [0.0, 360.0)}.
	 * 
	 * @param degrees
	 *            the direction measured in degrees
	 * 
	 * 			@pre. {@code 0.0 <= degrees < 360.0}
	 */

	public Direction2(double degrees) {
		this.setAngle(degrees);
	}

	/**
	 * Sets this direction to have the specified degrees. Any double value (positive
	 * or negative) can be used to set the direction, but it is a class invariant
	 * that this {@code Direction2} object will normalize the direction angle so
	 * that {@code 0.0 <= this.getDirection() < 360.0}.
	 * 
	 * @param degrees
	 *            the direction measured in degrees
	 */
	public final void setAngle(double degrees) {
		// double cc = 0.0;

		degrees = degrees % 360.0;
		if (degrees < 0) {
			degrees += 360.0;
		}
		this.degrees = degrees;

	}

	/**
	 * Returns the direction measured in degrees represented by this object. The
	 * returned value is guaranteed to be in the range
	 * {@code 0.0 <= this.getDirection() < 360.0}
	 * 
	 * @return the direction measured in degrees
	 */
	public double getAngle() {
		return this.degrees;
	}

	/**
	 * Returns the x component of the unit vector pointing in the direction given by
	 * this object. The returned value is equal to the cosine of the angle
	 * represented by this direction.
	 * 
	 * @return the x component of the unit vector pointing in the direction given by
	 *         this object
	 */
	public double getX() {
		// convert degrees to radians
		double cc = Math.toRadians(degrees);
		return Math.cos(cc);
	}

	/**
	 * Returns the y component of the unit vector pointing in the direction given by
	 * this object. The returned value is equal to the sine of the angle represented
	 * by this direction.
	 * 
	 * @return the y component of the unit vector pointing in the direction given by
	 *         this object
	 */
	public double getY() {
		double yy = Math.toRadians(degrees);
		return Math.sin(yy);
	}

	/**
	 * Changes the direction represented by this object by adding the angle delta
	 * measured in degrees to the angle of this object. A positive value of delta
	 * corresponds to a counter-clockwise change of the angle, and a negative value
	 * of delta corresponds to a clockwise change of the angle.
	 * 
	 * @param delta
	 *            the angle to add to this direction
	 */
	public void turn(double delta) {
		double current = this.degrees + delta;
		current = current % 360;
		if (current < 0.0) {
			current += 360.0;
		}
		this.degrees = current;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(degrees);
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
		Direction2 other = (Direction2) obj;
		if (Double.doubleToLongBits(degrees) != Double.doubleToLongBits(other.degrees))
			return false;
		return true;
	}

	/**
	 * Returns a string representation of this direction. The returned string is the
	 * angle of this direction in degrees followed by the string {@code " deg"}.
	 * 
	 * @return a string representation of this direction
	 */
	@Override
	public String toString() {
		return this.getAngle() + " deg";
	}
}
