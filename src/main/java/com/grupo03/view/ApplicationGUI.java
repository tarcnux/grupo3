package com.grupo03.view;

import com.grupo03.dao.CoffeeRoomDao;
import com.grupo03.dao.EventRoomDao;
import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.dao.PersonDao;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
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
        }while(opcao.equalsIgnoreCase("S"));


    }

    //metodo de cadastro de Pessoas
    public static void createPerson(){
        Person person;
        Scanner teclado = new Scanner(System.in);


        String name,lastName,opcao;
        int capMax,capAtual, aux;

        List<EventRoom> rooms;
        List<Person> pessoas;

        PersonDao personController = new PersonDao();
        EventRoomDao roomController = new EventRoomDao();

        pessoas = personController.getAll();
        capAtual = pessoas.size();

        rooms = roomController.getAll();
        capMax = rooms.get(1).getCapacity();

        //obter Capacidade Maxima
        for (EventRoom room:rooms
             ) {
            aux=room.getCapacity();
            if(capMax>aux){
                capMax=aux;
            }
            
        }        
        capMax =capMax*rooms.size();

        do {
            if(capAtual<capMax){
            System.out.println("Insira o nome:");
            name = teclado.nextLine();

            System.out.println("Insira o sobrenome:");
            lastName = teclado.nextLine();

            System.out.println("Nome: " + name  + lastName);
            person = new Person (name, lastName);
            person.setName(name);
            person.setLastname(lastName);
            personController.save(person);

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
        int op;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seja Bem vindo!");
        System.out.println("Lembre-se deve ser cadastrado as salas antes das pessoas");
        do {
            System.out.println("Selecione uma das opções abaixo: ");
            System.out.println("" +
                    "1)Cadastrar Salas\n" +
                    "2)Cadastrar Salas de Café\n" +
                    "3)Cadastrar Pessoas\n" +
                    "4)Consultar Pessoas\n" +
                    "5)Consultar Salas\n" +
                    "6)Consultar Salas de Café\n" +
                    "7)Alocar pessoas as salas\n"+
                    "0)Sair\n");
            op = teclado.nextInt();
            switch (op){
                case 1: createEventRooom();
                        break;
                case 2: createCoffeeRoom();
                        break;
                case 3: createPerson();
                        break;
                case 4: getPersonList();
                        break;
                case 5: getEventRoomList();
                        break;
                case 6: getCoffeeRoomList();
                        break;
                case 7: setPersonRoom();
                        break;
                case 0: break;
                default:
                    System.out.println("Opção Invalida");
           }
            limpar();
        }while(op!=0);
        System.out.println("Obrigado por usar o sistema!");
    }

}
