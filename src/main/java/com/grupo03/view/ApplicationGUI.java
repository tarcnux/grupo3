package com.grupo03.view;

import com.grupo03.dao.AllocationDao;
import com.grupo03.dao.CoffeeRoomDao;
import com.grupo03.dao.EventRoomDao;
import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.dao.PersonDao;
import com.grupo03.persistence.EntityManagerProvider;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.*;


/**
 * Classe que contém os métodos que são chamados para executar cada funcionalidade do sistema.
 * Interface com o usuário.
 * @see com.grupo03.dao.AllocationDao
 * @see com.grupo03.dao.CoffeeRoomDao
 * @see com.grupo03.dao.EventRoomDao
 * @see com.grupo03.model.CoffeeRoom
 * @see com.grupo03.model.EventRoom
 * @see com.grupo03.model.Person
 * @see com.grupo03.dao.PersonDao
 * @see com.grupo03.persistence.EntityManagerProvider
 *
 * {@link #start()} Método que exibe o menu de funcionalidades do sistema.
 * {@link #createPerson()} Cria uma nova pessoa e a cadastra no banco de dados.
 * {@link #createEventRooom()} Cria uma nova sala de evento e salva no banco de dados.
 * {@link #createCoffeeRoom()} Cria uma nova sala de café e salva no banco de dados.
 * {@link #getPersonList()} Exibe as salas de evento e café em que a pessoa selecionada foi cadastrada.
 * {@link #getEventRoomList()} Exibe todas as pessoas que estão cadastradas na sala de evento selecionada,
 * durante as etapas 1 e 2.
 * {@link #getCoffeeRoomList()} Exibe todas as pessoas que estão cadastradas na sala de café selecionada,
 * durante as etapas 1 e 2.
 * {@link #setPersonRoom()} Distribui as pessoas cadastradas no sistema nas salas de evento e ambientes de café.
 * {@link #limpar()} Mantém a exibição dos menus mais organizada,
 * para facilitar a compreensão do que está sendo exibido.
 *
 * @author Jorge Davi Navarro
 * @author Tarcísio Nunes
 * @author Carlos Eduardo Ribeiro
 * @author Guilherme Peyerl Florêncio
 */
public class ApplicationGUI {

    /**
     * Método que cria as salas de eventos e cadastra no banco
     */
    public static void createEventRooom(){
        Scanner teclado = new Scanner(System.in);
        EventRoomDao eventController = new EventRoomDao();
        EventRoom   eventRoom;
        int capacity;
        String name,opcao;


        do {
            System.out.println("Insira o nome da sala");
            name = teclado.nextLine();

            System.out.println("Insira a capacidade máxima da sala");
            capacity = teclado.nextInt();

            System.out.println("Nome: " + name + "capacidade máxima:" + capacity);
            eventRoom = new EventRoom(name,capacity);
            eventController.save(eventRoom);

            System.out.println("Deseja inserir outra sala? S ou N");
            opcao = teclado.next();
            teclado.nextLine();
            limpar();
        }while(opcao.equalsIgnoreCase("S"));


    }

    /**
     * Método que cria as salas de café e salva no banco
     */
    public static void createCoffeeRoom(){
        Scanner teclado = new Scanner(System.in);
        CoffeeRoomDao coffeeController = new CoffeeRoomDao();
        CoffeeRoom coffeeRoom;
        String name,opcao;


        do {
            System.out.println("Insira o nome da sala do Café");
            name = teclado.nextLine();

            System.out.println("Nome: " + name );

            coffeeRoom = new CoffeeRoom(name);
            coffeeController.save(coffeeRoom);
            System.out.println("Deseja inserir outra sala do Café? S ou N");
            opcao = teclado.next();
            teclado.nextLine();
            limpar();
        } while(opcao.equalsIgnoreCase("S"));


    }

