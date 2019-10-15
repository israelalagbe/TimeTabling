package com.israelalagbe.timetable;

import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ActionButtonTableCell<S> extends TableCell<S, Button> {

    private final Button actionButton;

    public ActionButtonTableCell(String label,final  Function< S, S> function) {
        this.getStyleClass().add("action-button-table-cell");
       
        this.actionButton = new Button(label);
        this.actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                         function.apply(getCurrentItem());
                }
}  );
        this.actionButton.setMaxWidth(Double.MAX_VALUE);
    }
    public void setButtonStyle(String style){
        this.actionButton.setStyle(style);
    }
    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(final String label, final Function< S, S> function) {
        return new Callback<TableColumn<S, Button>, TableCell<S, Button>>() {
            @Override
            public TableCell<S, Button> call(TableColumn<S, Button> param) {
                        return new ActionButtonTableCell<>(label, function);
            }
        };
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {                
            setGraphic(actionButton);
        }
    }
}