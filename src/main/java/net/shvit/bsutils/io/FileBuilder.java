package net.shvit.bsutils.io;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
            this.configuration.load(file);

        } catch (IOException | InvalidConfigurationException exception) {

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

    public @NotNull Map<String, Object> getSection(String path) {
        Map<String, Object> locationMap = new TreeMap<>();
        ConfigurationSection locationSection = this.configuration.getConfigurationSection(path);

        if(locationSection != null) {
            for (String key : locationSection.getKeys(true)) {
                locationMap.put(key, locationSection.get(key));
            }
        }
        return locationMap;
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
