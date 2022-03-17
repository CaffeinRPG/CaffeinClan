package tokyo.ramune.caffeinclan.database;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private FileConfiguration config;

    public String  MYSQL_HOST;
    public int     MYSQL_PORT;
    public String  MYSQL_DATABASE;
    public String  MYSQL_USERNAME;
    public String  MYSQL_PASSWORD;

    public Config(FileConfiguration config) {
        this.config = config;
        load();
    }

    public void load() {
        MYSQL_HOST      = config.getString("config.database.host");
        MYSQL_PORT      = config.getInt("config.database.port");
        MYSQL_DATABASE  = config.getString("config.database.database");
        MYSQL_USERNAME  = config.getString("config.database.username");
        MYSQL_PASSWORD  = config.getString("config.database.password");
    }
}
