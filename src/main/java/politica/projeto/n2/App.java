package politica.projeto.n2;


import politica.projeto.n2.dao.PoliticaDAOMySQL;

import politica.projeto.n2.resource.InterfacePolitica;
import politica.projeto.n2.resource.PoliticaTrends;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.Scanner;

public class App extends Application<Configuration> {
    public static void main( String[] args ) {
        try {
            (new App()).run(args);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "hello world";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        AssetsBundle assetsBundle = new AssetsBundle("/site","/",   "index.html");
        bootstrap.addBundle(assetsBundle);
        super.initialize(bootstrap);
    }


    @Override
    public void run(Configuration configuration, Environment environment){


        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);


        cors.setInitParameter("allowedOrigins","*");
        cors.setInitParameter("allowedHeaders","X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods","OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true,"");






        environment.jersey().register(new PoliticaTrends());

        environment.jersey().setUrlPattern("/api/*");


        Scanner print = new Scanner(System.in);
        int escolha = 0;
        int escolha2 = 0;

        System.out.println("**** Menu ****");

        System.out.println("Deseja modificar 0 banco de dados?");
        System.out.println("Digite 1 para sim ou 2 para não:");
        escolha = print.nextInt();
        if (escolha == 1) {
                PoliticaDAOMySQL dao1 = new PoliticaDAOMySQL();
                InterfacePolitica politica = new InterfacePolitica(dao1);

            politica.iniciar();
            } if (escolha == 2) {
            System.out.println("Você saiu do programa!");
            System.out.println("Se quiser retornar ao programa, por favor de start novamente :) ");
            return;
        }
    }
    }

