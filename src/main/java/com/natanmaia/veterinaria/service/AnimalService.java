package com.natanmaia.veterinaria.service;

import com.natanmaia.veterinaria.converter.DozerConverter;
import com.natanmaia.veterinaria.data.model.Animal;
import com.natanmaia.veterinaria.data.vo.AnimalVO;
import com.natanmaia.veterinaria.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public AnimalVO create(AnimalVO animal) {
        var entity = DozerConverter.parseObject(animal, Animal.class);
        return DozerConverter.parseObject(repository.save(entity), AnimalVO.class);
    }

    public AnimalVO update(AnimalVO animal) {
        var entity = repository.findById(animal.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sem resultados para esse ID!"));

        entity.setNome(animal.getNome());
        entity.setIdade(animal.getIdade());
        entity.setRaca(animal.getRaca());

        return DozerConverter.parseObject(repository.save(entity), AnimalVO.class);
    }

    public void delete(Long id) {
        Animal entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem resultados para esse ID!"));
        repository.delete(entity);
    }

    public List<AnimalVO> findAll() {
        return DozerConverter.parseListObject(repository.findAll(), AnimalVO.class);
    }

    public AnimalVO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sem resultados para esse ID!"));
        return DozerConverter.parseObject(repository.save(entity), AnimalVO.class);
    }

    public Page<AnimalVO> findAllPaginate(Pageable pageable) {
        var page = repository.findAll(pageable);

        return page.map(this::convertToAnimalVO);
    }

    private AnimalVO convertToAnimalVO(Animal pessoa) {
        return DozerConverter.parseObject(repository.save(pessoa), AnimalVO.class);
    }

}
