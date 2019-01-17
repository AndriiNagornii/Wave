package core;

import core.chain.StageWrapper;

import java.util.Set;

public class Core {
    private ChainsKeeper chainsKeeper = new ChainsKeeper();

    public void addStage(StageWrapper sw) {
        chainsKeeper.addStage(sw);
    }

    public void process(String chainName, Object o) {
        chainsKeeper.push(chainName, o);
    }

    public Object getSynch(String chainName) {
        return chainsKeeper.getSynch(chainName);
    }

    public Set<String> getChainNames() {
        return chainsKeeper.getChainNames();
    }

    public String getChainNameAsString(String name) {
        return chainsKeeper.getChainAsList(name);
    }

}
