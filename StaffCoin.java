package com.rubixstudios.staffcoin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rubixstudios.staffcoin.coin.StaffCoinController;
import com.rubixstudios.staffcoin.coin.commands.StaffCoinCommands;
import com.rubixstudios.staffcoin.data.Config;
import com.rubixstudios.staffcoin.data.ConfigFile;
import com.rubixstudios.staffcoin.util.ColorUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;

@Getter
public final class StaffCoin extends JavaPlugin {

    @Getter private static StaffCoin instance;

    private Gson gson;

    @Setter private ConfigFile configFile;

    private StaffCoinController staffCoinController;

    @Override
    public void onEnable() {
        instance = this;

        try {
            this.configFile = new ConfigFile("config.yml");
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.registerGson();
        new Config();

        this.registerControllers();
        this.registerCommands();

        this.log("&aStaffCoin plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        this.unRegisterControllers();

        this.log("&cStaffCoin plugin has been disabled!");
    }

    private void registerCommands() {
        StaffCoin.getInstance().getCommand("staffcoin").setExecutor(new StaffCoinCommands());
    }

    private void registerControllers() {
        this.staffCoinController = new StaffCoinController();
    }

    private void unRegisterControllers() {
        this.staffCoinController.disable();
    }

    public void registerGson() {
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .enableComplexMapKeySerialization().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.translate(message));
    }
}
