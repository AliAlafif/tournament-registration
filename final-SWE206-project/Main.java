import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Main extends Application {
    ArrayList<Admin> adminsList = new ArrayList<>();
    List<Tournament> tournamentsList = new ArrayList<>();
    List<Student> studentsList = new ArrayList<>();
    boolean validAdmin;
    boolean validStusent;
    int teamSize = 0;
    

    @Override
    public void start(Stage stage) throws IOException {
        Image backgroundImage = new Image("xx.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));


        try (BufferedReader br = new BufferedReader(new FileReader(new File("StudentDetails.csv")))){
            String line;
            boolean firstLine = true; 
            while((line=br.readLine())!=null){
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String password = parts[1];
                String email = parts[3];
                String name = parts[4];
                Student student = new Student(name, id, email, password);
                    studentsList.add(student);
            }
           }
           catch(FileNotFoundException e) {
               System.out.println(e);
           }
           try (BufferedReader br = new BufferedReader(new FileReader(new File("AdminDetails.csv")))){
            String line;
            boolean firstLine = true; 
            while((line=br.readLine())!=null){
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                String password = parts[1];
                String name = parts[0];
                Admin admin = new Admin(name, password);
                    adminsList.add(admin);
            }
           }
           catch(FileNotFoundException e) {
               System.out.println(e);
           }
        tournamentsList.add(new Tournament("World Cup", "Elimination", "Team", "Football", LocalDate.of(2023, 5, 11), LocalDate.of(2023, 5, 16)));
        tournamentsList.add(new Tournament("World Cup2", "Round Robin", "Team", "Tennis", LocalDate.of(2023, 6, 26), LocalDate.of(2023, 7, 9)));
        tournamentsList.add(new Tournament("World Cup3", "Elimination", "Individual", "Cycling", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 23)));
        tournamentsList.add(new Tournament("World Cup", "Elimination", "Team", "Football", LocalDate.of(2022, 11, 21), LocalDate.of(2022, 12, 18)));
        tournamentsList.add(new Tournament("World Cup2", "Round Robin", "Individual", "Tennis", LocalDate.of(2023, 6, 26), LocalDate.of(2023, 7, 9)));
        tournamentsList.add(new Tournament("World Cup3", "Elimination", "Individual", "Cycling", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 23)));
        tournamentsList.get(2).addMatchTotournament(new match(getStudentById(9215, studentsList), getStudentById(2964, studentsList)));
        tournamentsList.get(2).addMatchTotournament(new match(getStudentById(2892, studentsList), getStudentById(9518, studentsList)));
        tournamentsList.get(2).addMatchTotournament(new match(getStudentById(9215, studentsList), getStudentById(2964, studentsList)));
        tournamentsList.get(2).addMatchTotournament(new match(getStudentById(2892, studentsList), getStudentById(9518, studentsList)));
        tournamentsList.get(0).addMatchTotournament(new match(new Team("A"), new Team("B")));
        tournamentsList.get(0).addMatchTotournament(new match(new Team("C"), new Team("D")));
        tournamentsList.get(0).addMatchTotournament(new match(new Team("E"), new Team("F")));
        tournamentsList.get(0).addMatchTotournament(new match(new Team("G"), new Team("H")));
        tournamentsList.get(3).addMatchTotournament(new match(new Team("I"), new Team("J")));
        tournamentsList.get(3).addMatchTotournament(new match(new Team("K"), new Team("L")));
        tournamentsList.get(5).addMatchTotournament(new match(getStudentById(6992, studentsList), getStudentById(7936, studentsList)));
        tournamentsList.get(5).addMatchTotournament(new match(getStudentById(8770, studentsList), getStudentById(3311, studentsList)));
        tournamentsList.get(5).addMatchTotournament(new match(getStudentById(5801, studentsList), getStudentById(2964, studentsList)));
        tournamentsList.get(5).addMatchTotournament(new match(getStudentById(5411, studentsList), getStudentById(3385, studentsList)));

        //tournamentsList.get(2).addMatchTotournament(new match(getStudentById(5801, studentsList), getStudentById(2964, studentsList)));
        Label titleLabel = new Label("              KFUPM tournament application");
        titleLabel.setStyle("-fx-font-size: 24pt; -fx-padding: 20; -fx-text-fill: green;");
        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        VBox signInBox = new VBox();
        signInBox.setSpacing(10);
        signInBox.setPadding(new Insets(20, 20, 20, 20));
        Label idLabel = new Label("ID or username:");
        TextField idField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signInButton = new Button("Sign In");
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            Label idLabel2 = new Label("ID or username:");
            TextField idField2 = new TextField();
            Label nameLabel = new Label("Name:");
            TextField nameField = new TextField();
            Label emailLabel = new Label("Email:");
            TextField emailField = new TextField();
            Label passwordLabel2 = new Label("Password:");
            PasswordField passwordField2 = new PasswordField();
            Button createButton = new Button("create");
            VBox signUpBox = new VBox();
            signUpBox.setSpacing(10);
            signUpBox.setPadding(new Insets(20, 20, 20, 20));
            signUpBox.getChildren().addAll(idLabel2, idField2, nameLabel, nameField, emailLabel, emailField, passwordLabel2, passwordField2,createButton);
            BorderPane root4 = new BorderPane();
            root4.setCenter(signUpBox);
            root4.setBackground(new Background(background));
            Scene scene4 = new Scene(root4, 800, 500);
            Stage stage4 = new Stage();
            stage4.setScene(scene4);
            stage4.show();
            createButton.setOnAction(event  -> {
                int id = Integer.parseInt(idField2.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String password = passwordField2.getText();
                Student newStudent = new Student(name, id, email, password);
                studentsList.add(newStudent);
                stage4.close();
            });
        });
        signInBox.getChildren().addAll(idLabel, idField, passwordLabel, passwordField, signInButton,signUpButton);
        root.setCenter(signInBox);
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
        BorderPane root2 = new BorderPane();
        Label titleLabel2 = new Label("                click on your choose");
        titleLabel2.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: green;");
        root2.setTop(titleLabel2);
        Button tournamentListBtn = new Button("Tournament List");
        tournamentListBtn.setPrefWidth(220);
        tournamentListBtn.setPrefHeight(90);
        tournamentListBtn.setOnAction(e -> {
            VBox tournamentBox = new VBox();
            tournamentBox.setSpacing(10);
            tournamentBox.setPadding(new Insets(20, 20, 20, 20));
            Label titleLabel3 = new Label("Tournament Schedule");
            titleLabel3.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: green;");
            tournamentBox.getChildren().add(titleLabel3);
        
            GridPane tournamentGrid = new GridPane();
            tournamentGrid.setHgap(10);
            tournamentGrid.setVgap(10);
        
            Label tournamentNameLabel = new Label("Name");
            tournamentNameLabel.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(tournamentNameLabel, 0, 0);
        
            Label startDateLabel = new Label("Start Date");
            startDateLabel.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(startDateLabel, 1, 0);
        
            Label endDateLabel = new Label("End Date");
            endDateLabel.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(endDateLabel, 2, 0);
            
            Label sportLabel1=new Label("Sport type");
            sportLabel1.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(sportLabel1, 3, 0);

            Label typesLabel1=new Label("type");
            typesLabel1.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(typesLabel1, 4, 0);

            Label participateTypesLabel=new Label("participate type");
            participateTypesLabel.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(participateTypesLabel, 5, 0);

        
            Label actionLabel = new Label("Action");
            actionLabel.setStyle("-fx-font-size: 15pt; -fx-padding: 20; -fx-text-fill: Yellow;");
            tournamentGrid.add(actionLabel, 6, 0);
        
            int row = 1;
            for (Tournament tournament : tournamentsList) {
                Label tournamentName = new Label(tournament.getName());
                tournamentName.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: White;");
                tournamentGrid.add(tournamentName, 0, row);
        
                Label startDate = new Label(tournament.getStartDate().toString());
                startDate.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: White;");
                tournamentGrid.add(startDate, 1, row);
        
                Label endDate = new Label(tournament.getEndDate().toString());
                endDate.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: White;");
                tournamentGrid.add(endDate, 2, row);

                Label sportLabel=new Label(tournament.getSport().toString());
                sportLabel.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: White;");
                tournamentGrid.add(sportLabel, 3, row);

                Label typeLabel=new Label(tournament.getType().toString());
                typeLabel.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: White;");
                tournamentGrid.add(typeLabel, 4, row);


                Label ParticipatetypeLabel=new Label(tournament.getParticipationType().toString());
                ParticipatetypeLabel.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: White;");
                tournamentGrid.add(ParticipatetypeLabel, 5, row);


        
                HBox actionBox = new HBox();
                actionBox.setSpacing(10);
        
                Button participateInTournament = new Button("Participate");
                participateInTournament.setOnAction(event -> {
                    if(validStusent){
                    String type=tournament.getParticipationType().toUpperCase();
                    VBox teamBox=new VBox();
                    teamBox.setSpacing(10);
                    teamBox.setPadding(new Insets(20, 20, 20, 20));
                    Stage stage7 = new Stage();
                    if(type.equals("TEAM")){
                        ArrayList<Student> teamStudent = new ArrayList<>();
                        int max=tournament.getTeamNumber();
                        Label teamNameLabel = new Label("Enter your team name:");
                        TextField teamNameField = new TextField();
                        Button addpage=new Button("create team");
                        teamBox.getChildren().addAll(teamNameLabel, teamNameField,addpage);
                        addpage.setOnAction(eventtt ->{
                            stage7.close();
                            Team team=new Team(teamNameField.getText());
                            VBox addpagee=new VBox();
                            addpagee.setSpacing(10);
                            addpagee.setPadding(new Insets(20, 20, 20, 20));
                            Stage stage8 = new Stage();
                            for(int i=1;i<=max;i++){
                                Label addTeamMemberButton= new Label("Add Member"+i+" ID or username:");
                                TextField addTeamMemberField = new TextField();
                                Button addmumberButton=new Button("ADD member");
                                addpagee.getChildren().addAll(addTeamMemberButton,addTeamMemberField,addmumberButton);
                                addmumberButton.setOnAction(eventt ->{
                                    Boolean add=true;
                                    Student student=getStudentById(Integer.parseInt(addTeamMemberField.getText()), studentsList);
                                    ArrayList<Team> teamParticList=tournament.getTeamInTournament();
                                    for(int ii=0;ii<teamParticList.size();ii++){
                                        ArrayList<Student> ss=teamParticList.get(ii).getMembers();
                                        if(ss.contains(student)){
                                            add=false;
                                        }  
                                    }
                                    if(add){
                                    teamStudent.add(student);
                                    }
                                    else{
                                        Alert alert = new Alert(AlertType.ERROR);
                                        alert.setTitle("ERROR");
                                        alert.setHeaderText(null);
                                        alert.setContentText("You member Already in the tournament.");
                                        alert.showAndWait();  
                                    }


                                    
                                });





                            }
                            Button finishButton = new Button("Finished");
                            addpagee.getChildren().add(finishButton);
                            finishButton.setOnAction(eventt ->{
                                if(teamStudent.size()==max){
                                    stage8.close();
                                    team.setMembers(teamStudent);
                                    tournament.setTeamInTournament(team);
                                    Label participated = new Label(" you have been Participated in this tournament");
                                    participated.setFont(new Font("Arial", 30));
                                    Label emaiLabel=new Label("check your email please");
                                    emaiLabel.setFont(new Font("Arial", 30));
                                    emaiLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                                    Button doneButton = new Button("Done");
                                    doneButton.setFont(new Font("Arial", 25));
                                    doneButton.setStyle("-fx-text-fill: gray;");
                                    VBox addpageee = new VBox(participated,emaiLabel, doneButton);
                                    addpageee.setAlignment(Pos.CENTER);
                                    addpageee.setSpacing(10);
                                    addpageee.setPadding(new Insets(20, 20, 20, 20));
                                    BorderPane root10 = new BorderPane();
                                    root10.setCenter(addpageee);
                                    root10.setBackground(new Background(background));
                                    Scene scene10 = new Scene(root10, 800, 500);
                                    Stage stage10 = new Stage();
                                    stage10.setScene(scene10);
                                    stage10.show();

                                    doneButton.setOnAction(eventttt ->{
                                    stage10.close();
                                    stage7.close();
                        });


                                    
                                
    
                                }
                                else{
                                    Label errorMessage = new Label("your team not complete yet");
                                    errorMessage.setStyle("-fx-text-fill: red;-fx-font-size: 16pt;");
                                    addpagee.getChildren().add(errorMessage);
                                }
                                });

                                BorderPane root8 = new BorderPane();
                                ScrollPane scrollPane2 = new ScrollPane(addpagee);
                                root8.setStyle("-fx-background-color: #444444;");
                                root8.setCenter(scrollPane2);
                                scrollPane2.setPrefSize(400, 400);
                                Scene scene8 = new Scene(root8, 800, 500);
                                stage8.setScene(scene8);
                                stage8.show();
                        });
  
                        
                        BorderPane root7 = new BorderPane();
                        root7.setCenter(teamBox);
                        root7.setBackground(new Background(background));
                        Scene scene7 = new Scene(root7, 800, 500);
                        stage7.setScene(scene7);
                        stage7.show();
        
                    
                        
                    }
                    else{
                        Student student=getStudentById(Integer.parseInt(idField.getText()), studentsList);
                        ArrayList<Student> tournamentPlayer=tournament.getStudentInTournament();
                        if(tournamentPlayer.contains(student)){
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("ERROR");
                                alert.setHeaderText(null);
                                alert.setContentText("You are Already in the tournament.");
                                alert.showAndWait();

                        }
                        else{
                            tournament.setStudentInTournament(student);
                            Label participated = new Label(" thank you for Participate in this tournament");
                            participated.setFont(new Font("Arial", 30));
                            Label emaiLabel=new Label("check your email please");
                            emaiLabel.setFont(new Font("Arial", 30));
                            emaiLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                            Button doneButton = new Button("Done");
                            doneButton.setFont(new Font("Arial", 25));
                            doneButton.setStyle("-fx-text-fill: gray;");
                            VBox addpagee = new VBox(participated,emaiLabel, doneButton);
                            addpagee.setAlignment(Pos.CENTER);
                            addpagee.setSpacing(10);
                            addpagee.setPadding(new Insets(20, 20, 20, 20));
                            teamBox.getChildren().add(addpagee);
                            teamBox.setAlignment(Pos.CENTER);
                            doneButton.setOnAction(eventtt ->{
                            stage7.close();
                            });
                            BorderPane root7 = new BorderPane();
                            root7.setCenter(teamBox);
                            root7.setBackground(new Background(background));
                            Scene scene7 = new Scene(root7, 800, 500);
                            stage7.setScene(scene7);
                            stage7.show();

                        }

                    }
                       
                }
                else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("You are not allowed to participate in tournament as Admin.");
                    alert.showAndWait();

                }

                    
                });
        
                Button editTournament = new Button("Edit");
                editTournament.setOnAction(event -> {
                    if(validAdmin){
                    Button deleteTournament = new Button("Remove");
                    VBox tournamentBox2 = new VBox();
                tournamentBox2.setSpacing(10);
                tournamentBox2.setPadding(new Insets(20, 20, 20, 20));
                Label TournamentNameLabel1 = new Label("Tournament name:");
                TextField TournamentNameField1 = new TextField(tournament.getName());
                Label TournamentTypeLabel1 = new Label("Tournament Type(Elimination or Round Robin):");
                TextField TournamentTypeField1 = new TextField(tournament.getType());
                Label participationType1= new Label("tournament participationType(team or individual):");
                TextField TournamentParticipationTypeField1=new TextField(tournament.getParticipationType());
                Label SportLabel1=new Label("Sport type:");
                TextField sportField1=new TextField(tournament.getSport());
                Label StartLabel1=new Label("Start time(YYYY-MM-DD):");
                TextField StartField1=new TextField(tournament.getStartDate());
                Label EndLabel1=new Label("End time(YYYY-MM-DD):");
                TextField EndField1=new TextField(tournament.getEndDate());
                Button editButton1 = new Button("edit Tournament");
                tournamentBox2.getChildren().addAll(TournamentNameLabel1,TournamentNameField1,TournamentTypeLabel1,TournamentTypeField1,participationType1,TournamentParticipationTypeField1,SportLabel1,sportField1,StartLabel1,StartField1,EndLabel1,EndField1,editButton1,deleteTournament);
                BorderPane root6 = new BorderPane();
                root6.setCenter(tournamentBox2);
                root6.setBackground(new Background(background));
                Scene scene6 = new Scene(root6, 800, 500);
                Stage stage6 = new Stage();
                stage6.setScene(scene6);
                stage6.show();
                deleteTournament.setOnAction(eee -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Delete Tournament");
                    alert.setContentText("Are you sure you want to delete this tournament?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        tournamentsList.remove(tournament);
                        tournamentGrid.getChildren().removeAll(tournamentName, startDate, endDate, actionBox,sportLabel,typeLabel,ParticipatetypeLabel);
                    }
                    stage6.close();
                
                });
                editButton1.setOnAction(eve -> {
                    if(LocalDate.parse(EndField1.getText()).isBefore(LocalDate.parse(StartField1.getText()))){
                        Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("date invalid(the tournament end date before its start!!!)");
                    alert.showAndWait();}
                    else{tournament.setName(TournamentNameField1.getText());
                        tournament.setType(TournamentTypeField1.getText());
                        tournament.setParticipationType(TournamentParticipationTypeField1.getText());
                        tournament.setSport(sportField1.getText());
                        tournament.setStartDate(LocalDate.parse(StartField1.getText()));
                        tournament.setEndDate(LocalDate.parse(EndField1.getText()));
                        /////////// we should update the page without go out
                        // tournamentGrid.getChildren().removeAll(tournamentName, startDate, endDate, actionBox);
                        stage6.close();}
                    
                });
            }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("You are not allowed to Edit tournament as a student.");
                    alert.showAndWait();
            }
                });
    

                Button showTournament = new Button("show");
                showTournament.setOnAction(event -> {
                        VBox TournamentShowBox = new VBox();
                        TournamentShowBox.setSpacing(10);
                        TournamentShowBox.setPadding(new Insets(20, 20, 20, 20));
                        Label titleLabel5 = new Label("Tournament Matches Graph");
                        titleLabel5.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: green;");
                        TournamentShowBox.getChildren().add(titleLabel5);
                    if(tournament.getType().toUpperCase().equals("ROUND ROBIN")){
                        
                        GridPane RoundRobinPane = new GridPane();
                        RoundRobinPane.setHgap(10);
                        RoundRobinPane.setVgap(10);
                        RoundRobinPane.setGridLinesVisible(true);
                        if(tournament.getParticipationType().toUpperCase().equals("TEAM")){
                            ArrayList<Team> arrayOfTeams=tournament.getTeamInTournament();
                            int numberOfcolumn=arrayOfTeams.size()-1;
                            int numberOfRow=((numberOfcolumn+1)/2)+1;
                            ArrayList<match> arraymMatchs=RoundRobinTournament(tournament);
                            for(int i=0;i<numberOfcolumn;i++){
                                String xx="Round-"+(i+1);
                                Label xxx=new Label(xx);
                                xxx.setStyle("-fx-font-size: 20pt; -fx-padding: 20; -fx-text-fill: Yellow;");
                                RoundRobinPane.add(xxx, i, 0);
                                for(int x=1;x<numberOfRow;x++){
                                    Label MatchLabel=new Label(arraymMatchs.remove(0).getMatchOfTeam());
                                    MatchLabel.setStyle("-fx-font-size: 20pt; -fx-padding: 20; -fx-text-fill: Black; bold;");
                                    RoundRobinPane.add(MatchLabel, i, x);
                                }
                            }
                        TournamentShowBox.getChildren().add(RoundRobinPane);

                        }
                        else{
                            ArrayList<Student> arrayOfPlayer=tournament.getStudentInTournament();
                            int numberOfcolumn=arrayOfPlayer.size()-1;
                            int numberOfRow=((numberOfcolumn+1)/2)+1;
                            ArrayList<match> arraymMatchs=RoundRobinTournament(tournament);
                            for(int i=0;i<numberOfcolumn;i++){
                                String xx="Round-"+(i+1);
                                Label xxx=new Label(xx);
                                xxx.setStyle("-fx-font-size: 20pt; -fx-padding: 20; -fx-text-fill: Yellow;");
                                RoundRobinPane.add(xxx, i, 0);
                                for(int x=1;x<numberOfRow;x++){
                                    Label MatchLabel=new Label(arraymMatchs.remove(0).getMatchOfTeam());
                                    MatchLabel.setStyle("-fx-font-size: 20pt; -fx-padding: 20; -fx-text-fill: Black; bold;");
                                    RoundRobinPane.add(MatchLabel, i, x);
                                }
                            }
                        TournamentShowBox.getChildren().add(RoundRobinPane);

                        }
                    }
                    else{
                        // complete from here the Elimination tournaments
                        int maxDepth = (int) (Math.log(tournament.getMatchesOfTournament().size()) / Math.log(2)) + 1;

                        ImageView cupWinner =  new ImageView("winnerCup.jpg");
                        cupWinner.setFitWidth(20);
                        cupWinner.setFitHeight(20);

                        TreeItem<String> rootNode = new TreeItem<>("Tournament Winner", cupWinner);
                        rootNode.setExpanded(true);

                        buildBinaryTree(rootNode, 1, maxDepth, tournament);

                        TreeView<String> treeView = new TreeView<>(rootNode);


                        ImageView imageView = new ImageView(new Image("eliminationTournament.jpg"));
                        imageView.setFitWidth(400); // Set the desired width of the image
                        imageView.setFitHeight(400); // Set the desired height of the image


                        SplitPane splitPane = new SplitPane();
                        splitPane.getItems().addAll(treeView, imageView);


                        TournamentShowBox.getChildren().add(splitPane);
                        /*Label titleLabel16 = new Label("Elimination Tournament");
                        titleLabel16.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: blue; -fx-alignment: center;");
                        root12.setTop(titleLabel16);
                        root12.setAlignment(titleLabel16, Pos.CENTER);
                        root12.setStyle("-fx-background-color: #FFFFFF;");*/
                    }

                    BorderPane root12 = new BorderPane();

                    if (tournament.getType().toUpperCase().equals("ROUND ROBIN")) {
                        /*Label titleLabel16 = new Label("Round Robin Tournament");
                        titleLabel16.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: green;");
                        TournamentShowBox.getChildren().add(titleLabel16);*/
                        root12.setStyle("-fx-background-color: #444444;");
                    }
                    else {
                        /*Label titleLabel16 = new Label("Elimination Tournament");
                        titleLabel16.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: blue; -fx-alignment: center;");
                        root12.setTop(titleLabel16);
                        root12.setAlignment(titleLabel16, Pos.CENTER);*/
                        root12.setStyle("-fx-background-color: #FFFFFF;");
                    }
                    root12.setCenter(TournamentShowBox);
                    ScrollPane scrollPane = new ScrollPane(root12);
                    scrollPane.setPrefSize(400, 400);
                    Scene scene12 = new Scene(scrollPane, 700, 550);
                    Stage stage12 = new Stage();
                    stage12.setScene(scene12);
                    stage12.show();
                });
                Button closeTournament = new Button("close");
                closeTournament.setOnAction(event -> {
                    if(validAdmin){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("the tournament did not finished yet");
                        alert.showAndWait();    
                    }
                    else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("You are not allow to close the tournament as a student.");
                        alert.showAndWait();
                    }
                });

                
        
                actionBox.getChildren().addAll(participateInTournament, editTournament,showTournament,closeTournament);
                tournamentGrid.add(actionBox, 6, row);
        
                row++;
            }
        
            tournamentBox.getChildren().add(tournamentGrid);
        
            BorderPane root3 = new BorderPane();
            root3.setStyle("-fx-background-color: #444444;");
            root3.setCenter(tournamentBox);
            ScrollPane scrollPane = new ScrollPane(root3);
            scrollPane.setPrefSize(400, 400);
            Scene scene3 = new Scene(scrollPane, 800, 500);
            Stage stage3 = new Stage();
            stage3.setScene(scene3);
            stage3.show();
        });
        Button addTournamentBtn = new Button("Add Tournament");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        addTournamentBtn.setOnAction(event  -> {
            if(validAdmin){
            Label TournamentNameLabel = new Label("Tournament name:");
            TextField TournamentNameField = new TextField();
            Label TournamentTypeLabel = new Label("Tournament Type(Elimination or Round Robin):");
            TextField TournamentTypeField = new TextField();
            Label participationType= new Label("tournament participationType(team or individual):");
            TextField TournamentParticipationTypeField=new TextField();
            Label SportLabel=new Label("Sport type:");
            TextField sportField=new TextField();
            Label StartLabel=new Label("Start time(YYYY-MM-DD):");
            TextField StartField=new TextField();
            Label EndLabel=new Label("End time(YYYY-MM-DD):");
            TextField EndField=new TextField();
            Button createButton2 = new Button("create Tournament");
            VBox createTournamentBox = new VBox();
            createTournamentBox.setSpacing(10);
            createTournamentBox.setPadding(new Insets(20, 20, 20, 20));
            createTournamentBox.getChildren().addAll(TournamentNameLabel,TournamentNameField,TournamentTypeLabel,TournamentTypeField,participationType,TournamentParticipationTypeField,SportLabel,sportField,StartLabel,StartField,EndLabel,EndField,createButton2);
            BorderPane root5 = new BorderPane();
            root5.setCenter(createTournamentBox);
            root5.setBackground(new Background(background));
            Scene scene5 = new Scene(root5, 800, 500);
            Stage stage5 = new Stage();
            stage5.setScene(scene5);
            stage5.show();
            createButton2.setOnAction(e2->{
                if(LocalDate.parse(EndField.getText()).isBefore(LocalDate.parse(StartField.getText()))){
                    Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("date invalid(the tournament end date before its start!!!)");
                alert.showAndWait();}
                else{
                    String Tournamentname = TournamentNameField.getText();
                String TournamentType = TournamentTypeField.getText();
                String Tournamentpar  = TournamentParticipationTypeField.getText();
                String TournamentSport= sportField.getText();
                LocalDate startTime = LocalDate.parse(StartField.getText(), formatter);
                LocalDate EndTime = LocalDate.parse(EndField.getText(), formatter);
                Tournament tournamentt=new Tournament(Tournamentname, TournamentType, Tournamentpar, TournamentSport, startTime, EndTime);
                tournamentsList.add(tournamentt);
                stage5.close();
                }
                
            });
        }
        else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("You are not allowed to create a tournament.");
        alert.showAndWait();
        }
    });
        addTournamentBtn.setPrefWidth(220);
        addTournamentBtn.setPrefHeight(90);
        Button matchesViewBtn = new Button("Matches View");
        matchesViewBtn.setPrefWidth(220);
        matchesViewBtn.setPrefHeight(90);
        matchesViewBtn.setOnAction(event ->{
            VBox viewVBox = new VBox();
            viewVBox.setSpacing(1);
            viewVBox.setPadding(new Insets(1, 1, 1, 1));
            viewVBox.setLayoutX(250);

            Label titleLabel5 = new Label("matches view");
            Label subTitle= new Label("matches of the day");
            titleLabel5.setStyle("-fx-font-size: 30pt; -fx-padding: 20; -fx-text-fill: green;");   
            subTitle.setStyle("-fx-font-size: 24pt; -fx-padding: 20; -fx-text-fill: green;");  
            Button next = new Button("Next");
            Button previous = new Button("Previous");
            HBox viewHBox = new HBox();
            viewHBox.setSpacing(600);
            //viewHBox.setAlignment(Pos.BOTTOM_CENTER);
            viewHBox.setLayoutX(50);
            viewHBox.setLayoutY(450);
            //viewHBox.getChildren().addAll(previous,next);
            viewVBox.getChildren().addAll(titleLabel5,subTitle);
            Pane viewMatchesPane = new Pane();
            viewMatchesPane.setBackground(new Background(background));

            VBox VscrollBox = new VBox();
            VscrollBox.setLayoutX(300);
            VscrollBox.setLayoutY(200);
            
            
            viewMatchesPane.getChildren().addAll(viewHBox,viewVBox,VscrollBox);
            Scene scene12 = new Scene(viewMatchesPane, 800, 600);
            Stage stage12 = new Stage();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
                for(int j=0;j<4;j++){
                                Student student1 = tournamentsList.get(2).getMatchesOfTournament().get(j).getStudent1();
                                Student student2 = tournamentsList.get(2).getMatchesOfTournament().get(j).getStudent2();
                                Button matchesText = new Button(student1.getName() + " VS " + student2.getName() );                                
                                VscrollBox.getChildren().add(matchesText);  
                                matchesText.setStyle("-fx-font-size: 12pt; -fx-padding: 20; -fx-text-fill: Black;"); 
            }
           
            stage12.setScene(scene12);
            stage12.show();

        });
        Button Profile = new Button("Show profile");
        Profile.setPrefWidth(220);
        Profile.setPrefHeight(80);
    Profile.setOnAction(e ->{
        Label titleLabel30 = new Label("                   Search for player profile");
        titleLabel30.setStyle("-fx-font-size: 24pt; -fx-padding: 20; -fx-text-fill: green;");
        BorderPane root30 = new BorderPane();
        root30.setBackground(new Background(background));
        root30.setTop(titleLabel30);
        VBox signInBox30 = new VBox();
        signInBox30.setSpacing(10);
        signInBox30.setPadding(new Insets(20, 20, 20, 20));
        Label idLabel30 = new Label("ID");
        TextField idField30 = new TextField();
        Button SearchButton = new Button("Search");
        signInBox30.getChildren().addAll(idLabel30, idField30,SearchButton);
        root30.setCenter(signInBox30);
        Scene scene30 = new Scene(root30, 800, 500);
        Stage stage30=new Stage();
        stage30.setScene(scene30);
        stage30.show();
        SearchButton.setOnAction(ee->{
        VBox signInBox31 = new VBox();
        BorderPane root31 = new BorderPane();
        root31.setBackground(new Background(background));
        signInBox30.setSpacing(10);
        signInBox30.setPadding(new Insets(20, 20, 20, 20));
        Student student=getStudentById(Integer.parseInt(idField30.getText()), studentsList);
        if(student==null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Student not exist");
            alert.showAndWait();
        }
        else{
            Image image = new Image("user2.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Label name=new Label("Name: "+student.getName());
            name.setStyle("-fx-font-size: 16pt; -fx-padding: 20; -fx-text-fill: Black; -fx-font-weight: bold;");
            Label ID=new Label("ID: "+String.valueOf(student.getId()));
            ID.setStyle("-fx-font-size: 16pt; -fx-padding: 20; -fx-text-fill: Black; -fx-font-weight: bold;");
            Label EMAIL=new Label("Email: "+student.getEmail());
            EMAIL.setStyle("-fx-font-size: 16pt; -fx-padding: 20; -fx-text-fill: Black; -fx-font-weight: bold;");
            signInBox31.setAlignment(Pos.CENTER);
            signInBox31.getChildren().addAll(imageView,name,ID,EMAIL);
            root31.setCenter(signInBox31);
            Scene scene31 = new Scene(root31, 800, 500);
            stage30.setScene(scene31);
            stage30.show();         

        }




        });
        });
        VBox optionsBox = new VBox(10, tournamentListBtn, addTournamentBtn, matchesViewBtn,Profile);
        optionsBox.setAlignment(Pos.CENTER);
        root2.setCenter(optionsBox); 
        Scene scene2 = new Scene(root2, 800, 500);
                signInButton.setOnAction(e -> {
            String id2=idField.getText();
            
            String password = passwordField.getText();
            validAdmin = false;
            validStusent=false;
            if(id2.matches("[a-zA-Z]+")){
            for (Admin admin : adminsList) {
                if (admin.getUsername().equals(id2)&& admin.getPassword().equals(password)) {
                    validAdmin = true;
                    break;
                }
            }
        }
        else{
            for(Student student : studentsList){
                int id = Integer.parseInt(idField.getText());
                if(student.getId()==id && student.getPassword().equals(password)){
                    validStusent=true;
                    break;
                }
            }
        }
            if (validAdmin || validStusent) {
                stage.setScene(scene2);
            } else {
                Label errorMessage = new Label("Invalid ID or password!");
                errorMessage.setStyle("-fx-text-fill: red;-fx-font-size: 16pt;");
                signInBox.getChildren().add(errorMessage);
            }
        });
        
        root.setBackground(new Background(background));
        root2.setBackground(new Background(background));


    }
    private Student getStudentById(int id, List<Student> studentsList) {
        for (Student student : studentsList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public  ArrayList<match> RoundRobinTournament(Tournament tournament){
        ArrayList<match> matches = new ArrayList<>();
        if(tournament.getParticipationType().toUpperCase().equals("TEAM")){
           ArrayList<Team> arrayOfTeams=tournament.getTeamInTournament();

           int numTeams = arrayOfTeams.size();

           for (int round = 1; round < numTeams; round++) {
                for (int i = 0; i < numTeams / 2; i++) {
                int team1 = i;
                int team2 = numTeams - 1 - i;
                match mm=new match(arrayOfTeams.get(team1),arrayOfTeams.get(team2) );
                matches.add(mm);
            }
            Team lastTeam = arrayOfTeams.remove(numTeams - 1);
            arrayOfTeams.add(1, lastTeam);
            }

            return matches;
        }
        else{
            ArrayList<Student> arrayOfPlayer=tournament.getStudentInTournament();
            int numPlayer = arrayOfPlayer.size();
            for (int round = 1; round < numPlayer; round++) {
                for (int i = 0; i < numPlayer / 2; i++) {
                int Player1 = i;
                int Player2 = numPlayer - 1 - i;
                match mm=new match(arrayOfPlayer.get(Player1),arrayOfPlayer.get(Player2) );
                matches.add(mm);
            }
            Student lastTeam = arrayOfPlayer.remove(numPlayer - 1);
            arrayOfPlayer.add(1, lastTeam);
            }
        return matches;
        }

    }
    int i = 0;
    private void buildBinaryTree(TreeItem<String> root, int depth, int maxDepth, Tournament tournament) {
        if (depth == 1) {
            i = 0;
        }
        if (depth <= maxDepth) {
            if (depth != maxDepth){
                TreeItem<String> leftNode = new TreeItem<>("Round " + (maxDepth - depth) + " Winner");
                leftNode.setExpanded(true);
                root.getChildren().add(leftNode);
                buildBinaryTree(leftNode, depth + 1, maxDepth, tournament);

                TreeItem<String> rightNode = new TreeItem<>("Round " + (maxDepth - depth) + " Winner");
                rightNode.setExpanded(true);
                root.getChildren().add(rightNode);
                buildBinaryTree(rightNode, depth + 1, maxDepth, tournament);
            }
            else {
                if (tournament.getParticipationType().equals("Team")){
                    TreeItem<String> leftNode = new TreeItem<>(tournament.getMatchesOfTournament().get(i).getTeam1().getName());
                    leftNode.setExpanded(true);
                    root.getChildren().add(leftNode);
                    buildBinaryTree(leftNode, depth + 1, maxDepth, tournament);

                    TreeItem<String> rightNode = new TreeItem<>(tournament.getMatchesOfTournament().get(i).getTeam2().getName());
                    rightNode.setExpanded(true);
                    root.getChildren().add(rightNode);
                    i++;
                    buildBinaryTree(rightNode, depth + 1, maxDepth, tournament);
                }
                else if (tournament.getParticipationType().equals("Individual")){
                    TreeItem<String> leftNode = new TreeItem<>(tournament.getMatchesOfTournament().get(i).getStudent1().getName());
                    leftNode.setExpanded(true);
                    root.getChildren().add(leftNode);
                    buildBinaryTree(leftNode, depth + 1, maxDepth, tournament);


                    TreeItem<String> rightNode = new TreeItem<>(tournament.getMatchesOfTournament().get(i).getStudent2().getName());
                    rightNode.setExpanded(true);
                    root.getChildren().add(rightNode);
                    i++;
                    buildBinaryTree(rightNode, depth + 1, maxDepth, tournament);
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
