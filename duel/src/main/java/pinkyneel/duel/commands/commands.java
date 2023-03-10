package pinkyneel.duel.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pinkyneel.duel.misc.duel;
import pinkyneel.duel.misc.misc;
import pinkyneel.duel.Main;

public class commands implements CommandExecutor {
    @SuppressWarnings("unused")
    private Main plugin;

    public commands(Main main) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player currentPlr = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("duel")) {
            if(args.length > 0) {
                String Pname = args[0];
                Player plr = Bukkit.getPlayerExact(Pname);
                if(plr != null) {
                    if(plr == currentPlr) {
                        currentPlr.sendMessage(misc.formatMessage(plugin.getConfig().getString("messages.duel_with_urself_err")));
                        return false;
                    }
                    duel.sendDuelRequest((Player) currentPlr, (Player) plr);
                }
                else {
                    currentPlr.sendMessage(misc.formatMessage(plugin.getConfig().getString("messages.invalid_plr")));
                }
            }

        }
        else if(cmd.getName().equalsIgnoreCase("accept")) {
            if(args.length > 0) {
                String Pname = args[0];
                Player plr = Bukkit.getPlayerExact(Pname);
                duel.acceptDuelRequest(currentPlr, (Player) plr);
            }
        }
        else if(cmd.getName().equalsIgnoreCase("reject")) {
            if(args.length > 0) {
                String Pname = args[0];
                Player plr = Bukkit.getPlayerExact(Pname);
                duel.rejectDuelRequest(currentPlr, (Player) plr);
            }
        }
        else if(cmd.getName().equalsIgnoreCase("duelreqs")) {
            String requests = duel.getRequests(currentPlr);
            if(requests == "") {
                currentPlr.sendMessage(misc.formatMessage(plugin.getConfig().getString("messages.req_error_noreq")));
            }
            else {
                currentPlr.sendMessage(misc.formatMessage(plugin.getConfig().getString("messages.duel_reqs").replace("<reqs>", requests)));
            }
        }
        else if(cmd.getName().equalsIgnoreCase("duelclear")) {
            duel.cancelDuel(currentPlr, true);
            currentPlr.sendMessage(misc.formatMessage(plugin.getConfig().getString("messages.duel_clearreqs")));
        }
        return false;
    }
}