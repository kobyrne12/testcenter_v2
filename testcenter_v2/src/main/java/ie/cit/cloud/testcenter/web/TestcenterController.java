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

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

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
public class TestcenterController {
    @Autowired
    private TestCaseService testcaseService;
    @Autowired
    private TestPlanService testplanService;
    
/*// index (home page)
    @RequestMapping(value = { "index", "" }, method = GET)
    public String index(Model model) {
	//model.addAttribute("testcases", testcaseService.getAllTestCases());
	//SecurityContext context = SecurityContextHolder.getContext();
	//String name = context.getAuthentication().getCredentials().toString();
	//System.out.println(name);
	return "index";
    }   */
    //
    
 // index (home page)
    @RequestMapping(value = { "index", ""}, method = GET)
    public String index(@RequestParam(required = false) String errormessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);		
    	return "index";
    }    
    
    @RequestMapping(value = { "testconfig/accessdenied"}, method = GET)
    public String testconfigindex(@RequestParam(required = false) String errormessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);		
    	return "index";
    }    
    
// View Create new test plan page 
    @RequestMapping(value = { "testconfig/newtestplan"}, method = GET)
    public String newtestplan(@RequestParam(required = false) String errormessage,String successmessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);
    	return "testconfig/newtestplan";
    }   
// Add New Test Plan 
    @RequestMapping(value = { "testconfig/newtestplan"}, method = POST)   
    public String createNewTestPlan(@RequestParam String testplanName,String testername, Model model) {  
    	try{
    		// Test plan alreay exists    		
    		System.out.println("here ---: "+testplanService.getTestPlanByName(testplanName));    	
    		return "redirect:newtestplan.html?errormessage="+testplanName+" already exists";	 
    	}
    	catch(NoResultException nre)
    	{
    		// No Test Plans of this name Exist
    		try{
	    		testplanService.addNewTestPlan(new TestPlan(testplanName,testername));
	    		return "redirect:../viewtests/viewtestplan.html";    
    		}
    		catch(ConstraintViolationException CVE)
    		{   			
    			System.out.println("here3 ---::: "+CVE.getConstraintViolations());
    			return "redirect:newtestplan.html?errormessage=Testplan name must be between 2 to 32 characters";	
    		}
    	}
    
    } 
    
 // Open EditTest Plan 
    @RequestMapping(value = { "testconfig/edittestplan"}, method = GET)   
    public String editTestPlan(@RequestParam(required = false) String errormessage,String successmessage,Long testplanID, Model model) {  
    	try{
    		// Test plan exists    
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
    		model.addAttribute("testplanID", testplanID);
    		model.addAttribute("testplanName", testplanService.getTestPlan(testplanID).getTestplanName());
        	model.addAttribute("testplanTester", testplanService.getTestPlan(testplanID).getTesterName());	     	
	    	return "testconfig/edittestplan"; 
    	}
    	catch(NoResultException nre)
    	{
    		return "redirect:../viewtests/viewtestplan.html?errormessage=No Test Plan to Edit";	    		
    	}    
    }  
 // Update EditTest Plan 
    @RequestMapping(value = { "testconfig/edittestplan"}, method = POST)   
    public String updateTestPlan(@RequestParam Long testplanID,String testplanName, Model model) {    
    	testplanService.updateTestPlanNameWithId(testplanID, testplanService.getTestPlan(testplanID), testplanName);
    	return "redirect:../viewtests/viewtestplan.html?successmessage="+testplanService.getTestPlan(testplanID).getTestplanName()+" Updated to " +testplanName;	 
    }  


// View Testplans   
    @RequestMapping(value = { "viewtests/viewtestplan"}, method = GET)
    public String viewtestplan(@RequestParam(required = false) String errormessage,String successmessage,Model model) {
    	Collection<TestPlan> AllTestPlans = testplanService.getAllTestPlans();
    	if (AllTestPlans.isEmpty())
    	{
    		// No Test Plans Exist    			
    		return "redirect:../testconfig/newtestplan.html?errormessage=No Test Plans exist";        	
    	}
    	else
    	{
    		
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
	    	model.addAttribute("testplans", testplanService.getAllTestPlans());    	
	    	return "viewtests/viewtestplan";
    	}
    }  
    
