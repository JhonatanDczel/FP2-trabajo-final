import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.*;


public abstract class Piece extends JButton {
  private int positionX;
  private int positionY;
  private int team;
  private Color color;
  private String tribe;

  public Piece(int positionX, int positionY, ImageIcon icon, int team, String tribe) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.team = team;
    this.tribe = tribe;

    revalidateColor();

    setCursor(new Cursor(Cursor.HAND_CURSOR));
    setIcon(icon);
  };

  public String getTribe() {
    return tribe;
  };

  public void setTribe(String tribe) {
    this.tribe = tribe;
  };
  

  public int[][] searchPositions(int[][] board) {
    return new int[10][10];
  };

  public int[] getPositions() {
    int[] positions = {positionX, positionY};
    return positions;
  };
  public int getPositionX() {
    return positionX;
  };
  public int getPositionY() {
    return positionY;
  };
  public void setPositionX(int x) {
    positionX = x;
  };
  public void setPositionY(int y) {
    positionY = y;
  };

  public void setPositions(int x, int y) {
    positionX = x;
    positionY = y;
    revalidateColor();
  };

  public int getTeam() {
    return team;
  };
  public void isPosible() {
    //Verde
    setBackground(new Color(0, 207, 79));
  };

  public void setBlack() {
    //Negro
    color = new Color(100, 100, 100);
    setBackground(new Color(100, 100, 100));
  };

  public void setWhite() {
    color = Color.WHITE;
    setBackground(Color.WHITE);
  };

  public void select() {
    //rojo
    setBackground(new Color(255, 77, 77));
  };

  public void defaultColor() {
    setBackground(color);
  };

  public void revalidateColor() {
    if (
      this.positionX % 2 != 0 && this.positionY % 2 != 0
      || this.positionX % 2 == 0 && this.positionY % 2 == 0
    ) {
      setWhite();
    } else {
      setBlack();
    }
  };

  public abstract  ArrayList<Piece> getPosiblePositions (Piece[][] board);

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Piece piece = (Piece) obj;
    return positionX == piece.positionX && positionY == piece.positionY && team == piece.team;
  }
  public String toString(){
    return "=> Team:"+getTeam()+" x:"+getPositionX()+" <-> y:"+getPositionY();
  }
}