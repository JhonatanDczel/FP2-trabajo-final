import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Torre extends Piece {
  private static ImageIcon whiteIcon = new ImageIcon("./assets/whiteTorre.png");
  private static ImageIcon blackIcon = new ImageIcon("./assets/blackTorre.png");

  public Torre(int positionX, int positionY, int team, String tribe) {
    super(positionX, positionY, team == 0 ? whiteIcon : blackIcon, team, tribe);
    String ruta = "./Img_Piezas/" + tribe + "_Torre.png";
    ImageIcon img = new ImageIcon(ruta);
    JLabel ficha = new JLabel(img);
    add(ficha);
  };

  public ArrayList<Piece> getPosiblePositions(Piece[][] board) {
    ArrayList<Piece> positions = new ArrayList<Piece>();

    int x = getPositionX();
    int y = getPositionY();

    for (int i = x - 1; i >= 0; i -= 1) {
      Piece position = board[i][y];
      if(position instanceof VoidCel){
        positions.add(position);
      } else if(position.getTeam() != this.getTeam()){
        positions.add(position);
        break;
      } else if(position.getTeam() == this.getTeam()){
        break;
      }
    }

    for(int i = x + 1; i < board.length; i += 1){
      Piece position = board[i][y];
      if(position instanceof VoidCel){
        positions.add(position);
      } else if(position.getTeam() != this.getTeam()){
        positions.add(position);
        break;
      } else if(position.getTeam() == this.getTeam()){
        break;
      }
    }

    for(int j = y - 1; j >= 0; j -= 1){
      Piece position = board[x][j];
      if(position instanceof VoidCel){
        positions.add(position);
      } else if(position.getTeam() != this.getTeam()){
        positions.add(position);
        break;
      } else if(position.getTeam() == this.getTeam()){
        break;
      }
    }

    for(int j = y + 1; j < board.length; j += 1){
      Piece position = board[x][j];
      if(position instanceof VoidCel){
        positions.add(position);
      } else if(position.getTeam() != this.getTeam()){
        positions.add(position);
        break;
      } else if(position.getTeam() == this.getTeam()){
        break;
      }
    }

    return positions;
  };
  public String toString(){
    return "Torre "+super.toString();
  }
}