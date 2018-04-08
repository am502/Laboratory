package ru.kpfu.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.dao.AutosDao;
import ru.kpfu.itis.model.Automobile;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.repository.AutosRepository;
import ru.kpfu.itis.service.AutosService;

import java.util.List;

@Service
public class AutosServiceImpl implements AutosService {

    @Autowired
    private AutosRepository autosRepository;

    public Automobile addAuto(Automobile automobile) {
        autosRepository.save(automobile);
        return automobile;
    }

    public void update(Automobile automobile) {
        autosRepository.save(automobile);
    }

    public List<Automobile> findAll() {
        return autosRepository.findAll();
    }

    public Automobile findById(Long id) {
        return autosRepository.findOne(id);
    }
}
