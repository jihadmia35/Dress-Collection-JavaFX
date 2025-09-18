package bd.edu.seu.dresscollection;

public class Dress {
    private String name;
    private String type;
    private String size;
    private String color;
    private int price;
    private String details;
    private String date;
    private int quantity;
    private String discount;
    private String gender;
    private boolean checkBox;
    private String imagePath;

    public Dress(){

    }

    public Dress(String name, String type, String size, String color, int price, String details, String date, int quantity, String discount, String gender, boolean checkBox, String imagePath) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.price = price;
        this.details = details;
        this.date = date;
        this.quantity = quantity;
        this.discount = discount;
        this.gender = gender;
        this.checkBox = checkBox;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
