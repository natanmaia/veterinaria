package com.natanmaia.veterinaria.controller;

import com.natanmaia.veterinaria.data.vo.EspecieVO;
import com.natanmaia.veterinaria.service.EspecieService;
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

@Api(value = "API Especie", tags = {"Especies"})
@RestController
@RequestMapping("/especie")
public class EspecieController {

    @Autowired
    private EspecieService service;

    @Autowired
    private PagedResourcesAssembler<EspecieVO> assembler;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public EspecieVO findById(@PathVariable("id") Long id) {
        EspecieVO vo = service.findById(id);

        vo.add(linkTo(methodOn(EspecieController.class).findById(id)).withSelfRel());
        return vo;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<EspecieVO> findAll() {
        List<EspecieVO> vos = service.findAll();

        vos.forEach(p -> p.add(linkTo(methodOn(EspecieController.class).findById(p.getKey())).withSelfRel()));
        return vos;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
            "application/json", "application/xml", "application/x-yaml"})
    public EspecieVO create(@RequestBody EspecieVO especieVO) {
        EspecieVO vo = service.create(especieVO);
        vo.add(linkTo(methodOn(EspecieController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
            "application/json", "application/xml", "application/x-yaml"})
    public EspecieVO update(@RequestBody EspecieVO especieVO) {
        EspecieVO vo = service.update(especieVO);

        vo.add(linkTo(methodOn(EspecieController.class).findById(vo.getKey())).withSelfRel());
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

        Page<EspecieVO> vos = service.findAllPaginate(pageable);

        vos.stream().forEach(p -> p.add(linkTo(methodOn(EspecieController.class).findById(p.getKey())).withSelfRel()));

        PagedResources<?> resources = assembler.toResource(vos);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
