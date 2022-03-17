package tokyo.ramune.caffeinclan.database;

public class DataBaseManager {

    public static void createTable() {
        if (!SQL.tableExists("players")) {
            SQL.createTable("players", "uuid text NOT NULL, username text NOT NULL, status text NOT NULL, latest_join_date date NOT NULL, clan text, clan_role text NOT NULL");
        }
        if (!SQL.tableExists("clans")) {
            SQL.createTable("clans", "clan_name text NOT NULL, master_uuid text NOT NULL, master_name text NOT NULL, members int NOT NULL");
        }
    }
}
