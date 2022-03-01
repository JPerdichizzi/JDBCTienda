/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Persistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joaqu
 */
public abstract class DAO {

    protected Connection connection = null;
    protected ResultSet result = null;
    protected Statement statement = null;
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    protected void connectDB() throws Exception {
        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            connection = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    protected void disconnectDB() throws Exception {
        try {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected void insertModifyDelete(String sql) throws Exception {
        try {
            connectDB();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            // conexion.rollback();
            /*
                Descomentar la linea anterior si desean tener en cuenta el rollback.
                Correr el siguiente script en Workbench
            
                SET autocommit=1;
                COMMIT;
            
                **Sin rollback igual anda
             */
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    protected void queryDataBase(String sql) throws Exception {
        try {
            connectDB();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
