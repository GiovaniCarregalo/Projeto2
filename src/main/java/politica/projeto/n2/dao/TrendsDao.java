package politica.projeto.n2.dao;

import politica.projeto.n2.api.Politica;
import politica.projeto.n2.api.Result;
import politica.projeto.n2.api.Trends;
import politica.projeto.n2.api.Politica;
import politica.projeto.n2.dao.PoliticaDao;

import java.util.List;

public class TrendsDao {
    Trends dataBase;
    PoliticaDao politicaDAO;

    public TrendsDao(PoliticaDao politicaDAO) {
        this.politicaDAO = politicaDAO;
        this.dataBase = new Trends();
        read();
    }

    public void read() {

        List<Politica> politicas = politicaDAO.read();
        List<Politica> politicas2 = politicas;
        for (int i = 0; i < politicas.size(); i++) {
            Politica politica = politicas.get(i);
            Result result = new Result();
            result.setDate(String.valueOf(politica.getDate()));
            result.setValue(politica.getValue());
            this.dataBase.getResults().add(result);
        }


    }

    public Trends getAllTrends() {
        return this.dataBase;
    }

}
