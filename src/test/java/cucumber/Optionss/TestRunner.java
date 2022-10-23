package cucumber.Optionss;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/bdd/features",plugin="json:target/jsonReports/cucumber--report.json",glue= {"stepDefinations"},tags=("@DeletePlace"))
public class TestRunner {
	
	
	//plugin="json:target/jsonReports/cucumber--report.json": we are telling to testrunner that I need report
	//target:Gerenrally we will store reports in this foldser
	//in target->jsonReports (Folder)->cucumber--report.json (Filename)
	//For example you have many .feature files and if you want to execute only one then you have to mention like src/test/java/bdd/features/placeValidations.feature 
	//,tags=("@DeletePlace")
//@DeletePlace: Basically when we run this test case it will looks for hooks and execute the method based on @ annotation
}
