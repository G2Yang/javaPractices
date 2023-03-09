
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example of connection to MySql database using Java JDBC bridge.
 *
 * @author Jose
 */
public class ShowCountriesJdbcMySql {

    public static void main(String[] args) throws Exception {
        ShowCountriesJdbcMySql ap = new ShowCountriesJdbcMySql();
        ap.run();
    }

    private void run() {
        boolean exit = false;
        try {
            //load the driver (only once).
            DbConnect.loadDriver();
            do {
                int optNumber = promptMenu();
                switch (optNumber) {
                    case 0 -> exit = true;
                    case 1 -> printAllCountries();
                    case 2 -> printAllCountriesAndMetadata();
                    case 3 -> insertANewCountry();
                    case 4 -> modifyACountry();
                    case 5 -> deleteACountry();
                    default -> System.out.println("Bad option!");
                }
            } while (!exit);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Displays menu and reads option from user.
     *
     * @return the option selected by user or -1 in case of error.
     */
    private static int promptMenu() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] options = {
            "Quit", "Print all countries", "Print all countries and metadata",
            "Insert a new country", "Modify a country", "Delete a country",};
        for (int i = 0; i < options.length; i++) {
            System.out.format("[%d]. %s\n", i, options[i]);
        }
        System.out.print("Choose a test to execute: ");
        int op;
        try {
            op = Integer.parseInt((reader.readLine()));
        } catch (NumberFormatException | IOException e) {
            op = -1;
        }
        return op;
    }

