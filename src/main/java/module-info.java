module com.application.main {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires gson;
    requires jbcrypt;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    
    opens com.application.controllers to javafx.fxml;
    opens com.application.main to javafx.fxml;
    opens com.application.models to javafx.base;
    
    
    exports com.application.main;
    exports com.application.controllers;
}
