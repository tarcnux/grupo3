package com.grupo03.view;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ApplicationGUI {
    //metodo de cadastro de Salas de evento
    public static void createEventRooom(){
        Scanner teclado = new Scanner(System.in);
        int capacidade;
        String nome,opcao;


        do {
            System.out.println("Insira o nome da sala");
            nome = teclado.nextLine();

            System.out.println("Insira a capacidade máxima da sala");
            capacidade = teclado.nextInt();

            System.out.println("Nome: " + nome + "capacidade máxima:" + capacidade);

            System.out.println("Deseja inserir outra sala? S ou N");
            opcao = teclado.next();
            teclado.nextLine();
            limpar();
        }while(opcao.equalsIgnoreCase("S"));


    }

    //metodo de cadastro de sala de café
    public static void createCoffeeRoom(){
        Scanner teclado = new Scanner(System.in);

        String nome,opcao;


        do {
            System.out.println("Insira o nome da sala do Café");
            nome = teclado.nextLine();

            System.out.println("Nome: " + nome );

            System.out.println("Deseja inserir outra sala do Café? S ou N");
            opcao = teclado.next();
            teclado.nextLine();
            limpar();
        }while(opcao.equalsIgnoreCase("S"));


    }

    //metodo de cadastro de Pessoas
    public static void createPerson(){
        Scanner teclado = new Scanner(System.in);
        String nome,sobrenome,opcao;


        do {
            System.out.println("Insira o nome:");
            nome = teclado.nextLine();

            System.out.println("Insira o sobrenome:");
            sobrenome = teclado.nextLine();

            System.out.println("Nome: " + nome  + sobrenome);

            System.out.println("Deseja inserir outra Pessoa?? S ou N");
            opcao = teclado.next();
            teclado.nextLine();
            limpar();
        }while(opcao.equalsIgnoreCase("S"));


    }

    //metodo de consultar Pessoas
    public static void getPersonList(){}

    //metodo de consultar sala de eventos
    public static void getEventRoomList(){}

    //metodo de consultar sala de café
    public static void getCoffeeRoomList(){}

    //metodo de alocar pessoas as salas
    public static void setPersonRoom(){}

    //limpar tela
    public static void limpar(){
        for(int i=0;i<70;i++) {System.out.println("");}
    }

    //metodo que inicia a aplicação, onde está localizado as opções do menu
    public static void start() {
        int op=0;
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
