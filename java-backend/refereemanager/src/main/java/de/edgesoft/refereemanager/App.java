package de.edgesoft.refereemanager;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;

import static de.edgesoft.refereemanager.jooq.tables.People.PEOPLE;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/refereemanager";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	Result<Record> result = create.select().from(PEOPLE).fetch();
        	for (Record r : result) {
        	    UInteger id = r.getValue(PEOPLE.ID);
        	    String firstName = r.getValue(PEOPLE.FIRST_NAME);
        	    String lastName = r.getValue(PEOPLE.NAME);

        	    System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
        	}
        } 

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
