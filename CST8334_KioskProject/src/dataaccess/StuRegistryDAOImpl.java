package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objects.StuRegistry;
/**
 * This class is the implementation of StuRegistryDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class StuRegistryDAOImpl implements StuRegistryDAO{
	
	private static final String INSERT_STUREGISTRY = "INSERT INTO StuRegistry (student_id, course_id, term) VALUES(?, ?, ?)";
	private static final String SELECT_BY_STUID = "SELECT * FROM StuRegistry WHERE student_id = ?";
	/**
	 * This is the search method when user want to 
	 * find a StuRegistry object from StuRegistry table 
	 * by student id
	 * @author Jin Lu
	 *
	 */
	@Override
	public StuRegistry findStuRegistryByStuID(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StuRegistry stuRegistry = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_STUID);			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			stuRegistry = new StuRegistry();
			stuRegistry.setStudent_id(rs.getString("student_id"));
			stuRegistry.setCourse_id(rs.getString("course_id"));
			stuRegistry.setTerm(rs.getString("term"));
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
		return stuRegistry;
	}
	/**
	 * This is the insert method when user want to 
	 * insert any StuRegistry object to StuRegistry table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void insertStuRegistry(StuRegistry stuRegistry) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(INSERT_STUREGISTRY);
			pstmt.setString(1, stuRegistry.getStudent_id());
			pstmt.setString(2, stuRegistry.getCourse_id());
			pstmt.setString(3, stuRegistry.getTerm());	
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
