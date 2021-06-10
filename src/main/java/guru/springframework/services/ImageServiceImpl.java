package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile multipartFile) {
        try {
            Recipe recipe = recipeRepository.findById(id).orElseThrow(RuntimeException::new);

            Byte[] byteObject = new Byte[multipartFile.getBytes().length];
            int i = 0;
            for (byte b : multipartFile.getBytes()) {
                byteObject[i++] = b;
            }
            recipe.setImage(byteObject);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occured", e);
            e.printStackTrace();
        }

    }
}