    /**
     * Método que cria as pessoas e salva no banco
     */
    public static void createPerson(){
        Person person;
        Scanner teclado = new Scanner(System.in);


        String name,lastName,opcao;
        int capAtual;

        List<EventRoom> rooms;
        List<CoffeeRoom> coffes;
        List<Person> pessoas;


        var personController = new PersonDao();
        var roomController = new EventRoomDao();
        var coffeeController = new CoffeeRoomDao();

        pessoas = personController.getAll();
        capAtual = pessoas.size();

        rooms = roomController.getAll();
        coffes = coffeeController.getAll();

        if(rooms.size() >= 2 && coffes.size() >= 2){

            // Busca a menor capacidade de sala:
            Optional<Integer> capMax =
                    rooms.stream().map(EventRoom::getCapacity).min(Comparator.naturalOrder());

            int capacidadeMaxima = capMax.get();

            capacidadeMaxima = capacidadeMaxima * rooms.size();

            do {
                if(capAtual<capacidadeMaxima){

                    System.out.println("Capacidade disponível: " + (capacidadeMaxima - capAtual));
                    System.out.println("Insira o nome:");
                    name = teclado.nextLine();

                    System.out.println("Insira o sobrenome:");
                    lastName = teclado.nextLine();

                    System.out.println("Nome: " + name + " " + lastName);
                    person = new Person (name, lastName);
                    person.setName(name);
                    person.setLastname(lastName);
                    personController.save(person);

                    capAtual++;
                    System.out.println("Deseja inserir outra Pessoa?? S ou N");
                    opcao = teclado.next();
                    teclado.nextLine();

                    limpar();
                }

                else {
                    System.out.println("Capacidade Máxima atingida. Retornando ao menu inicial.");
                    opcao="N";
                }
            }while(opcao.equalsIgnoreCase("S"));
        }else{
            System.out.println("Cadastre pelo menos 2 salas de Evento e 2 salas de Café antes de cadastrar pessoas");
        }
        }


    /**
     * Método que retorna as salas de evento e café em que uma pessoa esta alocada
     * @throws InputMismatchException
     * @throws IndexOutOfBoundsException
     */
    public static void getPersonList(){

        Scanner teclado = new Scanner(System.in);
        var em = EntityManagerProvider.getEntityManager();
        int opcao = 0;

        var pController = new PersonDao();

        List<Person> persons;
        persons = pController.getAll();

        Person person;

        do{
            limpar();
            System.out.println("Selecione a pessoa na lista abaixo:\n\n");
            int aux=1;
            for (Person p:persons) {
                System.out.println(aux+") "+ p.getName()+" "+p.getLastname());
                aux++;
            }
            System.out.println("Digite: ");
            try {
                opcao = teclado.nextInt();
                int id;
                id = persons.get(opcao-1).getId();
                person = em.find(Person.class,id);
                System.out.println("Etapa 1"
                        +"\nSala de Evento: "+ person.getEventRoomPersonList().get(0).getEventRoom().getName()
                        +"\nSala de Café: "+ person.getCoffeeRoomPersonList().get(0).getCoffeeRoom().getName()
                        +"\nAssento: "+ person.getSeat()
                        +"\n\nEtapa 2"
                        +"\nSala de Evento: "+ person.getEventRoomPersonList().get(1).getEventRoom().getName()
                        +"\nSala de Café: "  + person.getCoffeeRoomPersonList().get(1).getCoffeeRoom().getName()
                        +"\nAssento: "+ person.getSeat()
                );

                System.out.println("\n\n\nDeseja buscar outra Pessoa?\n1)Sim\n2)Não\nDigite:");
                opcao = teclado.nextInt();
            }catch (InputMismatchException inputError){
                System.out.println("A opção selecionada não é válida! Retornando ao Menu Principal");
                opcao = 2;
            }catch (IndexOutOfBoundsException indexBound){
                if (opcao > persons.size() || opcao < 1){
                    System.out.println("O valor digitado não corresponde a uma pessoa da lista");
                }else {
                    System.out.println("A função de alocar pessoas nas salas não foi executada! Selecione a opção 7 no menu Principal");
                    opcao = 2;
                }
            }
        }while(opcao!=2);
   }

