package it.learn.gc.pojo;

public class DirtyObj {

    private byte[] data;

    public DirtyObj(int byteSize) {
        data = new byte[byteSize];
    }
}
