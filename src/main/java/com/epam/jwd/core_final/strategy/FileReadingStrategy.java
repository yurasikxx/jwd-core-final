package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileReadingStrategy<T extends BaseEntity> {

    void readFromFile(String path) throws FileNotFoundException;

    List<T> getEntities();

}
