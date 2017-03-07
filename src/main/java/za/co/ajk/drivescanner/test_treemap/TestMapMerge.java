package za.co.ajk.drivescanner.test_treemap;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TestMapMerge {

    public Map<Object, Object> merge ( Map<?, ?> map1, Map<?, ?> map2 )
    {
        Map<Object, Object> merged = new HashMap<Object, Object>();
        if ( map1 == null || map2 == null )
        {
            if ( map1 != null )
            {
                merged.putAll( map1 );
            }
            if ( map2 != null )
            {
                merged.putAll( map2 );
            }
            return merged;
        }

        Set<Object> allKeys = new HashSet<Object>();
        allKeys.addAll( map1.keySet() );
        allKeys.addAll( map2.keySet() );

        for ( Object key : allKeys )
        {
            Object v1 = map1.get( key );
            Object v2 = map2.get( key );
            if ( v1 instanceof Set || v2 instanceof Set )
            {
                Set<Object> newSet = new HashSet<Object>();
                if ( v1 instanceof Set )
                {
                    newSet.addAll( (Set) v1 );
                }
                if ( v2 instanceof Set )
                {
                    newSet.addAll( (Set) v2 );
                }
                merged.put( key, newSet );
            }
            else if ( v1 instanceof Map || v2 instanceof Map )
            {
                Map<?, ?> m1 = v1 instanceof Map ? (Map<?, ?>) v1 : null;
                Map<?, ?> m2 = v2 instanceof Map ? (Map<?, ?>) v2 : null;
                merged.put( key, merge( m1, m2 ) );
            }

        }
        return merged;
    }

    public Map deepMerge(Map original, Map newMap) {
        for (Object key : newMap.keySet()) {
            if (newMap.get(key) instanceof Map && original.get(key) instanceof Map) {
                Map originalChild = (Map) original.get(key);
                Map newChild = (Map) newMap.get(key);
                original.put(key, deepMerge(originalChild, newChild));
            } else if (newMap.get(key) instanceof List && original.get(key) instanceof List) {
                List originalChild = (List) original.get(key);
                List newChild = (List) newMap.get(key);
                for (Object each : newChild) {
                    if (!originalChild.contains(each)) {
                        originalChild.add(each);
                    }
                }
            } else {
                original.put(key, newMap.get(key));
            }
        }
        return original;
    }

   public void deepMerge2(Map<String, Object> map1, Map<String, Object> map2) {
        for(String key : map2.keySet()) {
            Object value2 = map2.get(key);
            if (map1.containsKey(key)) {
                Object value1 = map1.get(key);
                if (value1 instanceof Map && value2 instanceof Map)
                    deepMerge((Map<String, Object>) value1, (Map<String, Object>) value2);
                else if (value1 instanceof List && value2 instanceof List)
                    map1.put(key, merge((List) value1, (List) value2));
                else map1.put(key, value2);
            } else map1.put(key, value2);
        }
    }

    private List merge(List list1, List list2) {
        list2.removeAll(list1);
        list1.addAll(list2);
        return list1;
    }
}
