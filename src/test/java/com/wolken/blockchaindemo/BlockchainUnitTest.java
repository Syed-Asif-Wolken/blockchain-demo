package com.wolken.blockchaindemo;

import com.wolken.blockchaindemo.models.Block;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockchainUnitTest {
    public static List<Block> blockchain = new ArrayList<>();
    public static int prefix = 4;
    public static String prefixString = new String(new char[prefix]).replace('\0','0');

    @BeforeAll
    public static void setUp() {
        Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
        genesisBlock.mineBlock(prefix);
        blockchain.add(genesisBlock);
        Block firstBlock = new Block("The is the First Block.", genesisBlock.getHash(), new Date().getTime());
        firstBlock.mineBlock(prefix);
        blockchain.add(firstBlock);
        Block secondBlock = new Block("The is the Second Block.", firstBlock.getHash(), new Date().getTime());
        secondBlock.mineBlock(prefix);
        blockchain.add(secondBlock);
    }

    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        Block newBlock = new Block("The is a New Block.", blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        String hash = newBlock.mineBlock(prefix);
        System.out.println("prefix: "+prefix);
        System.out.println("prefixString: "+prefixString);
        System.out.println("generatedHash: "+hash);
        assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
        blockchain.add(newBlock);
    }

    @Test
    public void givenBlockchain_whenValidated_thenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i == 0 ? "0": blockchain.get(i - 1).getHash();
            System.out.println("previousHash: "+previousHash);
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
                    && previousHash.equals(blockchain.get(i).getPreviousHash())
                    && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
            if (!flag)
                break;
        }
        assertTrue(flag);
    }

    @AfterAll
    public static void tearDown() {
        blockchain.clear();
    }
}
