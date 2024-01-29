import java.util.ArrayList;

import javax.swing.ImageIcon;

public class King extends Piece{
  private static ImageIcon whiteIcon = new ImageIcon("./assets/whiteKing.png");
  private static ImageIcon blackIcon = new ImageIcon("./assets/blackKing.png");

  public King(int positionX, int positionY, int team) {
    super(positionX, positionY, team == 0 ? whiteIcon : blackIcon, team);
  };

  public ArrayList<Piece> getPosiblePositions(Piece[][] board) {
    ArrayList<Piece> positions = new ArrayList<Piece>();

    int x = getPositionX();
    int y = getPositionY();

    for (int i = -1; i < 2; i += 1) {
      for (int j = -1; j < 2; j += 1) {
        if(i + x > -1 && i + x < 8 && j + y > -1 && j + y < 8){
          Piece position = board[i + x][j + y];
          if(position instanceof VoidCel){
            positions.add(position);
          }else if(position.getTeam() != this.getTeam()){
            positions.add(position);
          }
        }
      }
    }
    return positions;
  };
  public String toString(){
    return "Rey "+super.toString();
  }
}
