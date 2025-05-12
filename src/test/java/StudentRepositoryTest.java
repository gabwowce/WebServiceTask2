

import lt.eif.viko.gtamaseviciute.StudentRepository;
import  lt.eif.viko.gtamaseviciute.studentwebserice.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Testų klasė, skirta {@link StudentRepository} metodų testavimui.
 * <p>
 * Testuojami šie funkcionalumai:
 * <ul>
 *     <li>Studento paieška pagal vardą.</li>
 *     <li>Visų studentų sąrašo gavimas.</li>
 *     <li>Studentų paieška pagal grupės pavadinimą.</li>
 * </ul>
 * </p>
 */
public class StudentRepositoryTest {
    /**
     * Testinis studentų duomenų saugyklos objektas.
     */
    private StudentRepository studentRepository;

    /**
     * Inicializuoja testinius duomenis prieš kiekvieną testą.
     */
    @BeforeEach
    public void setUp() {
        studentRepository = new StudentRepository();
        studentRepository.initData();
    }

    /**
     * Testuoja studento paiešką pagal vardą.
     */
    @Test
    public void testFindStudentByName() {
        Student student = studentRepository.findStudent("Tomas");
        assertNotNull(student);
        assertEquals("Tomas", student.getName());
        assertEquals("PI22A", student.getGroup());
    }

    /**
     * Testuoja visų studentų sąrašo gavimą.
     */
    @Test
    public void testFindAllStudents() {
        List<Student> students = studentRepository.findAllStudents();
        assertEquals(3, students.size());
    }

    /**
     * Testuoja studentų paiešką pagal grupės pavadinimą.
     */
    @Test
    public void testFindStudentsByGroup() {
        List<Student> students = studentRepository.findStudentsByGroup("PI23S");
        assertFalse(students.isEmpty());
        assertEquals(2, students.size());
        for (Student student : students) {
            assertEquals("PI23S", student.getGroup());
        }
    }
}
