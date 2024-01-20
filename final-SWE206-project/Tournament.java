
import java.time.LocalDate;
import java.util.ArrayList;

public class Tournament {
    private String name;
    private String type;
    private String participationType;
    private String sport;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private ArrayList<Student> studentarray;
    private ArrayList<Team> teamarray;
    private int numberPerTeam=2;
    private ArrayList<match> arrayOfmatch = new ArrayList<>();

    public Tournament(String name, String type, String participationType, String sport, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.type = type;
        this.participationType = participationType;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        if(participationType.toUpperCase().equals("TEAM"))
           this.teamarray = new ArrayList<Team>();
        else{
            this.studentarray = new ArrayList<Student>();
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTeamNumber(int numberPerTeam){
        this.numberPerTeam=numberPerTeam;
    }
    public int getTeamNumber(){
        return numberPerTeam;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getParticipationType() {
        return participationType;
    }
    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }
    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public String getStartDate() {
        return startDate.toString();
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate.toString();
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getStatus(){
        return status;
    }
    public void setStudentInTournament(Student x){
        studentarray.add(x);
    }
    public ArrayList<Student> getStudentInTournament(){
        return studentarray;
    }
    public void setTeamInTournament(Team x){
        teamarray.add(x);
    }
    public ArrayList<Team> getTeamInTournament(){
        return teamarray;
    }
 public void addMatchTotournament(match match){
        arrayOfmatch.add(match);
 }
 public ArrayList<match> getMatchesOfTournament(){
    return arrayOfmatch;
 }
}

