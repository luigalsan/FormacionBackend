package org.bosonit;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
public class LeerCSV {

    public List<Person> leerFichero() throws IOException, InvalidFormatLineException {
        Path filePath = Paths.get("C:\\Users\\luismiguel.gallego\\IdeaProjects\\FormacionBackend\\block1-process-file-and-streams\\src\\main\\resources\\people.csv");
        List<String> lines = Files.readAllLines(filePath);

        //Crear ArrayList para guardar los campos recibidos por el List<String> lines cuando el formato esté correcto
        List<Person> personas = new ArrayList<>();
        for (String line : lines) {
            //Cada String que obtiene lo va a dividir en palabras cuando por spliteo
            String[] values = line.split(":", -1);
            System.out.println("Numero de columnas" + values.length);

            if (values.length == 2) {
                throw new InvalidFormatLineException("Falta el último delimitador de campo");
            } else if (values.length == 1) {
                throw new InvalidFormatLineException(("Faltan dos delimitadores de campo"));
            } else if (values[0].isEmpty()) {
                throw new InvalidFormatLineException("El campo name está vacío");
            } else {

                Integer age = !values[2].isEmpty() ? Integer.parseInt(values[2]) : 0;
                String town = !values[1].trim().isEmpty()?values[1]: "unknown";
                String name = values[0].trim();
                //Guardar el objeto persona
                personas.add(new Person(name, town, age));
            }

        }
            return personas;
    }

}
