package net.shvit.bsutils.io;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class FileBuilder {

    private final File file;
    private final YamlConfiguration configuration;

    public FileBuilder(String filePath, String fileName) {

        this.file = new File(filePath, fileName);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);

    }

    public FileBuilder save() {

        try {

            this.configuration.save(file);

        } catch (IOException exception) {

            exception.printStackTrace();

        }

        return this;

    }

    public void set(String path, Object value) {

        this.configuration.set(path, value);
        this.save();

    }

    public Object get(String path) {
        return this.configuration.get(path);
    }

    public Boolean contains(String path) {
        return this.configuration.contains(path);
    }

    public Set<String> getKeys() {

        return this.configuration.getKeys(false);

    }

    public boolean isEmpty() {
        return  this.configuration.getKeys(false).isEmpty();
    }

}
