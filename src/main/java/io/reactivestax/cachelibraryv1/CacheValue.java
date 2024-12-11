package io.reactivestax.cachelibraryv1;

public class CacheValue<V> {
    V value;
    long TTL;
    long lastTimeAccessed;
    long expiryTime;

    public CacheValue(V value, long TTL) {
        this.value = value;
        this.TTL = TTL;
        this.lastTimeAccessed = System.currentTimeMillis();
        if(TTL == -1){
            this.expiryTime = -1;
        } else {
            this.expiryTime = System.currentTimeMillis() + TTL * 1000;
        }
    }

    public void setTTL(long TTL) {
        this.TTL = TTL;
        if(TTL == -1) {
            this.expiryTime = -1;
        } else {
            this.expiryTime = System.currentTimeMillis() + TTL * 1000;
        }
    }


    public boolean isExpired(long currentTime){
    return  expiryTime != -1  && currentTime > expiryTime;
    }
}
