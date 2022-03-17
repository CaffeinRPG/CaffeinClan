package tokyo.ramune.caffeinclan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import tokyo.ramune.caffeinclan.CaffeinClan;

public class MySQL {
    private static Connection con;

    public MySQL() {
    }

    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String user, String password, String database, String port, boolean message) {
        if (host != null && user != null && password != null && database != null) {
            disconnect(false);

            try {
                String driver = "mysql";
                String tlsVersion = "";
                con = DriverManager.getConnection("jdbc:" + driver + "://" + host + ":" + port + "/" + database + "?autoReconnect=true&maxReconnects=10" + (tlsVersion != null && tlsVersion.length() > 0 ? "&enabledTLSProtocols=TLSv" + tlsVersion : "") + "&useSSL=" + false, user, password);
                if (message) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SQL connected.");
                }
            } catch (Exception var7) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connect Error: " + var7.getMessage());
                var7.printStackTrace();
            }

        }
    }

    public static void connect(boolean message) {
        String host = CaffeinClan.config.MYSQL_HOST;
        String user = CaffeinClan.config.MYSQL_USERNAME;
        String password = CaffeinClan.config.MYSQL_PASSWORD;
        String database = CaffeinClan.config.MYSQL_DATABASE;
        String port = String.valueOf(CaffeinClan.config.MYSQL_PORT);
        if (isConnected()) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connect Error: Already connected");
            }
        } else if (host.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Host is blank");
        } else if (user.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: User is blank");
        } else if (password.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Password is blank");
        } else if (database.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Database is blank");
        } else if (port.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Port is blank");
        } else {
            setConnection(host, user, password, database, port, message);
        }

    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(boolean message) {
        try {
            if (isConnected()) {
                con.close();
                if (message) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SQL disconnected.");
                }
            } else if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Disconnect Error: No existing connection");
            }
        } catch (Exception var2) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Disconnect Error: " + var2.getMessage());
            }
        }

        con = null;
    }

    public static void reconnect() {
        disconnect();
        connect(true);
    }

    public static boolean isConnected() {
        if (con != null) {
            try {
                return !con.isClosed();
            } catch (Exception var1) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connection:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var1.getMessage());
            }
        }

        return false;
    }

    public static boolean update(String command) {
        if (command == null) {
            return false;
        } else {
            boolean result = false;
            connect(false);

            try {
                if (con != null) {
                    Statement st = con.createStatement();
                    st.executeUpdate(command);
                    st.close();
                    result = true;
                }
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Update:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
                }
            }

            disconnect(false);
            return result;
        }
    }

    public static ResultSet query(String command) {
        if (command == null) {
            return null;
        } else {
            connect(false);
            ResultSet rs = null;

            try {
                if (con != null) {
                    Statement st = con.createStatement();
                    rs = st.executeQuery(command);
                }
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Query:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
                }
            }

            return rs;
        }
    }
}
