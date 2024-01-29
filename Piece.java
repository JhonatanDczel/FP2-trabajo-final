import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;


public abstract class Piece extends JButton {
  private int positionX;
  private int positionY;
  private int team;
  private Color color;

  public Piece(int positionX, int positionY, ImageIcon icon, int team) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.team = team;

    revalidateColor();

    setIcon(icon);
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
    setBackground(Color.ORANGE);
  };

  public void setBlack() {
    color = Color.BLACK;
    setBackground(Color.BLACK);
  };

  public void setWhite() {
    color = Color.WHITE;
    setBackground(Color.WHITE);
  };

  public void select() {
    setBackground(Color.RED);
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