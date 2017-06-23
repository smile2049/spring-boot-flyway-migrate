package com.github.tompower.spring.boot.flyway.migrate.core;

import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Writer {

    public void write(List<String> updates, File file) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
            Formatter formatter = FormatStyle.DDL.getFormatter();
            updates.stream().forEach(update -> writer.print(formatter.format(update) + ";"));
        }
    }

}
