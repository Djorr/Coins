package com.rubixstudios.staffcoin.coin;

import com.rubixstudios.staffcoin.StaffCoin;
import com.rubixstudios.staffcoin.coin.object.StaffCoinPlayer;
import com.rubixstudios.staffcoin.data.Config;
import com.rubixstudios.staffcoin.util.FileUtil;
import com.rubixstudios.staffcoin.util.GsonUtil;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:09
 * @project StaffCoin
 */

@Getter
public class StaffCoinManager {

    private Map<UUID, StaffCoinPlayer> staffCoinsCache;

    private final File staffCoinsFile;

    public StaffCoinManager() {
        this.staffCoinsFile = FileUtil.getOrCreateFile(Config.STAFF_DIR, "staffcoins.json");

        this.loadStaffCoinData();
    }

    public void disable() {
        this.saveStaffCoinData();

        this.staffCoinsCache.clear();
    }

    public void loadStaffCoinData() {
        String content = FileUtil.readWholeFile(this.staffCoinsFile);

        if (content == null) {
            this.staffCoinsCache = new HashMap<>();
            return;
        }

        this.staffCoinsCache = StaffCoin.getInstance().getGson().fromJson(content, GsonUtil.STAFFCOIN_TYPE);

        StaffCoin.getInstance().log("- &7Loaded &b" + this.staffCoinsCache.size() + " &7staffcoin data.");
    }

    public void saveStaffCoinData() {
        if(this.staffCoinsCache == null) return;

        FileUtil.writeString(this.staffCoinsFile, StaffCoin.getInstance().getGson()
                .toJson(this.staffCoinsCache, GsonUtil.STAFFCOIN_TYPE));

        StaffCoin.getInstance().log("- &7Saved &b" + this.staffCoinsCache.size() + " &7staffcoin data.");
    }

    public StaffCoinPlayer getStaffCoinPlayer(Player player) {
        return this.getStaffCoinPlayer(player.getUniqueId());
    }

    public StaffCoinPlayer getStaffCoinPlayer(UUID playerId) {
        StaffCoinPlayer staffCoinPlayer = this.getStaffCoinsCache().get(playerId);
        if (staffCoinPlayer != null) {
            return staffCoinPlayer;
        } else {
            staffCoinPlayer = new StaffCoinPlayer(playerId);
            this.staffCoinsCache.put(playerId, staffCoinPlayer);

            return staffCoinPlayer;
        }
    }
}
