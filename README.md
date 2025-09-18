# Dress Collection

![JavaFX](https://img.shields.io/badge/JavaFX-24-blue)
![Java](https://img.shields.io/badge/Java-24-orange)
![License](https://img.shields.io/badge/License-MIT-green)

---

## Overview
**Dress Collection** is a JavaFX desktop application that allows users to browse, select, and view dresses along with hidden discount codes. The application focuses on a clean, user-friendly interface, dynamic data handling, and interactive elements.  

The discount code for each dress is initially masked (`*******`) and only revealed when the user clicks the **Show** button. The code changes dynamically when a different dress is selected, ensuring that the feature is interactive and secure.

---



## Features

- **Interactive Table View:**  
  Browse dresses with details like name, category, price, and a placeholder for discount codes.  

- **Masked Discount Codes:**  
  Codes appear as `*******` initially for privacy.  

- **Reveal Discount:**  
  Click **Show** to display the discount code for the selected dress.  

- **Dynamic Reset:**  
  Selecting another dress resets the code to masked state.  

- **Responsive GUI:**  
  Built with JavaFX components (TableView, Buttons, Labels) for smooth user experience.  

- **Easy Data Management:**  
  Uses `ObservableList` for dynamic updates in the table view.  

---

## Technologies Used

- **Java 24** – Main programming language  
- **JavaFX 24** – GUI framework  
- **FXML** – GUI layout design  
- **ObservableList** – Dynamic data management  
- **MVC Architecture** – Separation of logic and UI  

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/jihadmia35/Dress-Collection-JavaFX
