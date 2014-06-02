package se.fam_karlsson.system.recipes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import se.fam_karlsson.system.recipes.services.RecipeServiceTest;
import se.fam_karlsson.system.recipes.services.StatusTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	StatusTest.class,
	RecipeServiceTest.class
})
public class RecipeIntegrationTestSuite {
}
