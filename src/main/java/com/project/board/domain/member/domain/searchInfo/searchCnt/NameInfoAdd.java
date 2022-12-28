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

        return name.equals("title")||name.equals("all");
    }

    @Override
    public void addCnt(String title) {

        for (String name : getNameByBlank(title)) {
            String duplication=null;

            duplication = orderMap.keySet().stream()
                    .filter(key -> key.equals(name))
                    .findFirst()
                    .orElse(null);
            //중복이 있다면 값을 증가 없다면 새로 추가
            orderMap.put(name,duplication==null? 1: orderMap.get(name)+1);
        }
    }

    public int getScore(String title){
        int sum=0;
        List<String> nameByBlank = getNameByBlank(title);
        Set<Map.Entry<String, Integer>> entries = orderMap.entrySet();

        for (String search : nameByBlank) {
            for (Map.Entry<String, Integer> entry : entries) {
                if(search.contains(entry.getKey())) sum+= entry.getValue();
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
