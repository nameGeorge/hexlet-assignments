package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.io.IOException;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO paramsDTO) {
        return withCategoryId(paramsDTO.getCategoryId())
                .and(withPriceGt(paramsDTO.getPriceGt()))
                .and(withPriceLt(paramsDTO.getPriceLt()))
                .and(withRatingGt(paramsDTO.getRatingGt()))
                .and(withTitleCont(paramsDTO.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) ->
                categoryId == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return ((root, query, criteriaBuilder) ->
                priceGt == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.greaterThan(root.get("price"), priceGt));
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return ((root, query, criteriaBuilder) ->
                priceLt == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.lessThan(root.get("price"), priceLt));
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return ((root, query, criteriaBuilder) ->
                ratingGt == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.greaterThan(root.get("rating"), ratingGt));
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return ((root, query, criteriaBuilder) ->
                titleCont == null
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder
                        .like(root.get("title"), "%" + titleCont + "%"));
    }


}
// END
