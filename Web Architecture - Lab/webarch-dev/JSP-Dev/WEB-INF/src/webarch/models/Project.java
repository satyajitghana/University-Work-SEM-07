package webarch.models;

public class Project {
    private Integer id;
    private String projectLeaderRegno;
    private String projectName;
    private String mentorName;
    private String department;
    private String category;
    
    
    public Project(Integer id, String projectLeaderRegno, String projectName, String mentorName, String department, String category) {
        this.id = id;
        this.projectLeaderRegno = projectLeaderRegno;
        this.projectName = projectName;
        this.mentorName = mentorName;
        this.department = department;
        this.category = category;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getProjectLeaderRegno() {
        return this.projectLeaderRegno;
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public String getMentorName() {
        return this.mentorName;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public String getCategory() {
        return this.category;
    }
}
