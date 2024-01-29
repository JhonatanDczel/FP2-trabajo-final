import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Horse extends Piece {
  private static ImageIcon whiteIcon = new ImageIcon("./assets/whiteHorse.png");
  private static ImageIcon blackIcon = new ImageIcon("./assets/blackHorse.png");

  public Horse(int positionX, int positionY, int team, String tribe) {
    super(positionX, positionY, team == 0 ? whiteIcon : blackIcon, team, tribe);
    String ruta = "./Img_Piezas/" + tribe + "_Caballo.png";
    setIcon(new ImageIcon(ruta));
  };

  public ArrayList<Piece> getPosiblePositions(Piece[][] board) {
    ArrayList<Piece> possiblePositions = new ArrayList<>();

    int x = getPositionX();
    int y = getPositionY();

    int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    for (int i = 0; i < dx.length; i++) {
      int newX = x + dx[i];
      int newY = y + dy[i];

      if (isValidPosition(newX, newY) && (board[newX][newY] == null || board[newX][newY].getTeam() != getTeam())) {
        possiblePositions.add(board[newX][newY]);
      }
    }

    return possiblePositions;
  }
  private boolean isValidPosition(int x, int y) {
    return x >= 0 && x < 8 && y >= 0 && y < 8;
  }
  public String toString(){
    return "Caballo "+super.toString();
  } 
}
