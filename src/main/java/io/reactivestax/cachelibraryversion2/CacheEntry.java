package io.reactivestax.cachelibraryversion2;

import lombok.Getter;

public class CacheEntry<V>{
    @Getter
    private V value;
    private long lastAccessedTime;
    long expiryTime;

    public CacheEntry(V value){
        this.value = value;
        this.lastAccessedTime = System.currentTimeMillis();
    }

    public void updateLastAccessedTime(){
        this.lastAccessedTime = System.currentTimeMillis();
    }

    public boolean isExpired(long currentTime){
        return  expiryTime != -1  && currentTime > expiryTime;
    }

}
