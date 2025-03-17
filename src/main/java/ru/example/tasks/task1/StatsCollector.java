package ru.example.tasks.task1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;

public class StatsCollector {
    public static <K,V> Collector<Map.Entry<K,V>, ?, LinkedHashMap<K, V>> toLinkedHashMap() {
        return Collector.of(
                LinkedHashMap::new,
                (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                }
        );
    }
}
