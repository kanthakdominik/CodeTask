package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
public class RealCompositeBlock extends RealBlock implements CompositeBlock {

    List<Block> blocks;

    public RealCompositeBlock(String color, String material) {
        super(color, material);
        blocks = new ArrayList<>();
    }

    @Override
    public Integer getCount() {
        return COUNTER_VALUE + getBlocks().stream()
                .mapToInt(Block::getCount)
                .sum();
    }

    @Override
    public Optional<Block> checkColor(String color) {
        if (getColor().equals(color)) {
            return Optional.of(this);
        } else {
            return blocks.stream()
                    .map(block -> block.checkColor(color))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();
        }
    }

    @Override
    public List<Block> checkMaterial(String material) {
        List<Block> searchedBlocks = new ArrayList<>(blocks.stream()
                .map(block -> block.checkMaterial(material))
                .toList()
                .stream()
                .flatMap(List::stream)
                .toList());

        if (getMaterial().equals(material)) {
            searchedBlocks.add(this);
        }
        return searchedBlocks;
    }

    public void addBlocks(Block... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
    }
}
