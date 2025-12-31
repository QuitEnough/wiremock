package com.yana.catapiintegration.service;

import com.yana.catapiintegration.api.CatsApi;
import com.yana.catapiintegration.dto.CatDto;
import com.yana.catapiintegration.exceptionhandler.CatApiUnauthorizedException;
import com.yana.catapiintegration.mapper.CatMapper;
import feign.FeignException;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatMapper catMapper;
    private final CatsApi catsApi;
    private final RateLimiter catApiRateLimiter;

    public List<CatDto> getAll() {
        Supplier<List<CatDto>> catsSupplier = () -> {
            try {
                var response = catsApi.getCats();
                var externalCats = response.getBody();
                return catMapper.toDto(externalCats);
            } catch (FeignException.Unauthorized e) {
                throw new CatApiUnauthorizedException("Missing of incorrect API key");
            }
        };

        return RateLimiter
                .decorateSupplier(catApiRateLimiter, catsSupplier)
                .get();
    }

    public CatDto getCatById(Long id) {
        Supplier<CatDto> catByIdSupplier = () -> {
            var response = catsApi.getCatById(id);
            var externalCat = response.getBody();
            return catMapper.toDto(externalCat);
        };

        return RateLimiter
                .decorateSupplier(catApiRateLimiter, catByIdSupplier)
                .get();
    }

}
