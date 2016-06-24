/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.operations;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eddy
 */
public class AuthenticationStockTableView {

    private SimpleStringProperty title;
    private SimpleStringProperty author;

    public AuthenticationStockTableView() {

    }
//
//    public AuthenticationStockTableView(String s1, String s2) {
//
//        title = new SimpleStringProperty(s1);
//
//        author = new SimpleStringProperty(s2);
//
//    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String s) {
        title.set(s);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String s) {
        author.set(s);
    }

    @Override
    public String toString() {
        return (title.get() + ", by " + author.get());
    }

}
