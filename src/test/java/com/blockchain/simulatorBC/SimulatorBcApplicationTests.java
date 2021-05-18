package com.blockchain.simulatorBC;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimulatorBcApplicationTests {
	List<Block> blockchain = new ArrayList<>();
	int prefix = 4;
	String prefixString = new String(new char[prefix]).replace('\0', '0');
	@Test
	void contextLoads() {
	}
	
	@Test
public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
    Block newBlock = new Block
	("The is a New Block.",blockchain.get(blockchain.size() - 1).getHash(),new Date().getTime());
    newBlock.mineBlock(prefix);
    assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
    blockchain.add(newBlock);
}

@Test
public void givenBlockchain_whenValidated_thenSuccess() {
    boolean flag = true;
    for (int i = 0; i < blockchain.size(); i++) {
        String previousHash = i==0 ? "0" : blockchain.get(i - 1).getHash();
        flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
          && previousHash.equals(blockchain.get(i).getPreviousHash())
          && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
            if (!flag) break;
    }
    assertTrue(flag);
}

}
