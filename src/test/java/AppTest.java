

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import domain.*;
import repository.*;
import service.*;
import validation.*;

public class AppTest 
{

    private Service service;

    @Before
    public void ServiceSetup() 
    {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @After
    public void DataCleanup()
    {
        for(Tema t : service.findAllTeme()) 
        {
            service.deleteTema(t.getID());
        }
    }

    @Test
    public void Service_saveTema_Valid_Success()
    {
        assertEquals(1, service.saveTema("1", "1", 1, 1));
    }

    @Test
    public void Service_saveTema_Duplicate_Failure()
    {        
        service.saveTema("1", "1", 1, 1);
        assertEquals(0, service.saveTema("1", "1", 1, 1));
    }

    @Test
    public void Service_saveStudent_Valid_Success()
    {
        assertEquals(1, service.saveStudent("1", "1", 111));
    }

    @Test
    public void Service_saveStudent_Duplicate_Failure()
    {        
        service.saveStudent("1", "1", 111);
        assertEquals(0, service.saveStudent("1", "1", 111));
    }
}