    private String inputString(String message) {
        String answer = "";
        try {
            System.out.print(message);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            answer = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    private void printAllCountriesAndMetadata() {
        System.out.println("Executing: " + (new Exception()).getStackTrace()[0].getMethodName());
        try ( Connection conn = DbConnect.getConnection() ) { //get a connection to database.
            if (conn != null) {
                //create a statement to perform queries.
                Statement stmt = conn.createStatement();
                //execute the query and get a resultset.
                String query = "SELECT * FROM countries WHERE lifeexpectancy > 77";
                ResultSet res = stmt.executeQuery(query);
                //get and write metadata.
                ResultSetMetaData rsmd = res.getMetaData();
                int numCol = rsmd.getColumnCount();
                System.out.println("Number of columns: " + rsmd.getColumnCount());
                System.out.println("Label \t Name \t Type \t Type name \t Auto increment \t Null \t Precision");
                for (int i = 0; i < numCol; i++) {
                    System.out.print(rsmd.getColumnLabel(i + 1) + " \t ");
                    System.out.print(rsmd.getColumnName(i + 1) + " \t ");
                    System.out.print(rsmd.getColumnType(i + 1) + " \t ");
                    System.out.print(rsmd.getColumnTypeName(i + 1) + " \t ");
                    System.out.print(rsmd.isAutoIncrement(i + 1) + " \t ");
                    System.out.print(rsmd.isNullable(i + 1) + " \t ");
                    System.out.print(rsmd.getPrecision(i + 1) + " \t ");
                    System.out.println("");
                }
                //write headers.
                System.out.println("");
                for (int i = 0; i < numCol; i++) {
                    System.out.print(rsmd.getColumnLabel(i + 1) + "\t");
                }
                System.out.println("");
                //iterate the resultset and write the rows.
                while (res.next()) {
                    //convert data to java format.
                    int id = res.getInt("id");
                    String name = res.getString("name");
                    String capital = res.getString("capital");
                    double surface = res.getDouble("surface");
                    int inhabitants = res.getInt("inhabitants");
                    double pib = res.getDouble("pib");
                    int lifeexpectancy = res.getInt("lifeexpectancy");
                    //instantiate a country object.
                    Country country = new Country(id, name, capital, surface, inhabitants, pib, lifeexpectancy);
                    //write data.
                    System.out.format("%d\t%-15s\t%-15s\t%8.0f\t%10d\t%8.2f\t%d\n",
                            country.getId(),
                            country.getName(), country.getCapital(),
                            country.getSurface(), country.getInhabitants(),
                            country.getPib(), country.getLifeexpectancy());
                }
                System.out.println("");
                //close resources.
                res.close();
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printAllCountries() {
        System.out.println("Executing: " + (new Exception()).getStackTrace()[0].getMethodName());
        try ( Connection conn = DbConnect.getConnection() ) { //get a connection to database.
            if (conn != null) {
                //create a statement to perform queries.
                Statement stmt = conn.createStatement();
                //execute the query and get a resultset.
                String query = "SELECT * FROM countries WHERE lifeexpectancy > 77";
                ResultSet res = stmt.executeQuery(query);
                //write headers.
                System.out.println("");
                System.out.format("%s\t%-15s\t%-15s\t%-10s\t%-10s\t%-10s\t%s\n",
                            "id",
                            "name", "capital",
                            "surface", "inhabitants",
                            "pib", "life expectancy");
                System.out.println("");
                //iterate the resultset and write the rows.
                while (res.next()) {
                    //convert data to java format.
                    int id = res.getInt("id");
                    String name = res.getString("name");
                    String capital = res.getString("capital");
                    double surface = res.getDouble("surface");
                    int inhabitants = res.getInt("inhabitants");
                    double pib = res.getDouble("pib");
                    int lifeexpectancy = res.getInt("lifeexpectancy");
                    //instantiate a country object.
                    Country country = new Country(id, name, capital, surface, inhabitants, pib, lifeexpectancy);
                    //write data.
                    //System.out.println(country.toString());
                    System.out.format("%d\t%-15s\t%-15s\t%8.0f\t%10d\t%8.2f\t%d\n",
                            country.getId(),
                            country.getName(), country.getCapital(),
                            country.getSurface(), country.getInhabitants(),
                            country.getPib(), country.getLifeexpectancy());
                }
                System.out.println("");
                //close resources.
                res.close();
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertANewCountry() {
        System.out.println("Executing: " + (new Exception()).getStackTrace()[0].getMethodName());
        try ( Connection conn = DbConnect.getConnection() ) { //get a connection to database.
            if (conn != null) {
                //create a statement to perform queries.
                Statement stmt = conn.createStatement();
                //execute the query.
                String query
                        = "insert into countries values "
                        + "(null, 'Wonderland', 'Capital', 1000.0, 100000, 2500.0, 90)";
                int numRowsAffected = stmt.executeUpdate(query);
                System.out.format("%d rows created\n", numRowsAffected);
                //close resources.
                stmt.close();
            }
            System.out.println("");
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modifyACountry() {
        System.out.println("Executing: " + (new Exception()).getStackTrace()[0].getMethodName());
        int id = Integer.parseInt(inputString("Input id: "));
        try ( Connection conn = DbConnect.getConnection() ) { //get a connection to database.
            if (conn != null) {
                //create a statement to perform queries.
                Statement stmt = conn.createStatement();
                //execute the query.
                String query
                        = String.format("update countries set pib = 50000.0 where id=%d", id);
                int numRowsAffected = stmt.executeUpdate(query);
                System.out.format("%d rows updated\n", numRowsAffected);
                //close resources.
                stmt.close();
            }
            System.out.println("");
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteACountry() {
        System.out.println("Executing: " + (new Exception()).getStackTrace()[0].getMethodName());
        int id = Integer.parseInt(inputString("Input id: "));
        try ( Connection conn = DbConnect.getConnection() ) { //get a connection to database.
            if (conn != null) {
                //execute the query.
                //create a statement to perform queries.
                Statement stmt = conn.createStatement();
                //execute the query.
                String query
                        = String.format("delete from countries where id=%d", id);
                int numRowsAffected = stmt.executeUpdate(query);
                System.out.format("%d rows deleted\n", numRowsAffected);
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

}
