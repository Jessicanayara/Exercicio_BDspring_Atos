package com.bdspring.exercicio_bdspring.controller;


import com.bdspring.exercicio_bdspring.model.Produto;
import com.bdspring.exercicio_bdspring.repository.ProdutoRepository;
import com.bdspring.exercicio_bdspring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import org.springframework.ui.Model;


@RestController
@RequestMapping("/Produto")
public class ProdutoController {



    @Autowired
    private ProdutoRepository produtoRepository;

    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService=produtoService;

    }



    @GetMapping("/view")
    public ResponseEntity<List<Produto>> listarProduto() {
        List<Produto> produtos = produtoService.lista();
        return ResponseEntity.ok(produtos);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduto(@RequestBody Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O produto já existe");
        }

        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto salvo com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarProduto(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoService.buscar(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok().body(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletarProduto(Produto produto){

            produtoService.delete(produto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Excluido");


    }


}
