package politica.projeto.n2.resource;

import politica.projeto.n2.api.Politica;

import politica.projeto.n2.dao.PoliticaDao;

import java.util.List;
import java.util.Scanner;

public class InterfacePolitica {
    PoliticaDao politicaDAO;
    Scanner in;


    public InterfacePolitica(PoliticaDao politicaDAO) {
        this.politicaDAO = politicaDAO;
        this.in = new Scanner(System.in);


    }

    public InterfacePolitica(){

    }

    public void iniciar() {
        imprimirMenu();
    }

    private void imprimirMenu() {
        int opc = 0;
        while (true) {
            System.out.println("Menu:");
            System.out.println("\t1. Criar");
            System.out.println("\t2. Ler");
            System.out.println("\t3. Atualizar");
            System.out.println("\t4. Deletar");
            System.out.println("\t5. Sair");
            opc = in.nextInt();

            in.nextLine();

            switch (opc) {
                case 1:
                    this.createPolitica();
                    break;
                case 2:
                    this.readPolitica();
                    break;
                case 3:
                    this.updatePolitica();
                    break;
                case 4:
                    this.deletePolitica();
                    break;
                case 5:
                    System.out.println("Fim do CRUD");
                    return;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }

    private void createPolitica() {
        Politica politica = new Politica();


        System.out.println(" Novos dados ");

        System.out.println("\nColoque a data da informação ");

            Scanner s = new Scanner(System.in);
            String dataRecebida = s.nextLine();

            politica.setDate(dataRecebida);

        politica.setValue(in.nextInt());


        if (politicaDAO.create(politica)) {
            System.out.println("Dados adicionados ao banco de dados");
        } else {
            System.out.println("Erro ao adicionar dados");
        }
    }


    private void readPolitica() {
        List<Politica> politicas = politicaDAO.read();


        System.out.println("  Dados cadastrados: ");

        for (Politica politica : politicas) {
            System.out.println("Id da data: " + politica.getId());
            System.out.println("Data do dado: " + politica.getDate());
            System.out.println("Valor do dia: " + politica.getValue() + "\n");

        }
    }



    private void updatePolitica() {
        Politica politica = new Politica();

        System.out.println(" Att data:");
        System.out.println("Coloque o ID para Modificação: ");

        politica.setId(in.nextInt());
        in.nextLine();

            Scanner s = new Scanner(System.in);
            System.out.println("Troque a data do valor: ");
            String dataRecebida = s.nextLine();

            politica.setDate(dataRecebida);

            System.out.println("Troque o valor do dia: ");
            politica.setValue(in.nextInt());

            if (politicaDAO.update(politica)) {
                System.out.println("Bd att!");
            } else {
                System.out.println("Erro na att do Bd");
            }
        }


    private void deletePolitica() {
        List<Politica> politicas = politicaDAO.read();


        while (true) {
            System.out.println(" Lista de dias: ");


            System.out.println(politicas);

            int a = 0;
            for (Politica politica : politicas) {
                System.out.println(a + ". Id do dia: " + politica.getId());
                System.out.println("  Data: " + politica.getDate());
                System.out.println("  Valor: " + politica.getValue());

                a++;

            }
            System.out.println(a + ". Cancelar a operação");

            System.out.println("Deseja tirar qual dia?\n");
            int resposta = in.nextInt();
            in.nextLine();

            if (resposta == a) {
                break;
            } else if (resposta > politicas.size() || resposta < 0) {
                System.out.println("Opção invalida");
            } else if (politicaDAO.delete(politicas.get(resposta))) {
                System.out.println("Dia: " + politicas.get(resposta).getDate() + " removido ");
            } else {
                System.out.println("Falha ao tentar remover!");
            }
            break;
        }
    }
}



