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
    @Builder.Default
    private long ttlDuration = 60; //In Seconds
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
}