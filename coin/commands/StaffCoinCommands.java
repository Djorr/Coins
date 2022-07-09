package com.rubixstudios.staffcoin.coin.commands;

import com.rubixstudios.staffcoin.coin.StaffCoinController;
import com.rubixstudios.staffcoin.coin.object.StaffCoinPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.print.DocFlavor;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:33
 * @project StaffCoin
 */
public class StaffCoinCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        final Player player = (Player) sender;

        final StaffCoinController staffCoinController = StaffCoinController.getInstance();

        if (args.length == 0) {
            final StaffCoinPlayer staffCoinPlayer = staffCoinController.getStaffCoinManager().getStaffCoinPlayer(player);
            player.sendMessage("Jou staffcoins balans: <amount>".replace("<amount>", "" + staffCoinPlayer.getAmountOfStaffCoins()));
            return true;
        }

        switch (args[0]) {
            case "help": {
                if (!this.hasPlayerPermission(player, "staffcoin.admin")) return true;

                player.sendMessage("Commands: /staffcoin");
                player.sendMessage(" - /staffcoin | Laat je aantal staffcoins zien");
                player.sendMessage(" - /staffcoin add | Voegt staffcoins toe");
                player.sendMessage(" - /staffcoin remove | Neemt staffcoins af");

                break;
            }
            case "add": {
                if (!this.hasPlayerPermission(player, "staffcoin.admin")) return true;

                final String targetName = args[1];
                if (!this.isPlayerOnline(player, targetName)) return true;

                final Player targetPlayer = Bukkit.getPlayer(targetName);
                final StaffCoinPlayer staffCoinPlayer = staffCoinController.getStaffCoinManager().getStaffCoinPlayer(targetPlayer);

                final int amount = Integer.parseInt(args[2]);
                staffCoinPlayer.addCoins(amount);

                player.sendMessage("Je hebt succevol <amount> gegeven aan <target>!"
                        .replace("<amount>", "" + amount)
                        .replace("<target>", "" + targetPlayer.getName()));
                break;
            }
            case "remove": {
                if (!this.hasPlayerPermission(player, "staffcoin.admin")) return true;

                final String targetName = args[1];
                if (!this.isPlayerOnline(player, targetName)) return true;

                final Player targetPlayer = Bukkit.getPlayer(targetName);
                final StaffCoinPlayer staffCoinPlayer = staffCoinController.getStaffCoinManager().getStaffCoinPlayer(targetPlayer);

                final int amount = Integer.parseInt(args[2]);
                staffCoinPlayer.removeCoins(amount);

                player.sendMessage("Je hebt succevol <amount> afgenomen van <target>!"
                        .replace("<amount>", "" + amount)
                        .replace("<target>", "" + targetPlayer.getName()));
                break;
            }
        }

        return false;
    }

    private boolean isPlayerOnline(Player sender, String targetName) {
        final Player targetPlayer = Bukkit.getPlayer(targetName);
        if (targetPlayer == null) {
            sender.sendMessage("Deze speler is niet online.");
            return false;
        }
        return true;
    }

    private boolean hasPlayerPermission(Player sender, String permission) {
        if (!sender.isOp() || !sender.hasPermission(permission)) {
            sender.sendMessage("Je hebt hier geen permissie voor.");
            return false;
        }
        return true;
    }
}
