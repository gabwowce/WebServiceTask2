import lt.eif.viko.gtamaseviciute.StudentEndpoint;
import lt.eif.viko.gtamaseviciute.StudentRepository;
import lt.eif.viko.gtamaseviciute.WebServiceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import java.io.IOException;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

/**
 * Testai, skirti {@link StudentEndpoint} klasei.
 * <p>
 * Testuojami šie scenarijai:
 * <ul>
 *     <li>Studento gavimas pagal vardą.</li>
 *     <li>Visų studentų sąrašo gavimas.</li>
 *     <li>Studentų gavimas pagal grupę.</li>
 * </ul>
 * <p>
 * Testai vykdomi naudojant {@link MockWebServiceClient}, kuris leidžia simuliuoti SOAP užklausas
 * ir patikrinti gautus atsakymus iš endpoint'o.
 * </p>
 */
@WebServiceServerTest
@ContextConfiguration(classes = { StudentEndpoint.class, StudentRepository.class, WebServiceConfig.class })
public class StudentEndpointTest {

    /**
     * Mock Web Service klientas SOAP užklausoms siųsti ir atsakymams testuoti.
     */
    @Autowired
    private MockWebServiceClient client;

    /**
     * Testinis studentų duomenų saugyklos bean'as, naudojamas testų duomenų paruošimui.
     */
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Prieš kiekvieną testą inicializuojami testiniai duomenys.
     */
    @BeforeEach
    public void setup() {
        studentRepository.initData();
    }

    /**
     * Testuoja studento paiešką pagal vardą.
     */
    @Test
    public void testGetStudent() throws IOException {
        StringSource request = new StringSource(
                """
                <getStudentRequest xmlns="http://viko.eif.lt/gtamaseviciute/studentWebSerice">
                    <name>Tomas</name>
                </getStudentRequest>
                """
        );

        StringSource expectedResponse = new StringSource(
                """
                        <getStudentResponse xmlns="http://viko.eif.lt/gtamaseviciute/studentWebSerice">
                            <student>
                                <name>Tomas</name>
                                <age>20</age>
                                <group>PI22A</group>
                                <averageGrade>7.33</averageGrade>
                                <active>true</active>
                                <gender>M</gender>
                                <subjects>
                                    <title>Diskrečioji matematika</title>
                                    <grade>9</grade>
                                </subjects>
                                <subjects>
                                    <title>Vadyba</title>
                                    <grade>8</grade>
                                </subjects>
                                <subjects>
                                    <title>Informacijos sistemos</title>
                                    <grade>5</grade>
                                </subjects>
                            </student>
                        </getStudentResponse>
                        """
        );

        client.sendRequest(withPayload(request))
                .andExpect(noFault())
                .andExpect(payload(expectedResponse));
    }


    /**
     * Testuoja visų studentų sąrašo gavimą.
     */
    @Test
    public void testGetStudents() throws IOException {
        StringSource request = new StringSource(
                """
                    <getStudentsRequest xmlns="http://viko.eif.lt/gtamaseviciute/studentWebSerice"/>
                """
        );

        StringSource expectedResponse = new StringSource(
                """
                        <getStudentsResponse xmlns="http://viko.eif.lt/gtamaseviciute/studentWebSerice">
                             <students>
                                 <name>Marija</name>
                                 <age>21</age>
                                 <group>PI23S</group>
                                 <averageGrade>7.33</averageGrade>
                                <active>true</active>
                                <gender>F</gender>
                                 <subjects>
                                     <title>Diskrečioji matematika</title>
                                     <grade>5</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Vadyba</title>
                                     <grade>9</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Informacijos sistemos</title>
                                     <grade>8</grade>
                                 </subjects>
                             </students>
                             
                             <students>
                                 <name>Tomas</name>
                                 <age>20</age>
                                 <group>PI22A</group>
                                 <averageGrade>7.33</averageGrade>
                                <active>true</active>
                                <gender>M</gender>
                                 <subjects>
                                     <title>Diskrečioji matematika</title>
                                     <grade>9</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Vadyba</title>
                                     <grade>8</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Informacijos sistemos</title>
                                     <grade>5</grade>
                                 </subjects>
                             </students>
                             
                              <students>
                                 <name>Antanas</name>
                                 <age>21</age>
                                 <group>PI23S</group>
                                 <averageGrade>9</averageGrade>
                                <active>true</active>
                                <gender>M</gender>
                                 <subjects>
                                     <title>Diskrečioji matematika</title>
                                     <grade>9</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Vadyba</title>
                                     <grade>10</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Informacijos sistemos</title>
                                     <grade>8</grade>
                                 </subjects>
                             </students>
                         </getStudentsResponse>
                        """
        );



        client.sendRequest(withPayload(request))
                .andExpect(noFault())
                .andExpect(payload(expectedResponse));
    }

    /**
     * Testuoja studentų gavimą pagal nurodytą grupę.
     */
    @Test
    public void testGetStudentsByGroup() throws IOException {
        StringSource request = new StringSource(
                """
                    <getStudentsByGroupRequest xmlns="http://viko.eif.lt/gtamaseviciute/studentWebSerice">
                        <group>PI22A</group>
                    </getStudentsByGroupRequest>
                """
        );

        StringSource expectedResponse = new StringSource(
                """
                        <getStudentsByGroupResponse xmlns="http://viko.eif.lt/gtamaseviciute/studentWebSerice">
                             <students>
                                 <name>Tomas</name>
                                 <age>20</age>
                                 <group>PI22A</group>
                                 <averageGrade>7.33</averageGrade>
                                <active>true</active>
                                <gender>M</gender>
                                 <subjects>
                                     <title>Diskrečioji matematika</title>
                                     <grade>9</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Vadyba</title>
                                     <grade>8</grade>
                                 </subjects>
                                 <subjects>
                                     <title>Informacijos sistemos</title>
                                     <grade>5</grade>
                                 </subjects>
                             </students>
                         </getStudentsByGroupResponse>
                        """
        );



        client.sendRequest(withPayload(request))
                .andExpect(noFault())
                .andExpect(payload(expectedResponse));
    }


}
