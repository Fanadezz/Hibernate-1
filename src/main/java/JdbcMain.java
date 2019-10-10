import java.sql.*;

public class JdbcMain {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        // TODO: 10/5/2019 create a DB Connection
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:contactmgr.db")) {
//TODO: 10/5/2019 Create a SQL Statement

            Statement statement = connection.createStatement();
// TODO: 10/5/2019  Create DB Table

            statement.executeUpdate("DROP TABLE IF EXISTS contacts");
            statement.executeUpdate("CREATE TABLE contacts (id INTEGER PRIMARY KEY, firstname STRING, lastname " +
                                            "STRING, email STRING, phone INTEGER(10))");
            // TODO: 10/5/2019  Insert a couple of contacts
            Contact c = new Contact("Tonnie", "Vicenzio", "vontonnie@yahoo.com", 723445813L);
save(c,statement);
            c = new Contact("Vontonnie","Fanadez", "vontonnie@gmail.com", 723445813L );
            save(c,statement);

// TODO: 10/5/2019  Fetch all the records from contacts table

            ResultSet rs = statement.executeQuery("SELECT * FROM contacts");
            while (rs.next()) {

                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");

                System.out.printf("%s %s (%d)", firstName, lastName, id);
            }
// TODO: 10/5/2019  Iterate over the ResultSet and display contact info
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void save(Contact contact, Statement statement) throws SQLException {
        String sql = "INSERT INTO contacts (firstname, lastname, email, phone) VALUES ('%s', '%s', '%s', '%d')";
        sql = String.format(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());

        statement.executeUpdate(sql);
    }
}
