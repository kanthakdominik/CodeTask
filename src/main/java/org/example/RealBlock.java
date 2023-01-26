package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class RealBlock implements Block {

    private final String color;
    private final String material;

    @Override
    public Integer getCount() {
        return COUNTER_VALUE;
    }

    @Override
    public Optional<Block> getBlockByColor(String color) {
        return getColor().equals(color) ? Optional.of(this) : Optional.empty();
    }

    @Override
    public List<Block> getBlocksByMaterial(String material) {
        return getMaterial().equals(material) ? List.of(this) : Collections.emptyList();
    }
}
