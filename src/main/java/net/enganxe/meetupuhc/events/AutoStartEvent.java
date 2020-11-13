package net.enganxe.meetupuhc.events;

import net.enganxe.meetupuhc.Main;
import net.enganxe.meetupuhc.config.WorldCreator;
import net.enganxe.meetupuhc.player.KitGiver;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.Objects;
import java.util.Random;

import static net.enganxe.meetupuhc.Main.*;

public class AutoStartEvent implements Listener {
    public static int time;
    public static int wtime;
    public static boolean enablewb;
    private final Main plugin;
    public AutoStartEvent(Main plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (starting && !started){
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("Health", "health");
            objective.setDisplayName(ChatColor.RED + "❤");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            p.setScoreboard(board);
            PlayerInventory inv =  p.getInventory();
            inv.clear();
            scatter(p);
            KitGiver.setInv(p);
            return;
        }
        Main.PlayersToStart = config.getConfig().getInt("config.playerstostart");
        String world = config.getConfig().getString("worlds.meetup_world");
        World worldd = Bukkit.getWorld(config.getConfig().getString("worlds.meetup_world"));;
        if (Bukkit.getOnlinePlayers().size() == Main.PlayersToStart) {
            if (!Main.started && !Main.starting) {
                time = 61;
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        time = time - 1;
                        if (time == 60){
                            WorldCreator.setWorldBorder();
                            worldd.setGameRule(GameRule.NATURAL_REGENERATION, false);
                            worldd.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                            worldd.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                            worldd.setGameRule(GameRule.DO_INSOMNIA, false);
                            worldd.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
                            worldd.setTime(9000);
                            worldd.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            Main.starting = true;
                            Main.started = false;
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                                scatter(all);
                                KitGiver.setInv(all);
                                ScoreboardManager manager = Bukkit.getScoreboardManager();
                                Scoreboard board = manager.getNewScoreboard();
                                Objective objective = board.registerNewObjective("Health", "health");
                                objective.setDisplayName(ChatColor.RED + "❤");
                                objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
                                all.setScoreboard(board);
                            }
                        }
                        if (time == 30) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                        }
                        if (time == 15) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }

                        }
                        if (time == 10) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }
                        }
                        if (time == 5) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }
                        }
                        if (time == 4) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }
                        }
                        if (time == 3) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }
                        }
                        if (time == 2) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }
                        }
                        if (time == 1) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting in " + ChatColor.LIGHT_PURPLE + time);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
                            }
                        }
                        if (time == 0) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "The Meetup has been " + ChatColor.LIGHT_PURPLE + "started!");

                            WorldBorderSh();
                            assert world != null;
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                Main.PlayersAlive.add(all);
                                all.setInvulnerable(false);
                                all.playSound(all.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, 10, 1);
                                for(PotionEffect effect : all.getActivePotionEffects()){
                                    all.removePotionEffect(effect.getType());
                                }
                                int gplayed = config.getConfig().getInt("stats.players." + all + ".gamesplayed");
                                config.getConfig().set("stats.players." + all + ".gamesplayed", gplayed + 1);
                                config.saveConfig();

                            }
                            int mplayed = config.getConfig().getInt("stats.Played");
                            config.getConfig().set("stats.Played", mplayed + 1);
                            config.saveConfig();
                            Main.starting = false;
                            Main.started = true;
                        }
                    }
                },0L, 20L);
            }
        }
    }
    public void WorldBorderSh() {
        if (Main.started && !enablewb) {
            enablewb = true;
            wtime = 61;
        }
        if (Main.started && enablewb) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    wtime = wtime - 1;
                    if (wtime == 60) {
                        String prefix = Main.config.getConfig().getString("config.borderprefix");
                        String msg = Main.config.getConfig().getString("messages.worldborder1");
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
                    }
                    if (wtime == 0) {
                        String prefix = Main.config.getConfig().getString("config.borderprefix");
                        String msg = Main.config.getConfig().getString("messages.worldborder2");
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
                        World world = Bukkit.getWorld(config.getConfig().getString("worlds.meetup_world"));
                        WorldBorder worldBorder = world.getWorldBorder();
                        worldBorder.setSize(100, 180);
                    }
                }
            }, 0L, 20L);
        }
    }
    public void scatter(Player p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 200));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2147483647, 200));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 2147483647, 200));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2147483647, 200));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 200));
        String world = config.getConfig().getString("worlds.meetup_world");
        int worldborder = Integer.parseInt(Objects.requireNonNull(config.getConfig().getString("config.worldborder")));
        new BukkitRunnable() {
            @Override
            public void run() {
                Random rand = new Random();
                int x = rand.nextInt(worldborder/2);
                int z = rand.nextInt(worldborder/2);
                if (rand.nextInt(100) <= 49) {
                    x = -1 * x;
                }
                if (rand.nextInt(100) <= 49) {
                    z = -1 * z;
                }
                int y = 1;
                assert world != null;
                if (Objects.requireNonNull(Bukkit.getWorld(world)).getHighestBlockAt(x, z).getY() < 120) {
                    y = Objects.requireNonNull(p.getServer().getWorld(world)).getHighestBlockYAt(x, z) + 2;

                    if (Objects.requireNonNull(Bukkit.getWorld(world)).getBlockAt(x, y, z).getType() == Material.WATER) {
                        rand = new Random();
                        x = rand.nextInt(worldborder/2);
                        z = rand.nextInt(worldborder/2);
                        if (rand.nextInt(100) <= 49) {
                            x = -1 * x;
                        }
                        if (rand.nextInt(100) <= 49) {
                            z = -1 * z;
                        }
                        y = Objects.requireNonNull(p.getServer().getWorld(world)).getHighestBlockYAt(x, z) + 2;
                    }
                } else {
                    rand = new Random();
                    x = rand.nextInt(worldborder/2);
                    z = rand.nextInt(worldborder/2);
                    if (rand.nextInt(100) <= 49) {
                        x = -1 * x;
                    }
                    if (rand.nextInt(100) <= 49) {
                        z = -1 * z;
                    }
                }
                p.setStatistic(Statistic.PLAYER_KILLS, 0);
                p.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                p.setGameMode(GameMode.SURVIVAL);

            }
        }.runTaskLater(this.plugin, 5);
    }
}