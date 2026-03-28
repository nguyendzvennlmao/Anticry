package me.aris.anticpvp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCPvP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (e.getItem() == null) return;

        if (e.getItem().getType() == Material.END_CRYSTAL) {
            Player p = e.getPlayer();
            if (!p.hasPermission("anticpvp.bypass")) {
                e.setCancelled(true);
                p.sendMessage("§cServer đã chặn Crystal PvP!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof EnderCrystal) {
            if (e.getDamager() instanceof Player p) {
                if (!p.hasPermission("anticpvp.bypass")) {
                    e.setCancelled(true);
                    p.sendMessage("§cKhông thể phá End Crystal!");
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        if (e.getEntity() instanceof EnderCrystal) {
            e.setCancelled(true);
        }
    }
                }
