package portilho.api_crud_produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portilho.api_crud_produtos.model.Produto;

public interface ProdutoRepository  extends JpaRepository <Produto, Long>{
}
