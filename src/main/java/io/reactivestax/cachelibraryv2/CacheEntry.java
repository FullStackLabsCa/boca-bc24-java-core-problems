package io.reactivestax.cachelibraryv2;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Getter
@Setter
public class CacheEntry<K, V> {
    K key;
    V value;
    long lastAccessedTime;
    long expiryTime;
    int accessCount;
    long TTL;

    public CacheEntry(K key,V value, long TTL) {
        this.key = key;
        this.TTL = TTL;
        this.value = value;
        this.lastAccessedTime = System.currentTimeMillis();
        this.accessCount = 0;
        if(TTL == -1){
            this.expiryTime = -1;
        } else {
            this.expiryTime = System.currentTimeMillis() + TTL * 1000;
        }
    }

    public void updateAccessTime(){
        this.lastAccessedTime = System.currentTimeMillis();
        this.countFrequency();
    }

    public boolean isExpired(long currentTime) {
        return expiryTime != -1 && currentTime > expiryTime;
    }

    public void countFrequency(){
        this.accessCount++;
    }

}