    /**
     * Método que retorna uma lista de pessoas em uma sala de evento durante cada etapa
     * @throws InputMismatchException
     * @throws IndexOutOfBoundsException
     */
    public static void getEventRoomList(){
        var em = EntityManagerProvider.getEntityManager();
        var teclado = new Scanner(System.in);
        int opcao = 0;

        var ercontroller = new EventRoomDao();

        List<EventRoom> eventrooms;
        eventrooms = ercontroller.getAll();

        EventRoom ev1;
        do{

        System.out.println("Qual sala você deseja consultar?\nDigite:");

        int aux=1;
        //imprime todas as salas na tela
        for (EventRoom ev:eventrooms
             ) {
            System.out.println(aux+")" +ev.getName());
            aux++;
        }

        System.out.println("Digite: ");

        try{
            opcao=teclado.nextInt();

            int id;

            id = eventrooms.get(opcao-1).getId();

            ev1 = em.find(EventRoom.class,id);
            //imprime as pessoas na sala
            if(ev1.getPersonList(1).size() == 0 && ev1.getPersonList(2).size() == 0 ){
                System.out.println("Selecione a opção 7 do menu Principal para executar a alocação de pessoas nas salas antes de fazer as consultas");
            }else {
                System.out.println("Etapa 1 \n\t| Nome \t\t\t\t| Assento |");
                ev1.getPersonList(1).forEach(e ->
                        System.out.printf("\t|%-20s | %s |\n",e.getName() +" "+ e.getLastname(), e.getSeat()));
                System.out.println("");
                System.out.println("Etapa 2 \n\t| Nome \t\t\t\t| Assento |");
                ev1.getPersonList(2).forEach(e ->
                        System.out.printf("\t|%-20s | %s |\n",e.getName() +" "+ e.getLastname(), e.getSeat()));
            }
            System.out.println("\n\n\nDeseja buscar outra sala?\n1)Sim\n2)Não\nDigite:");
            opcao= teclado.nextInt();
        // Dispara a exceção caso o valor digitado pelo usuário não seja uma das opções exibidas
        }catch (InputMismatchException inputError){
            System.out.println("A opção selecionada não é válida! Retornando ao Menu Principal");
            opcao = 2;
        // Dispara a exceção caso a opção digitada não esteja na lista de opções ou caso a lista de pessoas
        // esteja vazia por não ter sido executado o método de alocação
        }catch (IndexOutOfBoundsException indexBound){
            if (opcao > eventrooms.size() || opcao < 1){
                System.out.println("O valor digitado não corresponde a uma sala de eventos da lista");
            }else {
                System.out.println("A função de alocar pessoas nas salas não foi executada! Selecione a opção 7 no menu Principal");
                opcao = 2;
            }
        }

        }while(opcao!=2);
}


    /**
     * Método que retorna uma lista de pessoas em uma sala de café durante cada etapa
     * @throws InputMismatchException
     * @throws IndexOutOfBoundsException
     */
    public static void getCoffeeRoomList(){

        var em = EntityManagerProvider.getEntityManager();
        var teclado = new Scanner(System.in);
        int opcao = 0;

        var cfcontroller = new CoffeeRoomDao();

        List<CoffeeRoom> coffeerooms;
        coffeerooms = cfcontroller.getAll();

        CoffeeRoom ev1;
        do{

            System.out.println("Qual sala do café você deseja consultar?");

            int aux=1;
            //imprime todas as salas na tela
            for (CoffeeRoom cr:coffeerooms
            ) {
                System.out.println(aux+")" +cr.getName());
                aux++;
            }
            System.out.println("Digite: ");
            try{
                opcao=teclado.nextInt();

                int id;
                id = coffeerooms.get(opcao-1).getId();
                ev1 = em.find(CoffeeRoom.class,id);

                //imprime as pessoas na sala
                if(ev1.getPersonList(1).size() == 0 && ev1.getPersonList(2).size() == 0 ){
                    System.out.println("Selecione a opção 7 do menu Principal para executar a alocação de pessoas nas salas antes de fazer as consultas");
                }else {
                    System.out.println("Etapa 1 \n\t| Nome \t\t\t\t| Assento |");
                    ev1.getPersonList(1).forEach(e ->
                            System.out.printf("\t|%-20s | %s |\n",e.getName() +" "+ e.getLastname(), e.getSeat()));
                    System.out.println("");
                    System.out.println("Etapa 2 \n\t| Nome \t\t\t\t| Assento |");
                    ev1.getPersonList(2).forEach(e ->
                            System.out.printf("\t|%-20s | %s |\n",e.getName() +" "+ e.getLastname(), e.getSeat()));
                }
                System.out.println("\n\n\nDeseja buscar outra sala de café?\n1)Sim\n2)Não\nDigite:");
                opcao= teclado.nextInt();
            // Dispara a exceção caso o valor digitado pelo usuário não seja uma das opções exibidas
            }catch (InputMismatchException inputError){
                System.out.println("A opção selecionada não é válida! Retornando ao Menu Principal");
                opcao = 2;
            // Dispara a exceção caso a opção digitada não esteja na lista de opções ou caso a lista de pessoas
            // esteja vazia por não ter sido executado o método de alocação
            }catch (IndexOutOfBoundsException indexBound){
                if (opcao > coffeerooms.size() || opcao < 1){
                    System.out.println("O valor digitado não corresponde a uma sala de eventos da lista");
                }else {
                    System.out.println("A função de alocar pessoas nas salas não foi executada! Selecione a opção 7 no menu Principal");
                    opcao = 2;
                }
            }

        }while(opcao!=2);


    }

