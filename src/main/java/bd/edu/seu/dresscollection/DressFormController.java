package bd.edu.seu.dresscollection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;
import java.time.LocalDate;
import java.util.EventListener;
import java.util.Random;
import java.util.ResourceBundle;



public class DressFormController implements Initializable {
    public ObservableList<String> dType = FXCollections.observableArrayList();
    public ObservableList<String> siZ = FXCollections.observableArrayList();

    public static String checkbox;
    public static String date;
    public static String discount;
    public static String dressColor;
    public static String details;
    public static String name;
    public static String type;
    public static String gender;
    public static int price;
    public static String size;
    public static boolean checkBox;
    public static int quantity;
    public static String imagePath;


    @FXML
    private CheckBox checkerBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private PasswordField discountCodeField;

    @FXML
    private ColorPicker dressColorPicker;

    @FXML
    private TextArea dressDetailsField;

    @FXML
    private TextField dressNameField;

    @FXML
    private ChoiceBox<String> dressTypeChoiceBox; //this

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private Slider priceSlider;

    @FXML
    private TextField quantitySpinner;

    @FXML
    private ChoiceBox<String> sizeChoiseBox;

    @FXML
    private Label colorError;

    @FXML
    private Label dateError;

    @FXML
    private Label detailsError;

    @FXML
    private Label genderError;

    @FXML
    private Label nameError;

    @FXML
    private Label priceError;

    @FXML
    private Label qunatityError;

    @FXML
    private Label sizeError;

    @FXML
    private Label typeError;

    @FXML
    private Label discountError;

    @FXML
    public ImageView imageView;


    @FXML
    void saveEvent(ActionEvent event) {

        if(dressNameField.getText() == null || dressNameField.getText().isEmpty()){
            nameError.setText("*Name is required");
            return;
        }
        else{
            nameError.setText("");
        }

        if(dressTypeChoiceBox.getValue() == null || dressTypeChoiceBox.getValue().isEmpty()){
            typeError.setText("*Type is required");
            return;
        }
        else{
            typeError.setText("");
        }

        if(sizeChoiseBox.getValue() == null || sizeChoiseBox.getValue().isEmpty()){
            sizeError.setText("*Size is required");
            return;
        }
        else{
            sizeError.setText("");
        }

        if(dressColorPicker.getValue() == null){
            colorError.setText("Color is required");
            return;
        }
        else{
            colorError.setText("");
        }

        if(priceSlider.getValue() <= 0){
            priceError.setText("Price is required");
            return;
        }
        else{
            priceError.setText("");
        }

        if(dressDetailsField.getText() == null || dressDetailsField.getText().isEmpty()){
            detailsError.setText("Details is required");
            return;
        }
        else{
            detailsError.setText("");
        }

        if(datePicker.getValue() == null){
            dateError.setText("Date is required");
            return;
        }
        else{
            dateError.setText("");
        }

        if(quantitySpinner.getText() == null || quantitySpinner.getText().isEmpty()){
            qunatityError.setText("Quantity is required");
            return;
        }

        else{
            qunatityError.setText("");
        }

        if(discountCodeField.getText() == null || discountCodeField.getText().isEmpty()){
            discountError.setText("Discount code is required");
            return;
        }
        else{
            discountError.setText("");
        }

        if(genderGroup.getSelectedToggle() == null){
            genderError.setText("Gender is required");
            return;
        }
        else{
            genderError.setText("");
        }

        name = dressNameField.getText();
        type = dressTypeChoiceBox.getValue();
        size = sizeChoiseBox.getValue();
        Color color = dressColorPicker.getValue();
        dressColor = color.toString();
        price = (int)priceSlider.getValue();
        details = dressDetailsField.getText();
        LocalDate dat = datePicker.getValue();
        date = dat.toString();
        discount = discountCodeField.getText();
        RadioButton rad = (RadioButton) genderGroup.getSelectedToggle();
        gender = rad.getText();
        checkBox = checkerBox.isSelected();
        quantity = Integer.parseInt(quantitySpinner.getText());

        System.out.println("Dress Name : " + name);
        System.out.println("Dress Type : " + type);
        System.out.println("Dress Price : " + price);
        System.out.println("Dress Size : " + size);
        System.out.println("Dress Color : " + color);
        System.out.println("Dress Date : " + date);
        System.out.println("Dress Discount Code : " + discount);
        System.out.println("Dress Details : " + details);
        System.out.println("Gender : " + gender);
        System.out.println("checkBox : " + checkBox);
        System.out.println("Image: " + imagePath);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dresscollection", "root", "password");
            String sql = "INSERT INTO dress VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, size);
            preparedStatement.setString(4, color.toString());
            preparedStatement.setDouble(5, (double)price);
            preparedStatement.setString(6, details);
            preparedStatement.setString(7, date);
            preparedStatement.setInt(8, quantity);
            preparedStatement.setString(9, discount);
            preparedStatement.setString(10, gender);
            preparedStatement.setBoolean(11, checkBox);
            preparedStatement.setString(12, imagePath);

            preparedStatement.executeUpdate();
            System.out.println("Data inserted into Databse");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        String line = name + "," +  type + "," + size + "," + color + "," + price + "," + details + "," + date + "," + quantity + "," + discount + "," + gender + "," + checkBox + "," + imagePath;
//        try{
//            RandomAccessFile raf = new RandomAccessFile("dresses.txt", "rw");
//            raf.seek(raf.length());
//            raf.writeBytes(line + "\n");
//            raf.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @FXML
    void listEvent(){
        HelloApplication.cng("dressCollection.fxml", 600, 600);
    }

    @FXML
    void uploadEvetn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dType.add("Hoodie");
        dType.add("Sweater");
        dType.add("T-Shirt");

        siZ.add("S");
        siZ.add("M");
        siZ.add("L");
        siZ.add("XL");
        siZ.add("XXL");

        dressTypeChoiceBox.setItems(dType);
        sizeChoiseBox.setItems(siZ);
    }
}
