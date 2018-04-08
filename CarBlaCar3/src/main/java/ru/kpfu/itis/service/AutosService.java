package ru.kpfu.itis.service;


import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Automobile;

import java.util.List;

@Service
public interface AutosService {
    Automobile addAuto(Automobile automobile);

    void update(Automobile automobile);

    List<Automobile> findAll();

    Automobile findById(Long id);
}
