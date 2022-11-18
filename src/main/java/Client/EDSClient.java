/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Client;

import com.muzammilnagariya.edsapp.entity.Department;
import com.muzammilnagariya.edsapp.entity.Employee;
import com.muzammilnagariya.edsapp.entity.Salary;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author muzz
 */
@RegisterRestClient(baseUri = "http://localhost:8080/EDSApp/rest/example")
public interface EDSClient {

    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getAllEmployee();

    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllDepartment")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Department> getAllDepartment();

    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeByDeptId/{deptId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getAllEmployeeByDeptId(@PathParam("deptId") Integer deptId);

    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeSalaryDetailsByDeptId/{deptId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Salary> getAllEmployeeSalaryDetailsByDeptId(@PathParam("deptId") Integer deptId);
    
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeSalaryDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Salary> getAllEmployeeSalaryDetails();
    
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeDetailsBySalary/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Salary> getAllEmployeeDetailsBySalary(@PathParam("amount") Integer amount);
    
    
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeDetailsBySalaryAndDeptId/{amount}/{deptId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Salary> getAllEmployeeDetailsBySalaryAndDeptId(@PathParam("amount") Integer amount,@PathParam("deptId") Integer deptId);
    
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeDetailsByAgeRange/{start}/{end}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Salary> getAllEmployeeDetailsByAgeRange(@PathParam("start") Integer start, @PathParam("end") Integer end);

    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getAllEmployeeByDateRange/{fromDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Salary> getAllEmployeeByDateRange(@PathParam("fromDate") String fromDate, @PathParam("endDate") String endDate);

    default String generateToken() {
        Config config = ConfigProvider.getConfig();
        String token = "Bearer " + config.getValue("jwt-string", String.class);
        return token;
    }
}