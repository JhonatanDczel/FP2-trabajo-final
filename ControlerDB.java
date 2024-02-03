import java.sql.*;
import java.util.HashMap;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.awt.*;


public class ControlerDB {
  private HashMap<String, Integer> scores = new HashMap<String, Integer>();
  private static ControlerDB instance = null;
  static {
      try {
          Class.forName("org.sqlite.JDBC");
      } catch (ClassNotFoundException e) {
          System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
      }
  }
  //crea un main simple para probar todo lo que has hecho aqui
  public static void main(String[] args) {
    ControlerDB controlerDB = new ControlerDB();
    controlerDB.getScoresCSV();
    controlerDB.showScoresCSV();
  }

  private ControlerDB(){
    getScoresCSV();
  }

  //Singleton
  public static synchronized ControlerDB getInstance() {
    if (instance == null) {
      instance = new ControlerDB();
    }
    return instance;
  }

  public static void cargarDatosPruebaSQLite(){
    try {
      Connection connection = DriverManager.getConnection("jdbc:sqlite:./sql/usuarios.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (usuario TEXT, puntuacion INTEGER)");
      statement.executeUpdate("INSERT INTO usuarios (usuario, puntuacion) VALUES ('Pepito', 100)");
      statement.executeUpdate("INSERT INTO usuarios (usuario, puntuacion) VALUES ('Juanito', 120)");
      statement.executeUpdate("INSERT INTO usuarios (usuario, puntuacion) VALUES ('Reginald', 30)");
      statement.executeUpdate("INSERT INTO usuarios (usuario, puntuacion) VALUES ('User', 400)");
      statement.executeUpdate("INSERT INTO usuarios (usuario, puntuacion) VALUES ('Magnus', 50)");
      statement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void cargarDatosPruebaCSV(){
    try {
      FileWriter writer = new FileWriter("./sql/usuarios.csv");
      writer.write("Pepito,100\n");
      writer.write("Juanito,120\n");
      writer.write("Reginald,30\n");
      writer.write("User,400\n");
      writer.write("Magnus,50\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void getScoresSQLite() {
    try {
      Connection connection = DriverManager.getConnection("jdbc:sqlite:./sql/usuarios.sql");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");

      while (resultSet.next()) {
        scores.put(resultSet.getString("usuario"), resultSet.getInt("puntuacion"));
      }

      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  //Crea un metodo que reciba el nombre del usuario y la puntuacion y actualice la base de datos
  public void updateScore(String user, int score) {
    try {
      Connection connection = DriverManager.getConnection("jdbc:sqlite:./sql/usuarios.sql");
      Statement statement = connection.createStatement();
      statement.executeUpdate("UPDATE usuarios SET puntuacion = " + score + " WHERE usuario = '" + user + "'");
      statement.close();
      connection.close();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  // Crea un metodo que muestre una ventana con la informacion del score del de menor puntuacion (el que gano con menos movimientos) hasta el que hizo con mas jugadas, usa java swing
  public void showScoresSQLite() {
    String[] users = scores.keySet().toArray(new String[0]);
    int[] scores = this.scores.values().stream().mapToInt(i -> i).toArray();

    for (int i = 0; i < scores.length; i++) {
      for (int j = 0; j < scores.length - 1; j++) {
        if (scores[j] > scores[j + 1]) {
          int temp = scores[j];
          scores[j] = scores[j + 1];
          scores[j + 1] = temp;

          String tempUser = users[j];
          users[j] = users[j + 1];
          users[j + 1] = tempUser;
        }
      }
    }

    String message = "";
    for (int i = 0; i < scores.length; i++) {
      message += users[i] + ": " + scores[i] + "\n";
    }

    JOptionPane.showMessageDialog(null, message, "Puntuaciones", JOptionPane.INFORMATION_MESSAGE);
  }

  //Ahora quiero un metodo que cargue los datos en el score hashmap pero que los lea con reader desde el archivo ./sql/usuarios.csv, no quiero que uses sqlite, usa el simple lector de archivos de java
  public synchronized void getScoresCSV() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("./sql/usuarios.csv"));
      String line = reader.readLine();
      while (line != null) {
        String[] data = line.split(",");
        scores.put(data[0], Integer.parseInt(data[1]));
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  //Ahora un metodo showScoresCSV que muestre una ventana con la informacion del score del de menor puntuacion (el que gano con menos movimientos) hasta el que hizo con mas jugadas, usa java swing
    public void showScoresCSV() {
        // Obtener datos ordenados
        String[] users = scores.keySet().toArray(new String[0]);
        int[] scoresArray = this.scores.values().stream().mapToInt(i -> i).toArray();

        for (int i = 0; i < scoresArray.length; i++) {
            for (int j = 0; j < scoresArray.length - 1; j++) {
                if (scoresArray[j] > scoresArray[j + 1]) {
                    int temp = scoresArray[j];
                    scoresArray[j] = scoresArray[j + 1];
                    scoresArray[j + 1] = temp;

                    String tempUser = users[j];
                    users[j] = users[j + 1];
                    users[j + 1] = tempUser;
                }
            }
        }

        // Crear una matriz para los datos de la tabla
        Object[][] data = new Object[scoresArray.length][2];
        for (int i = 0; i < scoresArray.length; i++) {
            data[i][0] = users[i];
            data[i][1] = scoresArray[i];
        }

        // Cambiar el Look and Feel a Nimbus


        // Crear la tabla y personalizar su apariencia
        JTable table = new JTable(data, new Object[]{"Usuario", "Puntuación"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFillsViewportHeight(true);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);

        // Crear un JScrollPane para hacer que la tabla sea desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        // Crear un JLabel para el título
        JLabel titleLabel = new JLabel("VICTORIAS EN MENOS JUGADAS:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // Utilizar una fuente más moderna

        // Crear un JPanel para contener el título y la tabla
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Agregar un icono personalizado al cuadro de diálogo
        ImageIcon icon = new ImageIcon(getClass().getResource("./assets/BattleChess.png"));
        UIManager.put("OptionPane.informationIcon", icon);

        // Mostrar el cuadro de diálogo
        JOptionPane.showMessageDialog(null, panel, "Puntuaciones", JOptionPane.INFORMATION_MESSAGE);
    }


    //metodo que escribe en el archivo ./sql/usuarios.csv el score del usuario que recibe como parametro
    public void updateScoreCSV(String user, int score) {
        try {
            FileWriter writer = new FileWriter("./sql/usuarios.csv", true);
            writer.write(user + "," + score + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

