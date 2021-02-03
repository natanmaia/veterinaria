package com.natanmaia.veterinaria.service;

import com.natanmaia.veterinaria.converter.DozerConverter;
import com.natanmaia.veterinaria.data.model.Especie;
import com.natanmaia.veterinaria.data.vo.EspecieVO;
import com.natanmaia.veterinaria.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository repository;

    public EspecieVO create(EspecieVO especie) {
        var entity = DozerConverter.parseObject(especie, Especie.class);
        return DozerConverter.parseObject(repository.save(entity), EspecieVO.class);
    }

    public EspecieVO update(EspecieVO especie) {
        var entity = repository.findById(especie.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sem resultados para esse ID!"));

        entity.setNome(especie.getNome());
        entity.setRacas(especie.getRacas());

        return DozerConverter.parseObject(repository.save(entity), EspecieVO.class);
    }

    public void delete(Long id) {
        Especie entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para esse ID!"));
        repository.delete(entity);
    }

    public List<EspecieVO> findAll() {
        return DozerConverter.parseListObject(repository.findAll(), EspecieVO.class);
    }

    public EspecieVO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sem resultados para esse ID!"));
        return DozerConverter.parseObject(repository.save(entity), EspecieVO.class);
    }

    public Page<EspecieVO> findAllPaginate(Pageable pageable) {
        var page = repository.findAll(pageable);

        return page.map(this::convertToEspecieVO);
    }

    private EspecieVO convertToEspecieVO(Especie pessoa) {
        return DozerConverter.parseObject(repository.save(pessoa), EspecieVO.class);
    }

}
