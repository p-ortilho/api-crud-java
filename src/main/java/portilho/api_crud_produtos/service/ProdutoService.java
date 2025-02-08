package portilho.api_crud_produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portilho.api_crud_produtos.model.Produto;
import portilho.api_crud_produtos.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> ListarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> BuscarPorId(Long id){
        return produtoRepository.findById(id);
    }

    public Produto SalvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void DeletarProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
