package org.example;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
public class LeerCSV {

    public List<Person> leerFichero() throws IOException, InvalidFormatLineException {
        Path filePath = Paths.get("C:\\Users\\luismiguel.gallego\\OneDrive - Bosonit\\Escritorio\\EjemploEjercicio1\\src\\people.csv");
        List<String> lines = Files.readAllLines(filePath);

        //Crear ArrayList para guardar los campos recibidos por el List<String> lines cuando el formato esté correcto
        List<Person> personas = new ArrayList<>();
        for (String line : lines) {
            //Cada String que obtiene lo va a dividir en palabras cuando por spliteo
            String[] values = line.split(":");

            if (values.length == 2) {
                throw new InvalidFormatLineException("Falta el úlimo delimitador de campo");
            } else if (values.length == 1) {
                throw new InvalidFormatLineException(("Faltan dos delimitadores de campo"));
            } else if (values[0].isEmpty()) {
                throw new InvalidFormatLineException("El campo name está vacío");
            } else {
                String name = values[0].trim();
                String town = values[1].trim();
                int age = Integer.parseInt(values[2].trim());
                //Guardar el objeto persona
                personas.add(new Person(name, town, age));
            }

        }
            return personas;
    }

}
