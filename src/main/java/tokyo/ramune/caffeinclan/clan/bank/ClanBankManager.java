package tokyo.ramune.caffeinclan.clan.bank;

import tokyo.ramune.caffeinclan.clan.Clan;

public class ClanBankManager {

    public static int getBalance(Clan clan) {
        return clan.getBank();
    }

    public static void setBalance(Clan clan, int balance) {
        clan.setBank(balance);
    }

    public static boolean deposit(Clan clan, int balance) {
        clan.setBank(clan.getBank() + balance);
        return true;
    }

    public static boolean withdrawal(Clan clan, int balance) {
        if (clan.getBank() < balance) {
            return false;
        }
        clan.setBank(clan.getBank() - balance);
        return true;
    }
}
