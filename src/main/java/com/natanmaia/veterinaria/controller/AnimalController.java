package com.natanmaia.veterinaria.controller;

import com.natanmaia.veterinaria.data.vo.AnimalVO;
import com.natanmaia.veterinaria.service.AnimalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Api(value = "API Animal", tags = {"Animais"})
@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Autowired
    private PagedResourcesAssembler<AnimalVO> assembler;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public AnimalVO findById(@PathVariable("id") Long id) {
        AnimalVO vo = service.findById(id);

        vo.add(linkTo(methodOn(AnimalController.class).findById(id)).withSelfRel());
        return vo;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<AnimalVO> findAll() {
        List<AnimalVO> vos = service.findAll();

        vos.forEach(p -> p.add(linkTo(methodOn(AnimalController.class).findById(p.getKey())).withSelfRel()));
        return vos;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
            "application/json", "application/xml", "application/x-yaml"})
    public AnimalVO create(@RequestBody AnimalVO animalVO) {
        AnimalVO vo = service.create(animalVO);
        vo.add(linkTo(methodOn(AnimalController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
            "application/json", "application/xml", "application/x-yaml"})
    public AnimalVO update(@RequestBody AnimalVO animalVO) {
        AnimalVO vo = service.update(animalVO);

        vo.add(linkTo(methodOn(AnimalController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/paginate", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAllPaginate(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

        Page<AnimalVO> vos = service.findAllPaginate(pageable);

        vos.stream().forEach(p -> p.add(linkTo(methodOn(AnimalController.class).findById(p.getKey())).withSelfRel()));

        PagedResources<?> resources = assembler.toResource(vos);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
