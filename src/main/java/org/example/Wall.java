package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall() {
        blocks = new ArrayList<>();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException("Color is null!");
        }

        return blocks.stream()
                .map(block -> block.getBlockByColor(color))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) {
            throw new IllegalArgumentException("Material is null!");
        }

        return blocks.stream()
                .map(block -> block.getBlocksByMaterial(material))
                .toList()
                .stream()
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public int count() {
        return blocks.stream()
                .mapToInt(Block::getCount)
                .sum();
    }

    public void addBlocks(Block... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
    }
}
