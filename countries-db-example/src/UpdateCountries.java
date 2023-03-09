
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example of updatable resultset.
 *
 * @author Jose
 */
public class UpdateCountries {

    public static void main(String[] args) throws Exception {
        UpdateCountries ap = new UpdateCountries();
        ap.run();
    }

    public void run() {
        Connection connection;
        try {
            //load the driver (only once).
            DbConnect.loadDriver();
            //get a connection to database.
            connection = DbConnect.getConnection();
            if (connection != null) {
                //create an browsable and updatable statement to execute queries.
                Statement stmt = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                //execute the query and store result in a resultset.
                String sql = "SELECT * FROM countries";
                ResultSet resultSet = stmt.executeQuery(sql);

                /*if ( resultSet.getConcurrency()!=ResultSet.CONCUR_UPDATABLE )
				System.out.println( "ResultSet not updatable" );*/
                
                char option; //selected option.
                Country country; //data to show or save.
                int id; //id value for the working entry.
                do {
                    //display menu and read option.
                    option = Menu();
                    //control bloc.
                    switch (option) {
                        case 'Q' -> {
                        }
                        case 'F' -> {
                            //move cursor to first registry.
                            resultSet.first();
                            country = ResultSetToCountry(resultSet);
                            System.out.println(country.toString());
                        }
                        case 'P' -> {
                            //move cursor to previous registry.
                            boolean success = resultSet.previous();
                            if (!success) {
                                resultSet.first();
                            }
                            country = ResultSetToCountry(resultSet);
                            System.out.println(country.toString());
                        }
                        case 'N' -> {
                            //move cursor to next registry.
                            boolean success = resultSet.next();
                            if (!success) {
                                resultSet.last();
                            }
                            country = ResultSetToCountry(resultSet);
                            System.out.println(country.toString());
                        }
                        case 'L' -> {
                            //move cursor to last registry.
                            resultSet.last();
                            country = ResultSetToCountry(resultSet);
                            System.out.println(country.toString());
                        }
                        case 'I' -> {
                            //insert a new registry.
                            country = readCountry();
                            insertRow(resultSet, country);
                            System.out.println(country.toString());
                        }
                        case 'U' -> {
                            //update a registry.
                            country = readCountry();
                            updateRow(resultSet, country);
                            country = ResultSetToCountry(resultSet);
                            System.out.println(country.toString());
                        }
                        default -> {
                        }
                    }
                    //exit
                } while (option != 'Q');
                //close resources.
                resultSet.close();
                stmt.close();
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Displays menu and reads option from user.
     *
     * @return the option selected by user.
     */
    private char Menu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] options = {"Quit", "First", "Previous", "Next", "Last", "Insert",
            "Update"};
        for (String option : options) {
            System.out.println("[" + option.charAt(0) + "]" + " - " + option);
        }
        System.out.print("Choose an option: ");
        char op = 'Q';
        try {
            op = ((br.readLine()).toUpperCase()).charAt(0);
        } catch (IOException ioe) {
        }
        return op;
    }

    /**
     * inserts a new row to resultset with data in country
     *
     * @param rs the resultset where to insert.
     * @param country the country with data to insert.
     */
    private void insertRow(ResultSet rs, Country country) {
        try {
            //move cursor to insertion row.
            rs.moveToInsertRow();
            //modify fields.
            //rs.updateLong( "id", p.getId() );
            rs.updateString("name", country.getName());
            rs.updateString("capital", country.getCapital());
            rs.updateDouble("surface", country.getSurface());
            rs.updateInt("inhabitants", country.getInhabitants());
            rs.updateDouble("pib", country.getPib());
            rs.updateInt("lifeexpectancy", country.getLifeexpectancy());
            //insert row
            rs.insertRow();
            //position cursor in current row.
            rs.moveToCurrentRow();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * updates data in current row to country data.
     *
     * @param rs the resultset where to update.
     * @param country the country with data to update.
     */
    private void updateRow(ResultSet rs, Country country) {
        try {
            //modify fields
            //rs.updateLong( "id", country.getId() );
            rs.updateString("name", country.getName());
            rs.updateString("capital", country.getCapital());
            rs.updateDouble("surface", country.getSurface());
            rs.updateInt("inhabitants", country.getInhabitants());
            rs.updateDouble("pib", country.getPib());
            rs.updateInt("lifeexpectancy", country.getLifeexpectancy());
            //submit the changes.
            rs.updateRow();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * reads data from resultset and builds a country with that data.
     *
     * @param resultSet the ResultSet to extract data from.
     * @return country object.
     */
    private Country ResultSetToCountry(ResultSet resultSet) throws SQLException {
        Country country;
        //retrieve the values of the fields.
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String capital = resultSet.getString("capital");
        double surface = resultSet.getDouble("surface");
        int inhabitants = resultSet.getInt("inhabitants");
        double pib = resultSet.getDouble("pib");
        int lifeexpectancy = resultSet.getInt("lifeexpectancy");
        //instantiate the object.
        country = new Country(id, name, capital, surface, inhabitants, pib, lifeexpectancy);
        return country;
    }

    /**
     * reads from standard input data for a country
     *
     * @return country object with entered data or null in case of error.
     */
    private Country readCountry() {
        Country country;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Name: ");
            String name = br.readLine();
            System.out.print("Capital: ");
            String capital = br.readLine();
            System.out.print("Surface: ");
            double surface = Double.parseDouble(br.readLine());
            System.out.print("Inhabitants: ");
            int inhabitants = Integer.parseInt(br.readLine());
            System.out.print("PIB: ");
            double pib = Double.parseDouble(br.readLine());
            System.out.print("Life expectancy: ");
            int lifeexpectancy = Integer.parseInt(br.readLine());
            country = new Country(0, name, capital, surface, inhabitants, pib, lifeexpectancy);
            return country;
        } catch (NumberFormatException | IOException e) {
            country = null;
        }
        return country;
    }
}
