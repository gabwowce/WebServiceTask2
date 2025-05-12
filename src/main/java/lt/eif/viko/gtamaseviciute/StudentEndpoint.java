package lt.eif.viko.gtamaseviciute;

import lt.eif.viko.gtamaseviciute.studentwebserice.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import static lt.eif.viko.gtamaseviciute.StudentUtils.calculateAverage;
import java.util.List;

/**
 * SOAP žinučių tvarkymo endpoint'as, atsakingas už studentų informacijos pateikimą.
 * <p>
 * Ši klasė apdoroja gaunamas SOAP užklausas ir grąžina atitinkamus atsakymus apie studentus,
 * naudodama {@link StudentRepository} duomenų šaltinį.
 * </p>
 * <p>
 * Palaikomi metodai:
 * <ul>
 *     <li>Gauti vieną studentą pagal vardą</li>
 *     <li>Gauti visų studentų sąrašą</li>
 *     <li>Gauti studentus pagal grupę</li>
 * </ul>
 * </p>
 */
@Endpoint
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://viko.eif.lt/gtamaseviciute/studentWebSerice";

    private StudentRepository studentRespository;

    /**
     * Konstruktorius, kuriam automatiškai priskiriamas {@link StudentRepository}.
     *
     * @param studentRepository studentų duomenų saugykla
     */
    @Autowired
    public StudentEndpoint(StudentRepository studentRepository){
        this.studentRespository = studentRepository;
    }

    /**
     * Apdoroja užklausą gauti konkretų studentą pagal vardą.
     *
     * @param request užklausa su studento vardu
     * @return atsakymas su rastu studentu
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request){
        GetStudentResponse response = new GetStudentResponse();
        response.setStudent(studentRespository.findStudent(request.getName()));
        return response;
    }

    /**
     * Apdoroja užklausą gauti visų studentų sąrašą.
     *
     * @param request užklausa be papildomų parametrų
     * @return atsakymas su visais studentais
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentsRequest")
    @ResponsePayload
    public GetStudentsResponse getStudent(@RequestPayload GetStudentsRequest request){
        GetStudentsResponse response = new GetStudentsResponse();
        response.getStudents().addAll(studentRespository.findAllStudents());
        return response;
    }

    /**
     * Apdoroja užklausą gauti studentus pagal jų grupę.
     *
     * @param request užklausa su grupės pavadinimu
     * @return atsakymas su studentais, priklausančiais nurodytai grupei
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentsByGroupRequest")
    @ResponsePayload
    public GetStudentsByGroupResponse getStudent(@RequestPayload GetStudentsByGroupRequest request){
        GetStudentsByGroupResponse response = new GetStudentsByGroupResponse();
        response.getStudents().addAll(studentRespository.findStudentsByGroup(request.getGroup()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentsAverageGradeRequest")
    @ResponsePayload
    public GetStudentsAverageGradeResponse getStudentsAverageGrade(@RequestPayload GetStudentsAverageGradeRequest request) {
        GetStudentsAverageGradeResponse response = new GetStudentsAverageGradeResponse();

        List<Student> all = studentRespository.findAllStudents();
        float avgGrade = calculateAverage(all);
        response.setAverageGrade(avgGrade);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setStudentActiveStatusRequest")
    @ResponsePayload
    public SetStudentActiveStatusResponse setStudentActiveStatus(@RequestPayload SetStudentActiveStatusRequest request) {
        SetStudentActiveStatusResponse response = new SetStudentActiveStatusResponse();

        Student student = studentRespository.findStudent(request.getName());
        student.setActive(request.isActive());
        response.setActive(student.isActive());

        return response;
    }


}
