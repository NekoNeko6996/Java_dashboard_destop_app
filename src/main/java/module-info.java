module com.application.main {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive com.google.common;

    requires java.annotation;

    requires gson;
    requires jbcrypt;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires java.base;
    requires java.sql;
    requires transitive org.fxmisc.richtext;
    requires transitive org.fxmisc.flowless;
    requires transitive org.fxmisc.undo;
    requires org.jsoup;
    requires spring.core;
    requires javafx.base;
    requires javafx.graphics;

    opens com.application.controllers to javafx.fxml;
    opens com.application.main to javafx.fxml, gson;
    opens com.application.models to javafx.base, gson;
    opens com.application.models.weather to javafx.base, gson;
    opens com.application.models.textFormat to gson;
    opens com.application.events to com.google.common;

    exports com.application.main;
    exports com.application.controllers;
    exports com.application.models;
    exports com.application.models.weather;
    exports com.application.models.textFormat;
    exports com.application.events;
}
