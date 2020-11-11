package io.github.kraleppa.server;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private final List<String> jsonList = new ArrayList<>();

    public synchronized void add(String string){
        jsonList.add(string);
    }

    public synchronized String getFirst(){
        if (!isEmpty()){
            return jsonList.remove(0);
        }
        return null;
    }

    public boolean isEmpty(){
        return jsonList.isEmpty();
    }

    @Override
    public String toString() {
        return jsonList.size() + "";
    }
}