    /**
     * Método de alocação de pessoas nas salas e ambientes de café
     */
    public static void setPersonRoom(){
        /*
            Para rodar este método uma segunda vez, é necessário limpar as tabelas
            associativas antes.
            delete from tbCoffeeRoomPerson where idCoffeeRoomPerson > 0;
            SELECT * FROM prowayeventsmanager_db.tbCoffeeRoomPerson;

            delete from tbEventRoomPerson where idEventRoomPerson > 0;
            SELECT * FROM prowayeventsmanager_db.tbEventRoomPerson;
         */

        PersonDao listPerson = new PersonDao();
        EventRoomDao eventDao = new EventRoomDao();
        CoffeeRoomDao coffeeDao = new CoffeeRoomDao();
        List<CoffeeRoom> coffeeRooms = coffeeDao.getAll();
        List<EventRoom> rooms = eventDao.getAll();
        List<Person> people = listPerson.getAll();
        AllocationDao alok = new AllocationDao(people);

        if (people.size() < 2 || rooms.size() < 2 || coffeeRooms.size() < 2){
            System.out.println("Cadastre pelo menos, 2 pessoas, 2 salas de Evento e 2 salas de café");
        }else{
            alok.alocar();

            System.out.println("Usuários alocados com sucesso!!");
        }
    }

    /**
     * Método que "limpa" a tela para deixar os dados em exibição mais organizados
     */
    public static void limpar(){
        for(int i=0;i<20;i++) {System.out.println("");}
    }

    /**
     * Método que inicia a aplicação exibindo o menu de funcinalidades
     */
    public static void start() {
        String op;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seja Bem vindo!");
        System.out.println("Lembre-se deve ser cadastrado as salas antes das pessoas");
        do {
            System.out.println("Selecione uma das opções abaixo: ");
            System.out.print("" +
                    "\t1)Cadastrar Salas\n" +
                    "\t2)Cadastrar Salas de Café\n" +
                    "\t3)Cadastrar Pessoas\n" +
                    "\t4)Consultar Pessoas\n" +
                    "\t5)Consultar Salas\n" +
                    "\t6)Consultar Salas de Café\n" +
                    "\t7)Alocar pessoas as salas\n"+
                    "\t0)Sair\n\n" +
                    "Digite: ");
            op = teclado.nextLine();

            switch (op){
                case "1": createEventRooom();
                        break;
                case "2": createCoffeeRoom();
                        break;
                case "3": createPerson();
                        break;
                case "4": getPersonList();
                        break;
                case "5": getEventRoomList();
                        break;
                case "6": getCoffeeRoomList();
                        break;
                case "7": setPersonRoom();
                        break;
                case "0": break;
                default:
                    System.out.println("Opção Invalida");
           }
            limpar();
        }while(!op.equals("0"));
        System.out.println("Obrigado por usar o sistema!");
    }

}
