package tokyo.ramune.caffeinclan.clan;

public enum ClanRole {

    MEMBER,
    SUB_LEADER,
    ELDER,
    MASTER;

    public static String getName(ClanRole role) {
        switch (role) {
            case MASTER:
                return "マスター";
            case SUB_LEADER:
                return "サブリーダー";
            case ELDER:
                return "長老";
            case MEMBER:
                return "メンバー";
        }
        return null;
    }
}
