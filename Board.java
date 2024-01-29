import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Board extends JFrame {
  private Piece[][] board = new Piece[8][8];
  private Turno turno = new Turno();

  private Piece finishBtn = null;
  private ArrayList<Piece> posiblePositions;
  private JPanel box1;
  private JButton btnSave = new JButton("SAVE GAME");
  private JButton btnRecover = new JButton("RECOVER GAME");
  private JButton btnNewPLay = new JButton("NEW PLAY");
  private JPanel box2;

  public Board() {
    newGame("prueba1", "prueba tambien");
  }

  private class ListenerBtn implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof Piece) {
        Piece startBtn = (Piece) e.getSource();
        if (finishBtn == null) {
          if (startBtn instanceof VoidCel)
            return;
          if (startBtn.getTeam() != turno.getTurno())
            return;
          startBtn.select();

          posiblePositions = startBtn.getPosiblePositions(board);
          paintPositions(posiblePositions);

          finishBtn = startBtn;
        } else {
          removeBoard();

          if (posiblePositions.contains(startBtn) && !finishBtn.equals(startBtn)) {
            changePositions(board, finishBtn, startBtn, posiblePositions);
            turno.played();
            if (!isKingLive(turno.getTurno())) {
              JOptionPane.showConfirmDialog(null, "WINNER: El team " + turno.getOpponentTeam());
              resetGame();
            }
          }

          reFill();

          finishBtn.defaultColor();

          posiblePositions = null;
          finishBtn = null;

          revalidate();
        }
      } else {
        if (e.getSource() == (JButton) btnSave) {
          saveGame();
        }
        if (e.getSource() == (JButton) btnRecover) {
          recoverGame();
        }
        if (e.getSource() == (JButton) btnNewPLay) {
          resetGame();
          turno.resetTurno();
        }
      }
    }
  }

  public void resetGame() {
    removeBoard();
    newGame("pruebita nomas", "no se lo que hago xd");
    reFill();
    box1.revalidate();
    box1.repaint();
  }

  public void saveGame() {
    try {
      ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("./playFile/game.bin"));

      fileOut.writeObject(board);

      fileOut.close();

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
    }
  }

  public void recoverGame() {
    try {
      removeBoard();

      ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("./playFile/game.bin"));

      board = (Piece[][]) fileIn.readObject();

      for (int i = 0; i < 8; i += 1) {
        for (int j = 0; j < 8; j += 1) {
          board[i][j].addActionListener(new ListenerBtn());
        }
      }

      fileIn.close();

      reFill();

      box1.revalidate();
      box1.repaint();

    } catch (Exception e) {
      JOptionPane.showMessageDialog(box1, "ERROR: " +e+"");
    }
  }

  public static void main(String[] args) {
    new Board();
  };

  public void removeBoard() {
    for (int i = 0; i < 8; i += 1) {
      for (int j = 0; j < 8; j += 1) {
        board[i][j].defaultColor();
        box1.remove(board[i][j]);

      }
    }
  }

  public void reFill() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        box1.add(board[i][j]);
      }
    }
  }

  public void changePositions(Piece[][] board, Piece p1, Piece p2, ArrayList<Piece> posiblePositions) {
    board[p1.getPositionX()][p1.getPositionY()] = new VoidCel(p1.getPositionX(), p1.getPositionY());
    board[p1.getPositionX()][p1.getPositionY()].addActionListener(new ListenerBtn());
    p1.setPositions(p2.getPositionX(), p2.getPositionY());
    board[p2.getPositionX()][p2.getPositionY()] = p1;
  }

  public void paintPositions(ArrayList<Piece> positions) {
    for (Piece position : positions) {
      position.isPosible();
    }
  }

  public void newGame(String tribe1, String tribe2) {
    box1 = new JPanel(new GridLayout(8, 8));
    for (int i = 0; i < 8; i++) {
      if (i == 0) {
        board[i][0] = new Torre(i, 0, 0, tribe2);
        box1.add(board[i][0]);

        board[i][1] = new Horse(i, 1, 0, tribe2);
        box1.add(board[i][1]);

        board[i][2] = new Alfil(i, 2, 0, tribe2);
        box1.add(board[i][2]);

        board[i][3] = new Queen(i, 3, 0, tribe2);
        box1.add(board[i][3]);

        board[i][4] = new King(i, 4, 0, tribe2);
        box1.add(board[i][4]);

        board[i][5] = new Horse(i, 5, 0, tribe2);
        box1.add(board[i][5]);

        board[i][6] = new Alfil(i, 6, 0, tribe2);
        box1.add(board[i][6]);

        board[i][7] = new Torre(i, 7, 0, tribe2);
        box1.add(board[i][7]);
      }
      if (i == 1)
        for (int j = 0; j < 8; j++) {
          board[i][j] = new Peon(i, j, 0, tribe2);
          box1.add(board[i][j]);
        }

      if (i > 1 && i < 6)
        for (int j = 0; j < 8; j++) {
          board[i][j] = new VoidCel(i, j);
          box1.add(board[i][j]);
        }

      if (i == 6)
        for (int j = 0; j < 8; j++) {
          board[i][j] = new Peon(i, j, 1, tribe1);
          box1.add(board[i][j]);
        }
      if (i == 7) {
        board[i][0] = new Torre(i, 0, 1, tribe1);
        box1.add(board[i][0]);

        board[i][1] = new Horse(i, 1, 1, tribe1);
        box1.add(board[i][1]);

        board[i][2] = new Alfil(i, 2, 1, tribe1);
        box1.add(board[i][2]);

        board[i][3] = new Queen(i, 3, 1, tribe1);
        box1.add(board[i][3]);

        board[i][4] = new King(i, 4, 1, tribe1);
        box1.add(board[i][4]);

        board[i][5] = new Horse(i, 5, 1, tribe1);
        box1.add(board[i][5]);

        board[i][6] = new Alfil(i, 6, 1, tribe1);
        box1.add(board[i][6]);

        board[i][7] = new Torre(i, 7, 1, tribe1);
        box1.add(board[i][7]);
      }
      putBtn();
      for (int j = 0; j < 8; j++) {
        board[i][j].addActionListener(new ListenerBtn());
      }
    }
    btnNewPLay.addActionListener(new ListenerBtn());
    btnSave.addActionListener(new ListenerBtn());
    btnRecover.addActionListener(new ListenerBtn());
    setTitle("Game-Chess");
    setSize(800, 800);
    setLayout(new BorderLayout());
    add(box1, BorderLayout.CENTER);
    add(box2, BorderLayout.SOUTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  };

  public void putBtn() {
    box2 = new JPanel(new GridLayout(1, 3));
    Color color = new Color(185, 202, 235);
    Font font = new Font("Arial", Font.BOLD, 16);
    Dimension dimension = new Dimension(400, 50);
    btnSave.setBackground(color);
    btnRecover.setBackground(color);
    btnNewPLay.setBackground(color);
    btnSave.setPreferredSize(dimension);
    btnRecover.setPreferredSize(dimension);
    btnNewPLay.setPreferredSize(dimension);
    btnSave.setFont(font);
    btnRecover.setFont(font);
    btnNewPLay.setFont(font);
    box2.add(btnNewPLay);
    box2.add(btnSave);
    box2.add(btnRecover);
  }

  private boolean isKingLive(int team) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] instanceof King && board[i][j].getTeam() == team) {
          return true;
        }
      }
    }
    return false;
  }
}
