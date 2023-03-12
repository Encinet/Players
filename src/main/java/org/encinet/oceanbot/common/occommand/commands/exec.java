package org.encinet.oceanbot.common.occommand.commands;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.MessageCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.encinet.oceanbot.OceanBot;
import org.encinet.oceanbot.common.occommand.BasicCommand;
import org.encinet.oceanbot.file.Config;
import org.encinet.oceanbot.until.TextUntil;

import java.util.*;
import java.util.stream.Collectors;

public class exec extends BasicCommand implements MessageCommandSender {
  private StringBuilder print = new StringBuilder();

  public exec() {
    super("exec", new String[] {"c", "执行"}, "<命令>", "执行控制台命令", true);
  }

  @Override
  public String onCommand(String label, long qq) {
    String[] split = label.split(" ", 2);
    if (split.length == 1) {
      return "无法找到需要执行的命令";
    } else {
      String cmd = split[1];
      print = new StringBuilder();
      MessageCommandSender sender = this;
      new BukkitRunnable() {
        @Override
        public void run() {
          Bukkit.dispatchCommand(sender, cmd);
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {
          }
          OceanBot.core
              .getBot()
              .getGroup(Config.LogGroup)
              .sendMessage("来自" + qq + "的命令 " + cmd + "\n---\n" + TextUntil.removeColorCodes(print.toString().trim()));
        }
      }.runTask(OceanBot.plugin);
      return "指令发送完成";
    }
  }

  @Override
  public String onTab(String[] args, long qq) {
    return null;
  }

  @Override
  public void sendMessage(String message) {
    print.append(message).append("\n");
  }

  @Override
  public void sendMessage(String... messages) {
    for (String message : messages) {
      print.append(message).append("\n");
    }
  }

  @Override
  public void sendMessage(UUID sender, String message) {
      this.sendMessage(message);
  }

  @Override
  public void sendMessage(UUID sender, String... messages) {
      this.sendMessage(messages);
  }

  @Override
  public String getName() {
    return super.getHead();
  }
}
