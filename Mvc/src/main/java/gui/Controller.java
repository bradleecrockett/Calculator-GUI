package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller class. The controller class creates the required models and
 * connects the view with any postalCode that needs a listener. The controller class
 * receives action events from the view and processes the events.
 */
public class Controller implements EventHandler<ActionEvent> {
    private final PostalCode postalCode;
    private View view;

    /**
     * Constructor
     * <p>
     * The constructor instantiates any required postalCode class
     */
    public Controller() {
        this.postalCode = new PostalCodeImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource().getClass().isAssignableFrom(Button.class)) {
            postalCode.search(view.getInputTextField().getText());
        } else if (event.getSource().getClass().isAssignableFrom(TextField.class)) {
            postalCode.search(view.getInputTextField().getText());
        }
    }

    /**
     * Set the view
     *
     * @param view our gui view
     */
    public void setView(View view) {
        this.view = view;
        view.getOutputTextField().textProperty().bind(postalCode.getCityNameProperty());
    }
}
