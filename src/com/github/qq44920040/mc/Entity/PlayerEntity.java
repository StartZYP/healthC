package com.github.qq44920040.mc.Entity;

public class PlayerEntity {
    private String Cmd;
    private int maxnum;
    private int minnum;

    public PlayerEntity(String cmd, int maxnum, int minnum) {
        Cmd = cmd;
        this.maxnum = maxnum;
        this.minnum = minnum;
    }

    public String getCmd() {
        return Cmd;
    }

    public void setCmd(String cmd) {
        Cmd = cmd;
    }

    public int getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(int maxnum) {
        this.maxnum = maxnum;
    }

    public int getMinnum() {
        return minnum;
    }

    public void setMinnum(int minnum) {
        this.minnum = minnum;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "Cmd='" + Cmd + '\'' +
                ", maxnum=" + maxnum +
                ", minnum=" + minnum +
                '}';
    }
}
