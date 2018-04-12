package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objects.Professor;
/**
 * This class is the implementation of ProfessorDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class ProfessorDAOImpl implements ProfessorDAO{
	/**
	 * This is the search method when user want to 
	 * find a professor object from professor table 
	 * by professor id
	 * @author Jin Lu
	 *
	 */	
	@Override
	public Professor findByID(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Professor Professor = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(
					"SELECT * FROM professor WHERE professor_id = ?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			Professor = new Professor();
			Professor.setId(rs.getString("professor_id"));
			Professor.setPw(rs.getString("professor_password"));
			Professor.setFirstName(rs.getString("professor_fname"));
			Professor.setLastName(rs.getString("professor_lname"));
			Professor.setEmail(rs.getString("professor_email"));
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
		return Professor;
	}
	
	/**
	 * This is the insert method when user want to 
	 * insert any professor object to professor table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void insertProfessor(Professor Professor) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(
					"INSERT INTO Professor (professor_id, professor_password, professor_fname, professor_lname, professor_email) " +
					"VALUES(?, ?, ?, ?, ?)");
			pstmt.setString(1, Professor.getId());
			pstmt.setString(2, Professor.getPw());
			pstmt.setString(3, Professor.getFirstName());	
			pstmt.setString(4,  Professor.getLastName());
			pstmt.setString(5,  Professor.getEmail());
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
	/**
	 * This is the delete method when user want to 
	 * delete any professor object to professor table
	 * by professor id 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void deleteProfessor(Professor professor) throws SQLException {
		DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement("delete from professor where professor_id = ?");
			pstmt.setString(1, professor.getId());

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

