package com.zytekaron.deathswap.game;

import lombok.Data;

@Data
public class GameSettings {
    private GameMode mode;
    
    private int teleportMin;
    private int teleportMax;
    
    private boolean countdownEnabled;
    private int countdown;
}