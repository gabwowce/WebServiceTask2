package lt.eif.viko.gtamaseviciute;

import lt.eif.viko.gtamaseviciute.studentwebserice.Student;
import lt.eif.viko.gtamaseviciute.studentwebserice.Subject;
import static lt.eif.viko.gtamaseviciute.StudentUtils.calculateAverage;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Studentų duomenų saugykla (repository), laikanti studentų duomenis.
 * <p>
 * Ši klasė simuliuoja duomenų bazę, saugodama informaciją apie studentus ir jų mokomuosius dalykus (subjects).
 * Duomenys inicializuojami panaudojus {@link PostConstruct} metodą {@link #initData()}.
 * </p>
 * <p>
 * Galimos operacijos:
 * <ul>
 *     <li>Rasti studentą pagal vardą</li>
 *     <li>Gauti visų studentų sąrašą</li>
 *     <li>Gauti studentus pagal grupę</li>
 * </ul>
 * </p>
 */
@Component
public class StudentRepository {
    /**
     * Studentų saugykla, kur raktas yra studento vardas.
     */
    private static final Map<String, Student> students = new HashMap<>();

    /**
     * Inicializuoja testinius studentų duomenis su jų mokomaisiais dalykais.
     * <p>
     * Šis metodas iškviečiamas automatiškai aplikacijos paleidimo metu.
     * </p>
     */
    @PostConstruct
    public void initData(){

        Subject student1Subj1 = new Subject();
        student1Subj1.setTitle("Diskrečioji matematika");
        student1Subj1.setGrade(9);
        Subject student1Subj2 = new Subject();
        student1Subj2.setTitle("Vadyba");
        student1Subj2.setGrade(8);
        Subject student1Subj3 = new Subject();
        student1Subj3.setTitle("Informacijos sistemos");
        student1Subj3.setGrade(5);

        Student student1 = new Student();
        student1.setName("Tomas");
        student1.setAge(20);
        student1.setGroup("PI22A");
        student1.getSubjects().add(student1Subj1);
        student1.getSubjects().add(student1Subj2);
        student1.getSubjects().add(student1Subj3);

        student1.setAverageGrade(calculateAverage(student1));
        student1.setActive(true);
        student1.setGender("M");

        students.put(student1.getName(), student1);


        Subject student2Subj1 = new Subject();
        student2Subj1.setTitle("Diskrečioji matematika");
        student2Subj1.setGrade(5);
        Subject student2Subj2 = new Subject();
        student2Subj2.setTitle("Vadyba");
        student2Subj2.setGrade(9);
        Subject student2Subj3 = new Subject();
        student2Subj3.setTitle("Informacijos sistemos");
        student2Subj3.setGrade(8);

        Student student2 = new Student();
        student2.setName("Marija");
        student2.setAge(21);
        student2.setGroup("PI23S");
        student2.getSubjects().add(student2Subj1);
        student2.getSubjects().add(student2Subj2);
        student2.getSubjects().add(student2Subj3);

        student2.setAverageGrade(calculateAverage(student2));
        student2.setActive(true);
        student2.setGender("F");

        students.put(student2.getName(), student2);


        Subject student3Subj1 = new Subject();
        student3Subj1.setTitle("Diskrečioji matematika");
        student3Subj1.setGrade(9);

        Subject student3Subj2 = new Subject();
        student3Subj2.setTitle("Vadyba");
        student3Subj2.setGrade(10);

        Subject student3Subj3 = new Subject();
        student3Subj3.setTitle("Informacijos sistemos");
        student3Subj3.setGrade(8);


        Student student3 = new Student();
        student3.setName("Antanas");
        student3.setAge(21);
        student3.setGroup("PI23S");
        student3.getSubjects().add(student3Subj1);
        student3.getSubjects().add(student3Subj2);
        student3.getSubjects().add(student3Subj3);

        student3.setAverageGrade(calculateAverage(student3));
        student3.setActive(false);
        student3.setGender("M");

        students.put(student3.getName(), student3);

    }

    /**
     * Randa studentą pagal jo vardą.
     *
     * @param name studento vardas
     * @return studentas
     */
    public Student findStudent(String name) {
        return students.get(name);
    }

    /**
     * Grąžina visų studentų sąrašą.
     *
     * @return sąrašas visų studentų
     */
    public List<Student> findAllStudents() {
        return new ArrayList<>(students.values());
    }

    /**
     * Grąžina studentus pagal nurodytą grupės pavadinimą.
     *
     * @param group grupės pavadinimas
     * @return sąrašas studentų, kurie priklauso nurodytai grupei
     */
    public List<Student> findStudentsByGroup(String group) {
        List<Student> studentsByGroup = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getGroup().equals(group)) {
                studentsByGroup.add(student);
            }
        }
        return studentsByGroup;
    }
}
