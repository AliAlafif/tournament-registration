
import java.util.ArrayList;

public class Team {
    private String TeamName;
    private ArrayList<Student> members;
    private int score;
    
    public Team(String TeamName) {
        this.TeamName = TeamName;
        this.members = new ArrayList<>();
        this.score = 0;
    }
    
    public String getName() {
        return TeamName;
    }
    
    public void setName(String TeamName) {
        this.TeamName = TeamName;
    }
    
    public ArrayList<Student> getMembers() {
        return members;
    }
    
    public void setMembers(ArrayList<Student> members) {
        this.members = members;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}