// Delete Test Plan
    @RequestMapping(value = { "testconfig/deletetestplan"}, method = GET)    
    public String deletetestplan(@RequestParam Long id, Model model) {
    	String TempTestPlanName = testplanService.getTestPlan(id).getTestplanName();
    	//Collection<TestCase> testcases = testcaseService.getAllTestCasesByID(id, testplanService.getTestPlan(id));
    	testplanService.remove(id);	
    	testcaseService.removeAllTestWithID(id);	
    	return "redirect:../viewtests/viewtestplan.html?errormessage="+TempTestPlanName+" including all testcases were deleted";
     }
    
 // View new test cases   
    @RequestMapping(value = { "testconfig/newtestcase"}, method = GET)
    public String newtestcase(@RequestParam(required = false) String errormessage,String successmessage,Long testplanID,Model model) {
    	//System.out.println("Number of testplanID " + testplanID);    	
    	if (testplanID == null)
    	{
    		// No Test Plan selected
    		return "redirect:../viewtests/viewtestplan.html?errormessage=You Must select a plan";	
    	}
    	else
    	{
    		try{
	    		model.addAttribute("errormessage", errormessage);
	    		model.addAttribute("successmessage", successmessage);
	    		model.addAttribute("testplanID", testplanID);
	    		model.addAttribute("testplanName", testplanService.getTestPlan(testplanID).getTestplanName());
	        	model.addAttribute("testplanTester", testplanService.getTestPlan(testplanID).getTesterName());	
	    		return "testconfig/newtestcase";
    		}
    		catch(NoResultException NRE)
    		{
    			return "redirect:../viewtests/viewtestplan.html?errormessage=Test Plan Does Not exist";	
    		}
    	}    	
    }   
// Add New Test Case 
    @RequestMapping(value = { "testconfig/newtestcase"}, method = POST)   
    public String createNewTestCase(@RequestParam Long testplanID,String testcasename,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS, Model model) {    		
    	if (testcaseService.getAllTestCasesByName(testplanID,testcasename).isEmpty())
    	{
    		// No Test case of this name Exists
    		try{
	    		testcaseService.addNewTestCase(new TestCase(testplanService.getTestPlan(testplanID),testplanID,testcasename,testcasesummary,testcasepre,testcasesteps,testcasepass,testcaseOS), testplanService.getTestPlan(testplanID));
	    		if (testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID)))
	    		{
	    			return "redirect:enterresults.html?testplanID="+testplanID+"&successmessage="+testcasename+" added";
	    		}
	    		else
	    		{
	    			return "redirect:enterresults.html?errormessage="+testcasename+" could not be updated&testplanID="+testplanID;
	    		}
    		}
    		catch(ConstraintViolationException CVE)
    		{
    			return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage=Testcase name must be between 2 to 254 characters";	
    		}
    	}
    	else
    	{
    		// Test case already exists
    		System.out.println("here ---: "+testcaseService.getAllTestCasesByName(testplanID,testcasename));    	
    		return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage="+testcasename+" already exists";	
    				   	
    	}
    	
    } 
