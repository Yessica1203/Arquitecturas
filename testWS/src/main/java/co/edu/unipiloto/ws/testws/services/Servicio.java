package co.edu.unipiloto.ws.testws.services;

import co.edu.unipiloto.ws.testws.entidad.Empresa;
import co.edu.unipiloto.ws.testws.entidad.Personas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("service")

public class Servicio {

    private static Map<Integer, Personas> personitas = new HashMap<Integer, Personas>();
    private static Empresa salarioEmpresa = new Empresa();

    static {
        Empresa empresa = new Empresa();
        for (int i = 0; i < 10; i++) {
            Personas persona = new Personas();
            int id = i+1 ;
            persona.setId(id);
            persona.setNombre("maravillosa persona " + id);
            persona.setEdad(new Random().nextInt(40)+1);
            persona.setSalario(persona.getEdad() * 1000000 / 3);
            empresa.setSumaSalario(empresa.getSumaSalario() + persona.getSalario());

            personitas.put(id, persona);
        }
    }

    @GET
    @Path("/getSalarioInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa getSalarioInJson() {
        int values = 0;
        int count = 0;
        for (Personas value : personitas.values()) {
            values += value.getSalario();
            count++;
        }
        int resp = values / count;
        System.out.println("El salario promedio es: " + resp);
        System.out.println("La suma del salario es: " + values);
        Empresa company = new Empresa();
        company.setPromedioSalario(resp);
        company.setSumaSalario(values);
        return company;

    }

    @GET
    @Path("/getSalarioInXML")
    @Produces(MediaType.APPLICATION_XML)
    public Empresa getSalarioInXML() {
        int values = 0;
        int count = 0;
        for (Personas value : personitas.values()) {
            values += value.getSalario();
            count++;
        }
        int resp = values / count;
        System.out.println("El salario promedio es: " + resp);
        System.out.println("La suma del salario es: " + values);
        Empresa company = new Empresa();
        company.setPromedioSalario(resp);
        company.setSumaSalario(values);
        return company;

    }

    @GET
    @Path("/getPersonaByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Personas getPersonaByIdXML(@PathParam("id") int id) {
        return personitas.get(id);
    }

    @GET
    @Path("/getPersonaByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personas getPersonaByIdJson(@PathParam("id") int id) {
        return personitas.get(id);
    }

    @GET
    @Path("/getAllpersonaInXml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Personas> getAllpersonaXml() {
        return new ArrayList<Personas>(personitas.values());
    }

    @GET
    @Path("/getAllpersonaInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personas> getAllpersonaInJson() {
        return new ArrayList<Personas>(personitas.values());
    }

    @POST
    @Path("/addpersonaInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Personas addPersonaInJson(Personas person) {
        System.out.println(person.getId());
        personitas.put(new Integer(person.getId()), person);
        return person;
    }

}
