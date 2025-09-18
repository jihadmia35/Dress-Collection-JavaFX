package bd.edu.seu.dresscollection;

import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.List;

public interface Filters {
    public ObservableList<Dress> sortInAes(ObservableList<Dress> dresses);
    public ObservableList<Dress> sortInDes(ObservableList<Dress> dresses);
    public ObservableList<Dress> morethan(ObservableList<Dress> dresses);
}
