package org.example;

import java.util.List;

public interface CompositeBlock extends Block {
    List<Block> getBlocks();

    /**
     * Adds Blocks instances to current Block
     */
    void addBlocks(Block... blocks);
}
