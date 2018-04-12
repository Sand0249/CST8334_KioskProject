package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.ProfRegistry;
import objects.StuRegistry;
/**
 * This class is the implementation of ProfRegistryDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class ProfRegistryDAOImpl implements ProfRegistryDAO{

	private static final String INSERT_PROFREGISTRY = "INSERT INTO ProfRegistry (professor_id, course_id, term) VALUES(?, ?, ?)";
	private static final String SELECT_BY_PROFID = "SELECT * FROM ProfRegistry WHERE professor_id = ?";
	/**
	 * This is the search method when user want to 
	 * find a profRegistry object from profRegistry table 
	 * by professor id
	 * @author Jin Lu
	 *
	 */
	@Override
	public ProfRegistry findProfRegistryByProfID(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProfRegistry profRegistry = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_PROFID);			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			profRegistry = new ProfRegistry();
			profRegistry.setProfessor_id(rs.getString("professor_id"));
			profRegistry.setCourse_id(rs.getString("course_id"));
			profRegistry.setTerm(rs.getString("term"));
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		finally{
			try{ if(rs != null){ rs.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
		return profRegistry;
	}
	/**
	 * This is the insert method when user want to 
	 * insert any profRegistry object to profRegistry table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void insertProfRegistry(ProfRegistry profRegistry) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(INSERT_PROFREGISTRY);
			pstmt.setString(1, profRegistry.getProfessor_id());
			pstmt.setString(2, profRegistry.getCourse_id());
			pstmt.setString(3, profRegistry.getTerm());	
			pstmt.executeUpdate();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		finally{
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
	}



}
