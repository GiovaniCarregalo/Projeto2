package politica.projeto.n2.dao;

import politica.projeto.n2.api.Politica;

import java.util.List;

public interface PoliticaDao {
boolean create(Politica politica);
List<Politica> read();
boolean update(Politica politica);
boolean delete(Politica politica);


}
