package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String getImageForm(@PathVariable Long id, Model model) {
        RecipeCommand command = recipeService.findCommandById(id);
        if (command != null) model.addAttribute("recipe", command);
        return "/recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost (@PathVariable Long id, @RequestParam("imagefile") MultipartFile multipartFile) {

        imageService.saveImageFile(id, multipartFile);

        return "redirect:/recipe/" + id + "/show";

    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB (@PathVariable Long id, HttpServletResponse response) throws IOException {
        RecipeCommand command = recipeService.findCommandById(id);

        byte[] byteArray = new byte[command.getImage().length];

        int i = 0;
        for(Byte wrappedByte : command.getImage()) {
            byteArray[i++] = wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
