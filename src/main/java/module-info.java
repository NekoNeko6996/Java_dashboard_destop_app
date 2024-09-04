module com.application.main {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires gson;
    requires jbcrypt;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires java.base;
    requires java.sql;
    requires org.fxmisc.richtext;
    requires org.fxmisc.flowless;
    requires org.fxmisc.undo;

    
    opens com.application.controllers to javafx.fxml;
    opens com.application.main to javafx.fxml;
    opens com.application.models to javafx.base, gson;
    opens com.application.models.weather to javafx.base, gson;
    
    
    exports com.application.main;
    exports com.application.controllers;
}
