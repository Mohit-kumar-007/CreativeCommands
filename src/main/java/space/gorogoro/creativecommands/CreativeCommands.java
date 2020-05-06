package space.gorogoro.creativecommands;


import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CreativeCommands extends JavaPlugin implements Listener{
  
  @Override
  public void onEnable(){
    try{
      getServer().getPluginManager().registerEvents(this, this);
      getLogger().info("The Plugin Has Been Enabled!");
    } catch (Exception e){
      getLogger().log(Level.WARNING, e.toString());
    }
    
  }
  
  /**
   * コマンド実行時に呼び出されるメソッド
   * @see org.bukkit.plugin.java.JavaPlugin#onCommand(org.bukkit.command.CommandSender,
   *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
   */
  public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
    try{
      if((sender instanceof Player)) {
        Player sp = (Player)sender;
        if(sp.hasPermission("space.gorogoro.creativecommands")){
          if( command.getName().equals("selfgive") && args.length >= 2) { 
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give "  + sp.getName() + " " + implode(args, " "));
            sp.sendMessage("selfgiveを実行しました。");
            return true;
          }else if( command.getName().equals("headgive") && args.length == 2) { 
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give "  + sp.getName() + " skull " + args[1] + " 3 {SkullOwner:" + args[0] + "}");
            sp.sendMessage("headgiveを実行しました。");
            return true;
          } else {
            return false;
          }
        }
      }
    }catch(Exception e){
      getLogger().log(Level.WARNING, e.toString());
    }
    return true;
  }
  
  public String implode(String[] values, String glue) {
    StringBuilder sb = new StringBuilder();
    int index = 0;
    int size = values.length;
    for (String value : values) {
        sb.append(value);
        if (index <= size - 2) {
            sb.append(glue);
        }
        index++;
    }
    return sb.toString();
}
  
  @Override
  public void onDisable(){
    getLogger().info("The Plugin Has Been Disabled!");
  }
}
