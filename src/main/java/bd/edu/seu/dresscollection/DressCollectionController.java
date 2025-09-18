package bd.edu.seu.dresscollection;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.management.DescriptorRead;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DressCollectionController implements Initializable, Filters {

    int totalQuantity;

    @FXML
    public Button backEvent;

    @FXML
    public Label boostinglav;

    @FXML
    public TableColumn<Dress, Boolean> bostedCol;

    @FXML
    public TableColumn<Dress, String> colorCol;

    @FXML
    public Label colorLev;

    @FXML
    public TableColumn<Dress, String> dateCol;

    @FXML
    public Label dateLav;

    @FXML
    public Label detailsLav;

    @FXML
    public Label disCodeLav;

    @FXML
    public Label genderLav;

    @FXML
    public TableColumn<Dress, String> nameCol;

    @FXML
    public Label nameLav;

    @FXML
    public TableColumn<Dress, Number> priceCol;

    @FXML
    public Label priceLav;

    @FXML
    public TableColumn<Dress, Number> quantityCol;

    @FXML
    public Label quantityLav;

    @FXML
    public TextField serachField;

    @FXML
    public Label sizeLav;

    @FXML
    public TableView<Dress> tableView;

    @FXML
    public TableColumn<Dress, String> typeCol;

    @FXML
    public Label typeLav;

    @FXML
    private ImageView imageView;

    @FXML
    private ChoiceBox<String> sortChoiseBox;

    @FXML
    private Label totalquantity;

    ObservableList<String> sortChoice = FXCollections.observableArrayList();


    @FXML
    void backEvent(ActionEvent event) {
        HelloApplication.cng("dressForm.fxml", 500, 500);
    }
    boolean sho = false;

    @FXML
    void priceMoreThan(ActionEvent event) {
        ObservableList<Dress> filtered = FXCollections.observableArrayList(morethan(dresses));
        tableView.setItems(filtered);
    }

    private Dress selectedDress;
    ObservableList<Dress> dresses = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //choice Box
        sortChoice.add("Ascending");
        sortChoice.add("Descending");
        sortChoiseBox.setItems(sortChoice);

//        try{
//            RandomAccessFile raf = new RandomAccessFile("dresses.txt", "r");
//            String line = "";
//            while((line = raf.readLine()) != null){
//                String[] data;
//                String imgPath = "";
//                data = line.split(",");
//                if(data.length > 11){
//                    imgPath = data[11];
//                }
//                dresses.add(new Dress(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), data[5], data[6], Integer.parseInt(data[7]), data[8], data[9], Boolean.parseBoolean(data[10]), imgPath));
//            }
//            raf.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dresscollection", "root", "password");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM dress";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String size = resultSet.getString("size");
                String color = resultSet.getString("color");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                String date = resultSet.getString("date");
                int quantity = resultSet.getInt("quantity");
                String discount = resultSet.getString("discount");
                String gender = resultSet.getString("gender");
                boolean checkBox = resultSet.getBoolean("checkbox");
                String imagePath = resultSet.getString("imagepath");

                dresses.add(new Dress(name, type, size, color, (int)price, details, date, quantity, discount, gender, checkBox, imagePath));
                totalQuantity += quantity;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableView.setItems(dresses);
        totalquantity.setText(String.valueOf(totalQuantity));

        nameCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        typeCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getType()));
        colorCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getColor()));
        priceCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()));
        dateCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDate()));
        quantityCol.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
        bostedCol.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isCheckBox()));

        serachField.textProperty().addListener((observable, oldValue, newValue) -> {
            String lowerSearch = newValue.toLowerCase().trim();

            ObservableList<Dress> filtered = dresses.stream()
                    .filter(d -> d.getName().toLowerCase().contains(lowerSearch) ||
                            d.getType().toLowerCase().contains(lowerSearch) ||
                            d.getColor().toLowerCase().contains(lowerSearch))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            tableView.setItems(filtered);
        });

        sortChoiseBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Ascending")) {
                    tableView.setItems(sortInAes(dresses));
                } else if (newValue.equals("Descending")) {
                    tableView.setItems(sortInDes(dresses));
                }
            }
        });



        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedDress = newValue;
                System.out.println("Selected Dress:");
                System.out.println("Name: " + newValue.getName());
                System.out.println("Type: " + newValue.getType());
                System.out.println("Color: " + newValue.getColor());
                System.out.println("Gender: " + newValue.getGender());
                System.out.println("Price: " + newValue.getPrice());
                System.out.println("Size: " + newValue.getSize());
                System.out.println("Discount Code: " + newValue.getDiscount());
                System.out.println("Quantity: " + newValue.getQuantity());
                System.out.println("Date: " + newValue.getDate());
                System.out.println("Boosted: " + (newValue.isCheckBox() ? "Yes" : "No"));
                System.out.println("Image path: " + newValue.getImagePath());

                //display on fx
                nameLav.setText("Dress Name: " + newValue.getName());
                typeLav.setText("Dress Type: " + newValue.getType());
                sizeLav.setText("Dress Type: " + newValue.getSize());
                colorLev.setText("Dress Type: " + newValue.getColor());
                priceLav.setText("Price: " + String.valueOf(newValue.getPrice()));
                detailsLav.setText("Price: " + newValue.getDetails());
                dateLav.setText("Last purchase Date: " + newValue.getDate());
                genderLav.setText("Last purchase Date: " + newValue.getGender());
                boostinglav.setText("Facebook Boosting: " + String.valueOf(newValue.isCheckBox() ? "Yes" : "No"));

                if (newValue.getImagePath() != null && !newValue.getImagePath().isEmpty()) {
                    File imgFile = new File(newValue.getImagePath());
                    if (imgFile.exists()) {
                        imageView.setImage(new Image(imgFile.toURI().toString()));
                    } else {
                        imageView.setImage(null);
                    }
                } else {
                    imageView.setImage(null);
                }


                if (newValue.getQuantity() < 10) {
                    quantityLav.setTextFill(Color.RED);
                }
                else{
                    quantityLav.setTextFill(Color.BLACK);
                }
                quantityLav.setText("Available Quantity: " + String.valueOf(newValue.getQuantity()));

                disCodeLav.setText("Discount Code: *******");
            }
        });
    }

    @FXML
    void showEvent(ActionEvent event) {
        if (selectedDress != null) {
            disCodeLav.setText("Discount Code: " + selectedDress.getDiscount());
        }
    }

    @Override
    public ObservableList<Dress> sortInAes(ObservableList<Dress> dresses) {
        ObservableList<Dress> temp = FXCollections.observableArrayList(dresses);
        temp.sort(Comparator.comparing(Dress::getPrice));
        return  temp;
    }

    @Override
    public ObservableList<Dress> sortInDes(ObservableList<Dress> dresses) {
        ObservableList<Dress> temp = FXCollections.observableArrayList(dresses);
        temp.sort(Comparator.comparing(Dress::getPrice).reversed());
        temp.reversed();
        return temp;
    }

    @Override
    public ObservableList<Dress> morethan(ObservableList<Dress> dresses) {
        return dresses.stream()
                .filter(p -> p.getPrice() >= 1000)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }


}
