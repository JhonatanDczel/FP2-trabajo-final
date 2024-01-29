import java.awt.Cursor;
import java.util.ArrayList;

public class VoidCel extends Piece {
  public VoidCel(int positionX, int positionY) {
    super(positionX, positionY, null, -1, "none");
    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  };

  public ArrayList<Piece> getPosiblePositions(Piece[][] board) {
    return new ArrayList<Piece>();
  };
  public String toString(){
    return "Vacio "+super.toString();
  }
}
