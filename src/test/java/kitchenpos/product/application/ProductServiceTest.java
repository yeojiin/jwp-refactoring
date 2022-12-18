package kitchenpos.product.application;

import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.product.application.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("상품 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product honeycombo;
    private Product redwing;

    @BeforeEach
    void setUp() {
        honeycombo = Product.of(1L, "honeycombo", BigDecimal.valueOf(18000));
        redwing = Product.of(2L, "redwing", BigDecimal.valueOf(19000));
    }

    @DisplayName("상품을 생성한다.")
    @Test
    void create() {
        when(productRepository.save(any())).thenReturn(honeycombo);

        ProductResponse result = productService.create(honeycombo);

        assertThat(result).isEqualTo(ProductResponse.from(honeycombo));
    }


    @DisplayName("상품 목록을 조회한다.")
    @Test
    void list() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(honeycombo, redwing));

        List<ProductResponse> results = productService.list();

        assertAll(
                () -> assertThat(results).hasSize(2),
                () -> assertThat(results).containsExactly(ProductResponse.from(honeycombo), ProductResponse.from(redwing))
        );
    }
}