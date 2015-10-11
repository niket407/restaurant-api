package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.exception.AppException;
import restaurant.model.*;
import restaurant.util.DBUtils;

public class CustomerDAO {

	public List<Customer> fetchAll() throws AppException {
		List<Customer> custList = new ArrayList<Customer>();

		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customer");
			rs = ps.executeQuery();

			while (rs.next()) {
				Customer cus = new Customer();
				cus.setIdcustomer(rs.getInt("idcustomer"));
				cus.setName(rs.getString("Name"));
				cus.setEmail(rs.getString("Email"));
				cus.setGuest(rs.getString("Guest"));
				cus.setDateTime(rs.getString("DateTime"));
				cus.setComments(rs.getString("Comments"));

				custList.add(cus);
			}
		//	System.out.println("Size of the CustomerList()1:::"+custList.size());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeResource(ps, rs, con);
		}
		return custList;
	}
	
	public Customer findOne(int idcustomer) throws AppException {
		Customer custList = new Customer();

		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customer WHERE idcustomer=?");
			ps.setInt(1, idcustomer);
			rs = ps.executeQuery();

			if (rs.next()) {
				custList.setIdcustomer(rs.getInt("idcustomer"));
				custList.setName(rs.getString("Name"));
				custList.setEmail(rs.getString("Email"));
				custList.setGuest(rs.getString("Guest"));
				custList.setDateTime(rs.getString("DateTime"));
				custList.setComments(rs.getString("Comments"));
			}
			else {
				throw new AppException("Record not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeResource(ps, rs, con);
		}
		return custList;
	}
	
	public int delete(int idCustomer) throws AppException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int Success = 0;
		try {
			ps = conn.prepareStatement("DELETE FROM customer WHERE idcustomer=?");
			ps.setInt(1, idCustomer);
			Success = ps.executeUpdate();
			
			System.out.println("Record has been Deleted with ID " +idCustomer);
			
			
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
			DBUtils.closeResource(ps, rs, conn);
		}
		return Success;
	}
	
	public Customer create(Customer cus) throws AppException {

		Connection con = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("INSERT INTO customer (Idcustomer, Name, Email, Guest, DateTime, Comments) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, cus.getIdcustomer());
			ps.setString(2, cus.getName());
			ps.setString(3, cus.getEmail());
			ps.setString(4, cus.getGuest());
			ps.setString(5, cus.getDateTime());
			ps.setString(6, cus.getComments());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				cus.setIdcustomer(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, con);
		}

		return cus;
	}
	
	public Customer update(int Idcustomer, Customer reg) throws AppException {
		
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps = conn.prepareStatement("UPDATE customer SET Name=?, Email=?, Guest=?, DateTime=?, Comments=? WHERE idcustomer=?");
			ps.setString(1, reg.getComments());
			ps.setString(2, reg.getDateTime());
			ps.setString(3, reg.getGuest());
			ps.setInt(4, Idcustomer);
			ps.executeUpdate();
			System.out.println("Record Updated for ID" +Idcustomer);
			
			
			
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally{
			DBUtils.closeResource(ps, rs, conn);
		}
		

		
		return reg;
		// TODO Auto-generated method stub
		
	}
	}