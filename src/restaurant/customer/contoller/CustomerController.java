package restaurant.customer.contoller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import restaurant.dao.CustomerDAO;
import restaurant.exception.AppException;
import restaurant.model.Customer;

@Path("/customer")
@Api(tags = { "customer" })
public class CustomerController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Customers" ,
	notes = "Finds all Customers in the DataBease")
	@ApiResponses(value = {
			@ApiResponse ( code = 200, message ="Success"),
			@ApiResponse ( code = 500, message ="Internal Server Error"),
			})
			
	public List<Customer> findAll(){
		System.out.println("Entered ::: findAll()");
		List<Customer> customers = null;
		try {
			CustomerDAO dao= new CustomerDAO();
			customers= dao.fetchAll();
		//	System.out.println("Size of the CustomerList()2:::"+customers.size());
			
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customers;
	} 
	
	@GET
	@Path("/{idcustomer}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Customers with id" ,
	notes = "Finds all Customers in the DataBase with id")
	@ApiResponses(value = {
			@ApiResponse ( code = 200, message ="Success"),
			@ApiResponse (code=404,message="not found"),
			@ApiResponse ( code = 500, message ="Internal Server Error"),
			})

	public Customer findOne(@PathParam("idcustomer") int idcustomer){
		try {
			CustomerDAO dao= new CustomerDAO();
			return dao.findOne(idcustomer);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create", notes = "Creates a customer")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Customer create(Customer cus) {
		try {
			CustomerDAO dao = new CustomerDAO();
			cus = dao.create(cus);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return cus;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( value="Updates a Registration", notes="Update a  Registration from the database")
	@ApiResponses( value={
			@ApiResponse ( code =200, message ="Success"),
			@ApiResponse(code=404 , message=" Registration Not found"),
			@ApiResponse (code = 500, message = "Internal Server Error")
	})
		public Customer update(@PathParam("idcustomer") int idcustomer, Customer cus){
			
			try {
				CustomerDAO dao =new CustomerDAO();
				cus=dao.update(idcustomer,cus);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cus;
	}

	
	@DELETE
	@Path("/delete/{idcustomer}")
	@ApiOperation(value = "Deletes Customer for the given ID" ,
	notes = "Finds and Delet the Customer")
	@ApiResponses(value = {
			@ApiResponse ( code = 200, message ="Success"),
			@ApiResponse (code=404,message="not found"),
			@ApiResponse ( code = 500, message ="Internal Server Error")
			})

	public int delete(@PathParam("idcustomer") int idcustomer) {
		int Success = 0;
		try {
			CustomerDAO dao =new CustomerDAO();
			Success=dao.delete(idcustomer);
		} catch (AppException e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return Success;
	    }

}
