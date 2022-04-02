

import domain.*;
import repository.*;
import service.*;
import validation.*;

public class Main {

    private static Service SetUpService() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        return new Service(fileRepository1, fileRepository2, fileRepository3);
        
    }

    private static void AddGrade() {
        // setup
        Service service = SetUpService();
        service.saveStudent("1", "1", 111);
        service.saveTema("1", "1", 1, 1);

        // content
        service.saveNota("1", "1", 1.0, 1, "1");

        // cleanup
        service.deleteStudent("1");
        service.deleteTema("1");
    }

    private static void AddAssignment() {
        // setup
        Service service = SetUpService();

        // content
        service.saveTema("1", "1", 1, 1);

        // cleanup
        service.deleteTema("1");
    }

    private static void AddStudent() {
        // setup
        Service service = SetUpService();
        
        // content
        service.saveStudent("1", "1", 111);

        // cleanup
        service.deleteStudent("1");
    }

    private static void RunTests() {
        AddStudent();
        AddAssignment();
        AddGrade();
    }


    public static void main(String[] args) {
        RunTests();
        
        /*
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();
        */


        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
