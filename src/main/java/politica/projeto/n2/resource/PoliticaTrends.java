package politica.projeto.n2.resource;



import politica.projeto.n2.api.Politica;

import politica.projeto.n2.api.Trends;
import politica.projeto.n2.dao.PoliticaDAOMySQL;
import politica.projeto.n2.dao.TrendsDao;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("trends")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PoliticaTrends {

        PoliticaDAOMySQL dao1 = new PoliticaDAOMySQL();
        TrendsDao politicas = new TrendsDao(dao1);


        @GET
        public Trends getPoliticas () {

            return politicas.getAllTrends();
        }

}

