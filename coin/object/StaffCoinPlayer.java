package com.rubixstudios.staffcoin.coin.object;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:13
 * @project StaffCoin
 */

@Getter
public class StaffCoinPlayer {

    private final UUID playerId;

    private int amountOfStaffCoins;

    public StaffCoinPlayer(UUID playerId) {
        this.playerId = playerId;
    }

    public void addCoins(int amount) {
        this.amountOfStaffCoins = this.amountOfStaffCoins + amount;
    }

    public void removeCoins(int amount) {
        this.amountOfStaffCoins = this.amountOfStaffCoins - amount;
    }
}
