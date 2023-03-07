module com.example.tictactoefull {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoefull to javafx.fxml;
    exports com.example.tictactoefull;
}