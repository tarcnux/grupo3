package com.grupo03.application;


import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;
import com.grupo03.view.ApplicationGUI;

public class Main {

    public static void main(String[] args) {

        var em = EntityManagerProvider.getEntityManager();

        ApplicationGUI.start();

    }

}
