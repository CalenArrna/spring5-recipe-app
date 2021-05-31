package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"recipe/{recipe_id}/show"})
    public String getRecipeView(@PathVariable Long recipe_id, Model model) {
        Recipe recipe = recipeService.findRecipeById(recipe_id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @RequestMapping({"recipe/new"})
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping({"recipe/{id}/update"})
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() +"/show/";
    }

    @GetMapping({"recipe/{id}/delete"})
    public String deleteRecipe(@PathVariable Long id){
        log.debug("!!!!     Invoke delete method from controller   !!!!");
        recipeService.deleteById(id);
        log.debug("!!!!     Now redirect home   !!!!");
        return "redirect:/";
    }
}
