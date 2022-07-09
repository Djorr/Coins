package com.rubixstudios.staffcoin.data;

import com.rubixstudios.staffcoin.StaffCoin;

import java.io.File;

/**
 * @author Djorr
 * @created 08/07/2022 - 16:33
 * @project StaffCoin
 */
public class Config {

    public static File STAFF_DIR;

    public Config() {
        STAFF_DIR = new File(StaffCoin.getInstance().getDataFolder(), "staff");
        if (!STAFF_DIR.exists()) STAFF_DIR.mkdir();
    }
}
