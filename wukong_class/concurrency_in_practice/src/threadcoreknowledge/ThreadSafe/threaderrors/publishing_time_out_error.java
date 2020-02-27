package threadcoreknowledge.ThreadSafe.threaderrors;

import java.util.HashMap;
import java.util.Map;
//逸出概念介绍
public class publishing_time_out_error {
    private Map<String,String> states;

    public publishing_time_out_error() {
        states = new HashMap<>();
        states.put("1","周一");
        states.put("2","周二");
        states.put("3","周三");
        states.put("4","周四");
        states.put("5","周五");
    }

    public Map<String,String> getStates(){
        return states;
    }
    public Map<String,String> getStatesImporved(){
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        publishing_time_out_error obj = new publishing_time_out_error();
        Map<String,String> states = obj.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
