module estm.dsic.jee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;

    opens estm.dsic.jee to javafx.fxml;
    opens estm.dsic.jee.controller to javafx.fxml;

    exports estm.dsic.jee;
}
