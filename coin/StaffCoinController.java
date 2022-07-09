package com.rubixstudios.staffcoin.coin;

import com.rubixstudios.staffcoin.StaffCoin;
import com.rubixstudios.staffcoin.coin.object.StaffCoinPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:09
 * @project StaffCoin
 */

@Getter
public class StaffCoinController implements Listener {

    @Getter private static StaffCoinController instance;

    private final StaffCoinManager staffCoinManager;

    public StaffCoinController() {
        instance = this;

        this.staffCoinManager = new StaffCoinManager();

        Bukkit.getPluginManager().registerEvents(this, StaffCoin.getInstance());
    }

    public void disable() {
        this.staffCoinManager.disable();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (this.staffCoinManager.getStaffCoinsCache().containsKey(player.getUniqueId())) return;

        final StaffCoinPlayer staffCoinPlayer = new StaffCoinPlayer(event.getPlayer().getUniqueId());
        this.staffCoinManager.getStaffCoinsCache().put(player.getUniqueId(), staffCoinPlayer);
    }
}
