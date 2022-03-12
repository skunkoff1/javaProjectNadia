package ProjetNadia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BddEpreuve {
	public static void getYear(JComboBox box) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs =null;
		Set<String> yearList = new TreeSet<>(); 
		try {
			ps = cn.prepareStatement("SELECT ANNEE FROM epreuve");			
			rs = ps.executeQuery();
			while(rs.next()) {	
				yearList.add(rs.getString(1));
				}			
			}catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		for(String s : yearList) {
			box.addItem(s);
		}
	}
	
	public static void getPlayers(String year, String sex, JTable table) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs =null; 
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for( int i = model.getRowCount() - 1; i >= 0; i-- )	{
		    model.removeRow(i);
		}	
		try {
			ps = cn.prepareStatement("SELECT ANNEE FROM epreuve");			
			rs = ps.executeQuery();
			while(rs.next()) {	
				}			
			}catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
	}
}
