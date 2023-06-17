package com.bdspring.exercicio_bdspring.service;

import com.bdspring.exercicio_bdspring.model.Produto;
import com.bdspring.exercicio_bdspring.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProdutoService {

    final ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;


    }


    @Transactional
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean existsByNome(String nome) {
        return produtoRepository.existsByNome(nome);
    }


    public List<Produto> lista() {
        return produtoRepository.findAll();
    }


    public Optional<Produto> buscar(long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }

}
