
import java.util.ArrayList;

import javax.swing.*;

public class Peon extends Piece {
  private static ImageIcon whiteIcon = new ImageIcon("./assets/whitePeon.png");
  private static ImageIcon blackIcon = new ImageIcon("./assets/blackPeon.png");
  private boolean firstMove = true;

  private int direction;

  public Peon(int positionX, int positionY, int team, String tribe) {
    super(positionX, positionY, team == 0 ? whiteIcon : blackIcon, team, tribe);
    direction = team == 0 ? 1 : -1;
  };

  public ArrayList<Piece> getPosiblePositions(Piece[][] board) {
    ArrayList<Piece> positions = new ArrayList<Piece>();
    for (int i = 1; i <= (firstMove ? 2:1); i += 1) {
      if (getPositionX() + i * direction < 0 || getPositionX() + i * direction > 7) {
        continue;
      }
      Piece position = board[getPositionX() + i * direction][getPositionY()];
      if (position instanceof VoidCel) {
        positions.add(position);
      }
    }
    if (getPositionX() + 1 * direction >= 0 && getPositionX() + 1 * direction <= 7
        && getPositionY() - 1 >= 0 && getPositionY() + 1 <= 7) {
      Piece position = board[getPositionX() + 1 * direction][getPositionY() + 1];

      if (!(position instanceof VoidCel) && position.getTeam() != getTeam()) {
        positions.add(position);
      }

      position = board[getPositionX() + 1 * direction][getPositionY() - 1];
      if (!(position instanceof VoidCel) && position.getTeam() != getTeam()) {
        positions.add(position);
      }
    }
    firstMove = false;
    return positions;
  };
  public String toString(){
    return "Peon "+super.toString();
  }
}
