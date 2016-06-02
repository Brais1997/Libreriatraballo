/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionAbases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author balva
 */
public class Acceso {
      private static Connection conexion;
    private static Statement s;
    private static final String SERVER = "jdbc:mysql://";

    /**
     * Conecta o programa ó servidor da base de datos
     *
     * @param HOST
     * @param BD Nome da base de datos
     * @param USER Usuario da base de datos
     * @param PASSWORD Contraseña dO usuario
     * @return Se saiu ben a conexion ca base de datos
     */

    public static String conectar(String BD, String USER, String PASSWORD, String HOST) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(SERVER + HOST + "/" + BD, USER, PASSWORD);
            return "Conecta á base de datos " + SERVER + HOST + "/" + BD + " ... OK";
        } catch (ClassNotFoundException ex) {
            return "Hai un erro no MySQL JDBC";
        } catch (SQLException ex) {
            return "Imposible realizar conexion con " + SERVER + HOST + "/" + BD + " ... Erro";
        }

    }
}