// Open EditTest Case 
    @RequestMapping(value = { "testconfig/edittestcase"}, method = GET)   
    public String editTestCase (@RequestParam(required = false) String errormessage,String successmessage,Long testplanID,Long testcaseID, Model model) {  
    	try{
    		// Test plan exists  
    		System.out.println("@@@ -- @@@ 1 :" + testplanID);
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
    		model.addAttribute("testplan",  testplanService.getTestPlan(testplanID));	
    		model.addAttribute("testplans", testplanService.getAllTestPlans()); 
    		model.addAttribute("testcase", testcaseService.getTestCase(testcaseID)); 
	    	return "testconfig/edittestcase"; 
    	}
    	catch(NoResultException nre)
    	{
    		return "redirect:enterresults.html?testplanID="+testplanID+"&errormessage=No Test Plan to Edit";	    		
    	}    
    }  
 // Update Test Case  
    @RequestMapping(value = { "testconfig/edittestcase"}, method = POST)   
    public String updateTestCase(@RequestParam Long testplanID,Long testplanID_changed,Long testcaseID,String testcasename,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS, Model model)
    {   
    	System.out.println("@@@ -- @@@ :" + testplanID_changed);
    	testcaseService.updateTestCaseDetails(testplanID_changed,testplanService.getTestPlan(testplanID_changed).getTestplanName(),testplanService.getTestPlan(testplanID_changed),
    	testcaseID, testcasename,testcasesummary,testcasepre,testcasesteps,testcasepass,testcaseOS,testcaseService.getTestCase(testcaseID));
    	if (testplanID_changed != testplanID)
    	{
    		// Test case Was move to ne test plan so an update of test results need to be implemented
    		if (testcaseService.getAllTestCasesByName(testplanID_changed,testcasename).isEmpty())
        	{    
    			// No Test case of this name Exists
    			testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
    			testplanService.updateTestPlan(testplanID_changed,testplanService.getTestPlan(testplanID_changed));
    			return "redirect:enterresults.html?testplanID="+testplanID_changed+"&successmessage="+testcasename+" Updated";	 
    		    
        	}
    		else
    		{
    			return "redirect:edittestcase.html?testplanID="+testplanID+"&testcaseID="+testcaseID+"&errormessage=Cannot Move "+testcasename+" To "+ testplanService.getTestPlan(testplanID_changed).getTestplanName()+" because a Test case with the same name already exists";	 
    		}
    	}
    	else
    	{
    		return "redirect:enterresults.html?testplanID="+testplanID_changed+"&successmessage="+testcasename+" Updated";	 
    	}
    	
    }     
    
// Open Enter Results page
    @RequestMapping(value = { "testconfig/enterresults" }, method = GET) 
    public String enterresults(@RequestParam(required = false) Long testplanID,String errormessage,String successmessage,Model model) {
    	System.out.println("Number of testplanID " + testplanID);    	
    	if (testplanID == null)
    	{
    		// No Test Plan selected
    		return "redirect:../viewtests/viewtestplan.html?errormessage=You Must select a plan";	
    	}
    	else
    	{
	    	try{
	    		Collection<TestCase> AllTestCases = testcaseService.getAllTestCasesByID(testplanID,testplanService.getTestPlan(testplanID));
	    		if (AllTestCases.size() <=0)
	        	{
	        		return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage=No Test cases Exist";
	        	}
	        	else
	        	{
	        		model.addAttribute("errormessage", errormessage);
		    		model.addAttribute("successmessage", successmessage);
		    		model.addAttribute("testcases", testcaseService.getAllTestCasesByID(testplanID,testplanService.getTestPlan(testplanID)));
		        	//model.addAttribute("testplanName", testplanService.getTestPlan(testplanID).getTestplanName());
		        	//model.addAttribute("testplanTester", testplanService.getTestPlan(testplanID).getTesterName());	
		        	//model.addAttribute("testplanID", testplanID);	
		        	model.addAttribute("testplan",  testplanService.getTestPlan(testplanID));	
		        	
		        	return "testconfig/enterresults";
	        	}
	    	}
	    	catch (NoResultException e)
	    	{
	    		// Not Test Cases Exist
	    		return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage=You must create a new test case";	    		
	    	}    
    	}    	
    }       
    
// Delete Test Case 
    @RequestMapping(value = "testconfig/deletetestcase", method = GET)
    public String deletetestcase(@RequestParam Long id,Long testplanID, Model model) {
	testcaseService.remove(id);	
	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
	return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
//Change test state  
    @RequestMapping(value = "testconfig/notrun", method = GET)
    public String notrun(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.notrun(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
    	return "redirect:enterresults.html?testplanID="+testplanID;
    }
    @RequestMapping(value = "testconfig/passed", method = GET)
    public String passed(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.passed(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    @RequestMapping(value = "testconfig/failed", method = GET)
    public String failed(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.failed(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    @RequestMapping(value = "testconfig/inprogress", method = GET)
    public String inprogress(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.inprogress(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    @RequestMapping(value = "testconfig/deferred", method = GET)
    public String deferred(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.deferred(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    @RequestMapping(value = "testconfig/blocked", method = GET)
    public String blocked(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.blocked(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }

}