package reifen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Fenster extends Application {

    private StackPane root;
    private GridPane grid1;
    private GridPane grid2;
    private GridPane grid3;
    private GridPane grid4;
    private GridPane grid5;
    private GridPane grid6;
    private Button start1;
    private Button start2;
    private Label label1;
    private Label label2;
    private TextArea area1;
    private TextArea area2;
    private TextField textField;
    private RadioButton radioSommer;
    private RadioButton radioWinter;
    private ComboBox<String> combo;
    private ToggleGroup group;

    @Override
    public void start(Stage primaryStage) {
        root = new StackPane();
        primaryStage.setScene(new Scene(root, 660, 500));
        primaryStage.setTitle("Datenbankzugriff II");

        grid1 = new GridPane();
        grid1.setVgap(5);
        grid1.setHgap(5);
        grid1.setPadding(new Insets(10));

        grid2 = new GridPane();
        grid2.setVgap(5);
        grid2.setHgap(5);
        grid2.setPadding(new Insets(10));

        grid3 = new GridPane();
        grid3.setVgap(5);
        grid3.setHgap(5);
        grid3.setPadding(new Insets(10));

        grid4 = new GridPane();
        grid4.setVgap(5);
        grid4.setHgap(5);
        grid4.setPadding(new Insets(10));

        grid5 = new GridPane();
        grid5.setVgap(5);
        grid5.setHgap(5);
        grid5.setPadding(new Insets(10));

        grid6 = new GridPane();
        grid6.setVgap(5);
        grid6.setHgap(5);
        grid6.setPadding(new Insets(10));

        start1 = new Button("Start");
        start2 = new Button("Start");
        label1 = new Label("Teil 1:");
        label2 = new Label("Teil 2:");
        area1 = new TextArea();
        area2 = new TextArea();
        textField = new TextField();
        
        group = new ToggleGroup();
        radioSommer = new RadioButton("Sommerreifen");
        radioWinter = new RadioButton("Winterreifen");
        radioSommer.setToggleGroup(getGroup());
        radioWinter.setToggleGroup(getGroup());
        radioSommer.setSelected(true);
 
        combo = new ComboBox<String>();
        combo.getItems().addAll("Brückstein", "Continent", "Kingroyal", "Michigan");
        combo.setValue("Brückstein");

        grid1.add(grid2, 0, 0);
        grid1.add(grid5, 0, 1);
        grid2.add(grid3, 0, 0);
        grid2.add(grid4, 1, 0);
        grid3.add(label1, 0, 0);
        grid3.add(getRadioSommer(), 0, 1);
        grid3.add(getRadioWinter(), 0, 2);
        grid3.add(getStart1(), 0, 4);
        grid4.add(getArea1(), 0, 0);
        grid4.add(getTextField(), 0, 1);
        grid5.add(grid6, 0, 0);
        grid5.add(getArea2(), 1, 0);
        grid6.add(label2, 0, 0);
        grid6.add(getCombo(), 0, 1);
        grid6.add(getStart2(), 0, 2);

        root.getChildren().add(grid1);
        primaryStage.show();
        Presenter.start(this);

    }

    public void setAusgabeArea1(String ausgabe) {
        this.getArea1().setText(ausgabe);
    }

    public void setAusgabeArea2(String ausgabe) {
        this.getArea2().setText(ausgabe);
    }

    public void setAusgabeTextField(String ausgabe) {
        this.getTextField().setText(ausgabe);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Button getStart1() {
        return start1;
    }

    public Button getStart2() {
        return start2;
    }

    public RadioButton getRadioSommer() {
        return radioSommer;
    }

    public RadioButton getRadioWinter() {
        return radioWinter;
    }

    public ComboBox<String> getCombo() {
        return combo;
    }

    public TextArea getArea1() {
        return area1;
    }

    public TextArea getArea2() {
        return area2;
    }

    public TextField getTextField() {
        return textField;
    }

    public ToggleGroup getGroup() {
        return group;
    }
}
