package webarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webarch.db.DBConnection;
import webarch.models.Project;

public class ProjectDao {
    public static List<Project> findProjects(String projectName) {
        Connection conn = DBConnection.getDbConnection();
        
        List<Project> projects = new ArrayList<>();
        
        try {
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PROJEKT WHERE project_name LIKE ?");
            stmt.setString(1, "%"+projectName+"%");
            
            ResultSet rs = stmt.executeQuery();
            

            
            while (rs.next()) {
                projects.add(new Project(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            
            return projects;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return projects;
    }
}
