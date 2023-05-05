package net.shvit.bsutils.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageCollection<K, V> {
    private final Map<K, List<V>> pages;
    public int size() {
        return pages.size();
    }

    public PageCollection() {
        this.pages = new HashMap<>();
    }

    public void add(K key,V content) {
        List<V> list = this.pages.get(key);
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(content);
        this.pages.put(key, list);
    }

    public void addPage(K key, List<V> content) {
        this.pages.put(key, content);
    }

    public ArrayList<V> get(K page) {
        return (ArrayList<V>) pages.get(page);
    }

}
