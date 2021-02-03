package com.natanmaia.veterinaria.service;

import com.natanmaia.veterinaria.converter.DozerConverter;
import com.natanmaia.veterinaria.data.model.Raca;
import com.natanmaia.veterinaria.data.vo.RacaVO;
import com.natanmaia.veterinaria.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacaService {

    @Autowired
    private RacaRepository repository;

    public RacaVO create(RacaVO raca) {
        var entity = DozerConverter.parseObject(raca, Raca.class);
        return DozerConverter.parseObject(repository.save(entity), RacaVO.class);
    }

    public RacaVO update(RacaVO raca) {
        var entity = repository.findById(raca.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sem resultados para esse ID!"));

        entity.setNome(raca.getNome());
        entity.setEspecie(raca.getEspecie());
        entity.setAnimais(raca.getAnimais());

        return DozerConverter.parseObject(repository.save(entity), RacaVO.class);
    }

    public void delete(Long id) {
        Raca entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para esse ID!"));
        repository.delete(entity);
    }

    public List<RacaVO> findAll() {
        return DozerConverter.parseListObject(repository.findAll(), RacaVO.class);
    }

    public RacaVO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sem resultados para esse ID!"));
        return DozerConverter.parseObject(repository.save(entity), RacaVO.class);
    }

    public Page<RacaVO> findAllPaginate(Pageable pageable) {
        var page = repository.findAll(pageable);

        return page.map(this::convertToRacaVO);
    }

    private RacaVO convertToRacaVO(Raca pessoa) {
        return DozerConverter.parseObject(repository.save(pessoa), RacaVO.class);
    }

}
