package com.rubixstudios.staffcoin.util;

import com.google.gson.reflect.TypeToken;
import com.rubixstudios.staffcoin.coin.object.StaffCoinPlayer;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:27
 * @project StaffCoin
 */
public class GsonUtil {

    public static final Type STAFFCOIN_TYPE = new TypeToken<Map<UUID, StaffCoinPlayer>>(){}.getType();
}
