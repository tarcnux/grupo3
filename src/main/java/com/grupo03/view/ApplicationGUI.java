package com.grupo03.view;

import com.grupo03.dao.CoffeeRoomDao;
import com.grupo03.dao.EventRoomDao;
import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.dao.PersonDao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ApplicationGUI {

    //metodo de cadastro de Salas de evento
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

    //metodo de cadastro de sala de café
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

    //metodo de cadastro de Pessoas
    public static void createPerson(){
        Person person;
        Scanner teclado = new Scanner(System.in);


        String name,lastName,opcao;
        int capAtual;

        List<EventRoom> rooms;
        List<Person> pessoas;

        PersonDao personController = new PersonDao();
        EventRoomDao roomController = new EventRoomDao();

        pessoas = personController.getAll();
        capAtual = pessoas.size();

        rooms = roomController.getAll();
//        capMax = rooms.get(0).getCapacity();1

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

                System.out.println("Nome: " + name  + lastName);
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
    }

    //metodo de consultar Pessoas
    public static void getPersonList(){}

    //metodo de consultar sala de eventos
    public static void getEventRoomList(){



    }

    //metodo de consultar sala de café
    public static void getCoffeeRoomList(){}

    //metodo de alocar pessoas as salas
    public static void setPersonRoom(){



    }

    //limpar tela
    public static void limpar(){
        for(int i=0;i<70;i++) {System.out.println("");}
    }

    //metodo que inicia a aplicação, onde está localizado as opções do menu
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
