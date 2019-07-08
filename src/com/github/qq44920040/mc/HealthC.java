package com.github.qq44920040.mc;

import com.github.qq44920040.mc.Entity.PlayerEntity;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class HealthC extends JavaPlugin implements Listener {
    private HashMap<String, PlayerEntity> playerinfo = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("healthcommand")){
            if (args.length==3){
                OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(args[0]);
                if (offlinePlayer.isOnline()){
                    Player player = offlinePlayer.getPlayer();
                    String[] split = args[1].split("v");
                    playerinfo.put(player.getName(),new PlayerEntity(args[2],Integer.parseInt(split[0]),Integer.parseInt(split[1])));
//                    System.out.println("成功");
                }else {
                    sender.sendMessage("§e§l玩家暂时不在线");
                }
            }
        }
        return super.onCommand(sender, command, label, args);
    }

    @EventHandler
    public void PlayerTakeHealth(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        if (entity.getType()== EntityType.PLAYER){
//            System.out.println("是玩家");
            Player player = ((Player) entity).getPlayer();
            if (playerinfo.containsKey(player.getName())){
//                System.out.println("有玩家名字");
                double health = player.getHealth();
                PlayerEntity playerEntity = playerinfo.get(player.getName());
//                System.out.println("区间最大:"+playerEntity.getMaxnum()+"-区间最小："+playerEntity.getMinnum());
//                System.out.println("当前血量;"+player.getHealth());
                if (health>=playerEntity.getMinnum()&&health<=playerEntity.getMaxnum()){
//                        System.out.println("在区间");
                        player.performCommand(playerEntity.getCmd().replace("{Player}",player.getName()));
                        playerinfo.remove(player.getName());
                        //player.sendMessage(playerEntity.getCmd().replace("{Player}",player.getName()));
                }
            }
        }
    }


    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }
}
