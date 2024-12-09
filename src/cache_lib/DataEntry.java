package cache_lib;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataEntry<K,V>{
    private K key;
    private V value;
    private long ttlDuration; //In Seconds
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
    private long maxSizePermitted;
}