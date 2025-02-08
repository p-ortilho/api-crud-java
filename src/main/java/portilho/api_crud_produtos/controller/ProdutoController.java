package portilho.api_crud_produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portilho.api_crud_produtos.model.Produto;
import portilho.api_crud_produtos.repository.ProdutoRepository;
import portilho.api_crud_produtos.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    private final ProdutoRepository produtoRepository;
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> ListarProdutos(){
        return produtoService.ListarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> BuscarPorId(@PathVariable Long id){
        Optional<Produto> produto = produtoService.BuscarPorId(id);
        return produto.map(resp -> ResponseEntity.ok(resp)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto SalvarProduto(@RequestBody Produto produto){
        return produtoService.SalvarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> AtualizarProduto(@PathVariable Long id, @RequestBody Produto produto){
        Optional<Produto> produtoBuscado = produtoService.BuscarPorId(id);

        if (produtoBuscado.isPresent()){
            Produto produtoAtualizado = produtoBuscado.get();

            produtoAtualizado.setTitle(produto.getTitle());
            produtoAtualizado.setImage(produto.getImage());
            produtoAtualizado.setPrice(produto.getPrice());

            Produto produtoSalvo = produtoService.SalvarProduto(produtoAtualizado);

            return ResponseEntity.ok(produtoSalvo);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarProduto(@PathVariable Long id){
        if (produtoService.BuscarPorId(id).isPresent()){
            produtoService.DeletarProduto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
