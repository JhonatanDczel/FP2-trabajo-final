public class Turno {
  private int turno;
  private int movements = 0;
  private String tribe1;
  private String tribe2;

  public void setTribes(String tribe1, String tribe2) {
    this.tribe1 = tribe1;
    this.tribe2 = tribe2;
  }

  public String getTribe(int team){
    if (team == 0) {
      return tribe2;
    } else {
      return tribe1;
    }
  }

  public Turno() {
    turno = 0;
  }

  public int getMovements() {
    return movements;
  }

  public int getTurno() {
    return turno;
  }
  public int getOpponentTeam() {
    return turno == 0 ? 1 : 0;
  }
  public void played() {
    turno = turno == 0 ? 1 : 0;
    movements++;
  }

  public void resetTurno() {
    turno = 0;
  }
}

