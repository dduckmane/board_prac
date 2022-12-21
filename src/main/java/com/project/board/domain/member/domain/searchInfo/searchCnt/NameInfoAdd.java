package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
@Embeddable
@Data
public class NameInfoAdd implements AddCnt {

    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {

        return name.equals("title");
    }

    @Override
    public void addCnt(String title) {
        getNameByBlank(title).stream().forEach(search->{

            if(orderMap.isEmpty())orderMap.put(search,1);
            orderMap.entrySet().stream().forEach(store->{

                if(store.getKey().equals(search)) orderMap.put(search,orderMap.get(search)+1);
                else orderMap.put(search,1);
            });
        });
    }

    public int getScore(String title){
        int sum=0;
        List<String> nameByBlank = getNameByBlank(title);
        Set<Map.Entry<String, Integer>> entries = orderMap.entrySet();

        for (String search : nameByBlank) {
            for (Map.Entry<String, Integer> entry : entries) {
                if(entry.getKey().equals(search)) sum+= entry.getValue();
            }
        }
        return sum;
    }

    public List<String> getNameByBlank(String name){
        if(name.length()>0){
            return Arrays.asList(name.split("\\s+"));
        }
        return new ArrayList<>();
    }
}
