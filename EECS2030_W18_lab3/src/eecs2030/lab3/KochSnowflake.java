package eecs2030.lab3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KochSnowflake {

  private static void drawKochSnowflake(List<Turtle> turtles) {
    
      drawKoch(turtles, 0.5, 4);
      for (Turtle t : turtles) {
        t.turnRight(120.0);
      }
      drawKoch(turtles, 0.5, 4);
      for (Turtle t : turtles) {
        t.turnRight(120.0);
      }
      drawKoch(turtles, 0.5, 4);
  }

  private static void drawKoch(List<Turtle> turtles, double length, int depth) {
    if (depth == 0) {
      for (Turtle t : turtles) {
        t.walk(length);
      }
    } else {
      length /= 3.0;
      drawKoch(turtles, length, depth - 1);
      for (Turtle t : turtles) {
        t.turnLeft(60.0);
      }
      drawKoch(turtles, length, depth - 1);
      for (Turtle t : turtles) {
        t.turnRight(120.0);
      }
      drawKoch(turtles, length, depth - 1);
      for (Turtle t : turtles) {
        t.turnLeft(60.0);
      }
      drawKoch(turtles, length, depth - 1);
    }
  }

  public static void main(String[] args) {
	  List<Color> colors = Arrays.asList(
			  Color.BLUE, Color.CYAN, 
			  Color.GREEN, Color.MAGENTA, Color.RED);
    List<Turtle> turtles = new ArrayList<Turtle>();
    Turtle t = new Turtle();
    turtles.add(t);
    for (int i = 0; i < 5; i++) {
      Turtle ti = new Turtle();
      ti.turnLeft(60.0 * (i + 1));
      ti.getPen().setColor(colors.get(i));
      turtles.add(ti);
    }    
    drawKochSnowflake(turtles);
  }
}
