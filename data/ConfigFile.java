package com.rubixstudios.staffcoin.data;

import com.rubixstudios.staffcoin.StaffCoin;
import com.rubixstudios.staffcoin.util.ColorUtil;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:19
 * @project StaffCoin
 */
public class ConfigFile extends YamlConfiguration {

    @Getter
    private final File file;

    public ConfigFile(String name) throws RuntimeException {
        this.file = new File(StaffCoin.getInstance().getDataFolder(), name);

        if(!this.file.exists()) {
            StaffCoin.getInstance().saveResource(name, false);
        }

        try {
            this.load(this.file);
        } catch(IOException | InvalidConfigurationException e) {
            StaffCoin.getInstance().log("");
            StaffCoin.getInstance().log("&2===&a=============================================&2===");
            StaffCoin.getInstance().log(StringUtils.center("&cError occurred while loading " + name + ".", 51));
            StaffCoin.getInstance().log("");

            Stream.of(e.getMessage().split("\n")).forEach(line -> StaffCoin.getInstance().log(line));

            StaffCoin.getInstance().log("&2===&a=============================================&2===");
            throw new RuntimeException();
        }
    }

    public void save() {
        try {
            this.save(this.file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigurationSection getSection(String name) {
        return super.getConfigurationSection(name);
    }

    @Override
    public int getInt(String path) {
        return super.getInt(path, 0);
    }

    @Override
    public double getDouble(String path) {
        return super.getDouble(path, 0.0);
    }

    @Override
    public boolean getBoolean(String path) {
        return super.getBoolean(path, false);
    }

    @Override
    public String getString(String path) {
        return ColorUtil.translate(super.getString(path, ""));
    }

    @Override
    public List<String> getStringList(String path) {
        return super.getStringList(path).stream().map(ColorUtil::translate).collect(Collectors.toList());
    }
}
