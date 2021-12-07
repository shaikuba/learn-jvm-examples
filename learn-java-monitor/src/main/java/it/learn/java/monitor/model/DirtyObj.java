package it.learn.java.monitor.model;

public class DirtyObj {

    private byte[] data;

    public DirtyObj(int byteSize) {
        data = new byte[byteSize];
    }
}
