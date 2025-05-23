package lt.eif.viko.gtamaseviciute;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


/**
 * Web Service konfigūracija, atsakinga už SOAP žinučių nukreipimą ir WSDL generavimą.
 * <p>
 * Ši klasė konfigūruoja Spring Web Services funkcionalumą:
 * <ul>
 *     <li>Registruoja {@link MessageDispatcherServlet}, kuris apdoroja SOAP užklausas.</li>
 *     <li>Apibrėžia WSDL failo generavimą naudojant XSD schemą.</li>
 *     <li>Nurodo, kokią XML schemą naudoti studentų duomenims aprašyti.</li>
 * </ul>
 * </p>
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean (name = "students")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("StudentsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://viko.eif.lt/gtamaseviciute/studentWebSerice");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema studentsSchema(){
        return new SimpleXsdSchema(new ClassPathResource("students.xsd"));
    }
}
