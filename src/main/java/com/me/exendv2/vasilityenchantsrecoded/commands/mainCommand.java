package com.me.exendv2.vasilityenchantsrecoded.commands;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import com.me.exendv2.vasilityenchantsrecoded.utils.ConfigManager;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class mainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        VasilityEnchants instance = VasilityEnchants.getPlugin(VasilityEnchants.class);
        GUIManager guiManager = new GUIManager();

        if (args.length == 0){
            if (sender instanceof Player){
                Player p = (Player) sender;
                guiManager.openGUI(p);
            }

            else {
                sender.sendMessage("Usage: /" + label + " <player> - " + "Open up an enchant inventory to a player");
            }
            return true;
        }

        else if (args[0].equalsIgnoreCase("reload")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (!p.hasPermission("vasilityenchants.reload")){
                    p.sendMessage("§cYou don't have permission to use this command!");
                    return true;
                }
            }
            sender.sendMessage(ConfigManager.ColorChanger(instance.getConfig().getString("PREFIX") + "§aConfiguration file has been reloaded!"));
            instance.configReload();
            return true;
        }

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("vasilityenchants.others")) {
                p.sendMessage("§cYou don't have permission to open an enchant inventory of other players!");
                return true;
            }

        String target = args[0];
        Player player = Bukkit.getPlayer(target);
        if (player == null){
            sender.sendMessage("§cCan't find an online player named §b" + target);
            return true;

        }

        guiManager.openGUI(player);
        sender.sendMessage("§aOpened the enchant gui for player §b" + player.getDisplayName());

    }

        return true;
    }
}
