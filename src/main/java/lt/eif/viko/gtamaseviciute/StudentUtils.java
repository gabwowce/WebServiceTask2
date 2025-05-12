package lt.eif.viko.gtamaseviciute;

import lt.eif.viko.gtamaseviciute.studentwebserice.Student;
import lt.eif.viko.gtamaseviciute.studentwebserice.Subject;

import java.util.List;
/**
 * <p>
 * Ši klasė skirta pagalbinėms funkcijos, pvz.: pažymių vidurkiui apskaičiuoti.
 * </p>
 */
public class StudentUtils {
    /**
     * Apskaičiuoja studento pažymių vidurkį pagal jo dalykų sąrašą.
     *
     * @param student studentas, kurio pažymių vidurkis skaičiuojamas
     * @return pažymių vidurkis (float)
     */
    public static float calculateAverage(Student student){
        List<Subject> subjects = student.getSubjects();
        int sum = 0;
        int size = 0;
        for(Subject subject : subjects){
            sum += subject.getGrade();
            size ++;
        }
        if(size == 0){
            return 0f;
        }
        else{
            return (float) sum / size;
        }
    }

    /**
     * Apskaičiuoja bendrą visų studentų pažymių vidurkį.
     *
     * @param students studentų sąrašas
     * @return visų pažymių vidurkis (float)
     */
    public static float calculateAverage(List<Student> students) {

        int sum = 0;
        int size = 0;

        for (Student student : students) {
            for (Subject subject : student.getSubjects()) {
                sum += subject.getGrade();
                size++;
            }
        }
        if(size == 0){
            return 0f;
        }else{
            return (float) sum / size;
        }
    }
}
