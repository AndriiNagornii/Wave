package core;

import core.chain.SimpleChain;
import core.chain.StageWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChainsKeeper {

    private Map<String, SimpleChain> chainMap = new HashMap<>();

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    /**
     * create chain if absent
     * @param sw
     */
    public void addStage(StageWrapper sw) {
        var chainName = sw.getChainName();
        var chain = chainMap
                .getOrDefault(chainName, new SimpleChain(chainName, executor))
                .addStage(sw);
        chainMap.put(chain.getName(), chain);
    }

    public void push(String chainName, Object ...in) {
        chainMap
                .get(chainName)
                .push(in);
    }

    public Object getSynch(String chainName) {
        return chainMap
                .get(chainName)
                .getSynch();
    }

    public Set<String> getChainNames() {
        return chainMap.keySet();
    }

    public String getChainAsList(String chainName) {
        return chainMap.get(chainName).getChainAsList();
    }

}
