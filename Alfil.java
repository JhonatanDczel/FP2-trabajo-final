import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Alfil extends Piece{
  private static ImageIcon whiteIcon = new ImageIcon("./assets/whiteAlfil.png");
  private static ImageIcon blackIcon = new ImageIcon("./assets/blackAlfil.png");

  public Alfil(int positionX, int positionY, int team) {
    super(positionX, positionY, team == 0 ? whiteIcon : blackIcon, team);
  };
  public ArrayList<Piece> getPosiblePositions(Piece[][] board) {
    ArrayList<Piece> positions = new ArrayList<Piece>();
    int x = this.getPositionX();
    int y = this.getPositionY();
    for (int i = 1; i < board.length; i += 1) {
      if (x + i < board.length && y + i < board.length) {
        Piece position = board[x + i][y + i];
        if(position instanceof VoidCel){
          positions.add(position);
        } else if(position.getTeam() != this.getTeam()){
          positions.add(position);
          break;
        } else if(position.getTeam() == this.getTeam()){
          break;
        }
      }
    }

    for (int i = 1; i < board.length; i += 1) {
      if (x - i > -1 && y - i > -1) {
        Piece position = board[x - i][y - i];
        if(position instanceof VoidCel){
          positions.add(position);
        } else if(position.getTeam() != this.getTeam()){
          positions.add(position);
          break;
        } else if(position.getTeam() == this.getTeam()){
          break;
        }
      }
    }

    for (int i = 1; i < board.length; i += 1) {
      if (x + i < board.length && y - i > -1) {
        Piece position = board[x + i][y - i];
        if(position instanceof VoidCel){
          positions.add(position);
        } else if(position.getTeam() != this.getTeam()){
          positions.add(position);
          break;
        } else if(position.getTeam() == this.getTeam()){
          break;
        }
      }
    }

    for (int i = 1; i < board.length; i += 1) {
      if (x - i > -1 && y + i < board.length) {
        Piece position = board[x - i][y + i];
        if(position instanceof VoidCel){
          positions.add(position);
        } else if(position.getTeam() != this.getTeam()){
          positions.add(position);
          break;
        } else if(position.getTeam() == this.getTeam()){
          break;
        }
      }
    }
    
    return positions;
  }
  
}