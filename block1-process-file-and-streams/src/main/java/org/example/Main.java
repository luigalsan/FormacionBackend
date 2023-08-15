package org.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        LeerCSV leer = new LeerCSV();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Imprimir fichero sin filtrar.\n" +
                "2. Filtrar menores de 25.\n" +
                "3. Obtener el primer elemento cuya ciudad sea Madrid.\n" +
                "4. Obtener el primer elemento cuya ciudad sea Barcelona ");

        try {
            int opcion = sc.nextInt();
            List<Person> ficheroLeido = leer.leerFichero();
            List<Person> ficheroFiltrado = menoresVeinticinco(ficheroLeido);
            switch(opcion) {
                case 1:
                    for (Person persona : ficheroLeido) {

                        System.out.println("Name: " + persona.getName() + (persona.getTown().isEmpty() ? "Town: unknown. " : "Town: " + persona.getTown()) + "Age: " + persona.getAge());

                            break;
                    }
                    case 2:

                    for(Person persona: ficheroFiltrado){

                        System.out.println("Name: " + persona.getName() + ". Town:" + persona.getTown() + ". Age: " + persona.getAge());
                        break;
                    }

                case 3:
                    Optional<Person> primeraPersonaDeMadrid = ficheroFiltrado.stream()
                            .filter(persona -> persona.getTown().equals("Madrid"))
                            .findFirst();

                    primeraPersonaDeMadrid.ifPresent(persona -> System.out.println("Primera persona de Madrid: " + persona.getName()));

                    break;

                case 4:
                    Optional<Person> primeraPersonaDeBarcelona = ficheroLeido.stream()
                            .filter(persona -> persona.getTown().equals("Barcelona"))
                            .findFirst();

                    primeraPersonaDeBarcelona.ifPresent(persona -> System.out.println("Primera persona de Barcelona: " + persona.getName()));

                    break;

            }

        } catch (IOException e) {
            System.out.println("Fichero no encontrado");

        } catch (InvalidFormatLineException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> menoresVeinticinco(List<Person> personas){
        List<Person> personasFiltradas = new ArrayList<>();

        for (Person persona : personas) {

            if (persona.getAge() < 25 && persona.getAge() > 0) {

                personasFiltradas.add(persona);
            }
        }
        return personasFiltradas;

    }
}

