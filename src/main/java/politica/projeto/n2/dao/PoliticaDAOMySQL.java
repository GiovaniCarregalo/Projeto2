package politica.projeto.n2.dao;


import politica.projeto.n2.api.Politica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoliticaDAOMySQL implements PoliticaDao {
    private String createSQL = "INSERT INTO politicas (date, value) VALUES (?,?)";
    private String readSQL = "SELECT * FROM politicas";
    private String updateSQL= "UPDATE politicas SET date = ?, value = ? WHERE id = ?";
    private String deleteSQL = "DELETE FROM politicas WHERE id = ?";

    private final MySQLConnection mysql = new MySQLConnection();


    @Override
    public boolean create(Politica politica) {
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(createSQL);

            stm.setString(1, politica.getDate());
            stm.setInt(2, politica.getValue());
            int registro =stm.executeUpdate();
            return(registro>0);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally {
            try{
                conexao.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Politica> read() {
        Connection conexao = mysql.getConnection();

        List<Politica> politicas= new ArrayList();
        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet registro = stm.executeQuery();
            while (registro.next()) {
                Politica politica = new Politica();
                politica.setId(registro.getInt("id"));
                politica.setDate(registro.getString("date"));
                politica.setValue(registro.getInt("value"));

                politicas.add(politica);
            }
            return politicas;

        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        } catch (final Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                conexao.close();
            } catch (final SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return politicas;

    }

    @Override
    public boolean update(Politica politica) {
        Connection conexao = mysql.getConnection();
        int registros = -1;
        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setString(1, politica.getDate());
            stm.setInt(2, politica.getValue());
            stm.setInt(3, politica.getId());

            registros = stm.executeUpdate();



        } catch (final SQLException e){
            System.out.println("Falha de conexão com a base de dados!");
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
            } catch (final Exception e){
                e.printStackTrace();
            }
        }
        return registros > 0;


    }

    @Override
    public boolean delete(Politica politica) {
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);
            stm.setInt(1, politica.getId());
            int registros = stm.executeUpdate();
            return registros > 0;

        } catch (SQLException e){
            System.out.println("Erro de conexão co o Bd");
            e.printStackTrace();
        } catch(final Exception e){
            e.printStackTrace();
        } finally {
            try{
                conexao.close();
            } catch (final Exception e){
                e.printStackTrace();
            }
        }

        return false;


    }
}
