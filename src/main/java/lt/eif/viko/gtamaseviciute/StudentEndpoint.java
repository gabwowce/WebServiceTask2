package lt.eif.viko.gtamaseviciute;

import lt.eif.viko.gtamaseviciute.studentwebserice.GetStudentRequest;
import lt.eif.viko.gtamaseviciute.studentwebserice.GetStudentResponse;
import lt.eif.viko.gtamaseviciute.studentwebserice.GetStudentsRequest;
import lt.eif.viko.gtamaseviciute.studentwebserice.GetStudentsResponse;
import lt.eif.viko.gtamaseviciute.studentwebserice.GetStudentsByGroupRequest;
import lt.eif.viko.gtamaseviciute.studentwebserice.GetStudentsByGroupResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://viko.eif.lt/gtamaseviciute/studentWebSerice";

    private StudentRepository studentRespository;

    @Autowired
    public StudentEndpoint(StudentRepository studentRepository){
        this.studentRespository = studentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request){
        GetStudentResponse response = new GetStudentResponse();
        response.setStudent(studentRespository.findStudent(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentsRequest")
    @ResponsePayload
    public GetStudentsResponse getStudent(@RequestPayload GetStudentsRequest request){
        GetStudentsResponse response = new GetStudentsResponse();
        response.getStudents().addAll(studentRespository.findAllStudens());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentsByGroupRequest")
    @ResponsePayload
    public GetStudentsByGroupResponse getStudent(@RequestPayload GetStudentsByGroupRequest request){
        GetStudentsByGroupResponse response = new GetStudentsByGroupResponse();
        response.getStudents().addAll(studentRespository.findStudentsByGroup(request.getGroup()));
        return response;
    }
}
