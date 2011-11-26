/**
 * 
 */
package ie.cit.cloud.testcenter.web;

/**
 * @author byrnek1
 *
 */

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import ie.cit.cloud.testcenter.TestCaseService;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.TestPlanService;
import ie.cit.cloud.testcenter.model.TestPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class TestCaseController {
    @Autowired
    private TestCaseService testcaseService;
    @Autowired
    private TestPlanService testplanService;
    
// index (home page)
    @RequestMapping(value = { "index", "" }, method = GET)
    public String index(Model model) {
	//model.addAttribute("testcases", testcaseService.getAllTestCases());
	//SecurityContext context = SecurityContextHolder.getContext();
	//String name = context.getAuthentication().getName();
	//System.out.println(name);
	return "index";
    }   
    
// testconfig home page   
    @RequestMapping(value = { "testconfig", "" }, method = GET)
    public String testconfig(Model model) {	
	return "testconfig/testconfig";
    } 
    
// new test plan   
    @RequestMapping(value = { "newtestplan", "" }, method = GET)
    public String newtestplan(Model model) {	
	return "testconfig/newtestplan";
    }   
    @RequestMapping(value = { "newtestplan", "" }, method = POST)   
    public String createNewTestPlan(@RequestParam String testplanName,String testername, Model model) {    		
    	testplanService.addNewTestPlan(new TestPlan(testplanName,testername));
	return "redirect:viewtestplan.html";
    }  

// View Test plan   
    @RequestMapping(value = { "viewtestplan", "" }, method = GET)
    public String viewtestplan(Model model) {	
    	model.addAttribute("testplans", testplanService.getAllTestPlans());
    	
    	//SecurityContext context = SecurityContextHolder.getContext();
    	//String name = context.getAuthentication().getName();
    	//System.out.println(name);
	return "testconfig/viewtestplan";
    }    
 
    @RequestMapping(value = { "edittestcases", "" }, method = GET) 
    public String edittestcases(@RequestParam Long testplanID,Model model) {	
    	model.addAttribute("testcases", testcaseService.getAllTestCases(testplanService.getTestPlan(testplanID)));
	return "testconfig/edittestcases";
    }   
// new test cases   
    @RequestMapping(value = { "newtestcase", "" }, method = GET)
    public String newtestcase(Model model) {
    	model.addAttribute("testplans", testplanService.getAllTestPlans());
	return "testconfig/newtestcase";
    }       
    @RequestMapping(value = { "newtestcase", "" }, method = POST)   
    public String createNewTestCase(@RequestParam Long testplanID,String testcasename,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS, Model model) {    		
    	testcaseService.addNewTestCase(new TestCase(testplanService.getTestPlan(testplanID),testcasename,testcasesummary,testcasepre,testcasesteps,testcasepass,testcaseOS), testplanService.getTestPlan(testplanID));
	return "redirect:viewtestplan.html";
    }      
    
// edit test cases  
   // @RequestMapping(value = { "edittestcases", "" }, method = GET)  
   /* @RequestMapping(value = { "edittestcases", "" }, method = POST) 
    public String edittestcases(@RequestParam Long testplanID,Model model) {	
    	model.addAttribute("testcases", testcaseService.getAllTestCases(testplanService.getTestPlan(testplanID)));
	return "testconfig/edittestcases";
    }  */  
    @RequestMapping(value = "delete", method = GET)
    public String delete(@RequestParam Long id, Model model) {
	testcaseService.remove(id);	
	return "redirect:edittestcases.html";
    }
    @RequestMapping(value = "notrun", method = GET)
    public String notrun(@RequestParam Long id, Model model) {
	testcaseService.notrun(testcaseService.getTestCase(id));	
	return "redirect:edittestcases.html";
    }
    @RequestMapping(value = "passed", method = GET)
    public String passed(@RequestParam Long id, Model model) {
	testcaseService.passed(testcaseService.getTestCase(id));
	return "redirect:edittestcases.html";
    }
    @RequestMapping(value = "failed", method = GET)
    public String failed(@RequestParam Long id, Model model) {
	testcaseService.failed(testcaseService.getTestCase(id));
	return "redirect:edittestcases.html";
    }
    @RequestMapping(value = "inprogress", method = GET)
    public String inprogress(@RequestParam Long id, Model model) {
	testcaseService.inprogress(testcaseService.getTestCase(id));
	return "redirect:edittestcases.html";
    }
    @RequestMapping(value = "deferred", method = GET)
    public String deferred(@RequestParam Long id, Model model) {
	testcaseService.deferred(testcaseService.getTestCase(id));
	return "redirect:edittestcases.html";
    }
    @RequestMapping(value = "blocked", method = GET)
    public String blocked(@RequestParam Long id, Model model) {
	testcaseService.blocked(testcaseService.getTestCase(id));
	return "redirect:edittestcases.html";
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }

}