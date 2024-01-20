
import java.sql.Date;

public class match {
    private Team team1;
    private Team team2;
    private Date matchDate;
    private int team1Score;
    private int team2Score;
    private String winner;
    private Student student1;
    private Student student2;

    public match(Student student2, Student student1) {
        this.student1 = student1;
        this.student2= student2;
    }
    public match(Team team1, Team team2){
        this.team1=team1;
        this.team2=team2;
    }
    public Team getTeam1(){
        return team1;
    }
    public void setTeam1(Team team1){
        this.team1=team1;
    }
    public Team getTeam2(){
        return team2;
    }
    public void setTeam2(Team team2){
        this.team2=team2;
    }
    public Student getStudent1(){
        return student1;
    }
    public void setStudent1(Student student1){
        this.student1=student1;
    }
    public Student getStudent2(){
        return student2;
    }
    public void setStudent2(Student student2){
        this.student2=student2;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public void setWinner(String winner){
        this.winner=winner;
    }
    public String getWinner(){
        return winner;
    }
    public String getMatchOfStudent(){
        return student1.getName()+" VS "+student2.getName();
    }
    public String getMatchOfTeam(){
        return team1.getName()+" VS "+team2.getName();
    }
}

