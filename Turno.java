public class Turno {
  private int turno;

  public Turno() {
    turno = 0;
  }

  public int getTurno() {
    return turno;
  }
  public int getOpponentTeam() {
    return turno == 0 ? 1 : 0;
  }
  public void played() {
    turno = turno == 0 ? 1 : 0;
  }

  public void resetTurno() {
    turno = 0;
  }
}
