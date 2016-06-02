/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionAbases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
     /**
     * Este metodo serve para insertar datos na base
     *
     * @param tab Nome da taboa a insertar
     * @param valores Un array de valores para insertar na tabla
     * @throws java.sql.SQLException
     */
    public static void insertar(String tab, String[] valores) throws SQLException {

        String consulta = "insert into " + tab + " values(";
        for (int i = 0; i < valores.length; i++) {
            if (i < valores.length - 1) {
                consulta += "'" + valores[i] + "',";
            } else {
                consulta += "'" + valores[i] + "'";
            }
        }
        consulta += ");";

        s = conexion.createStatement();
        s.executeUpdate(consulta);
    }
     /**
     * Pecha o fluxo da base
     */
    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Devolve todo o que esta na base de datos
     *
     * @param tab Nome da taboa que se quere consultar
  
     */
    public static ResultSet consultar(String tab) {
        ResultSet rs = null;
        Statement s = null;
        try {
            s = conexion.createStatement();
            rs = s.executeQuery("select * from " + tab + ";");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs;
    }
    /**
     * Elimina un registro de una tabla
     *
     * @param tab Nome da taboa que se quere eliminar o rexistro
     * @param primaryKeyCol Nome da columna da clave primaria
     * @param primaryKeyVal Valor da clave Primaria
     * @throws java.sql.SQLException
     */
    public static void eliminar(String primaryKeyCol, String primaryKeyVal, String tab) throws Exception {
        if (primaryKeyVal.equals("null")) {
            throw new Exception("Fila vacía");
        }
        Statement s = null;
        try {
            s = conexion.createStatement();
            s.executeUpdate("delete from " + tab + " where " + primaryKeyCol + "='" + primaryKeyVal + "';");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                s.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
